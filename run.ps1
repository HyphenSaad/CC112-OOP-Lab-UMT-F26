#!/usr/bin/env pwsh
<#
.SYNOPSIS
    Chirp OOP Labs - build, run, test, pack. One command for everything.

.DESCRIPTION
    Run in interactive mode (no args) or supply -Lab / -Level / -Problem /
    -Operation on the command line. Missing arguments are prompted for.

    In interactive mode you can press `b` at any step (except step 1) to go
    back to the previous step, or `q` to quit. Every step re-renders as a
    fresh page.

    Operations:
      init             Create workspace/ from starter/ (must be run once per problem)
      run              Compile and run your workspace/     (requires init)
      test             Compile workspace/ + tests/          (requires init)
      solution         Compile and run the reference solution (no init needed)
      pack             Zip your workspace/ into dist/       (requires init at problem level)
      run,test         Run your code, then ask before the test phase
      solution,test    Run reference, then verify it passes the same tests

    IMPORTANT: run and test require the workspace to be initialised first.
    Call `init` once; the runner will refuse a second init and tell you
    how to reset if you really want to start over.

    Prerequisites are handled automatically:
      - Java checked (auto-installs via winget/brew/apt if missing)
      - Chirp jar hash-checked against the latest release, refreshed if changed
      - JUnit console launcher downloaded if absent

    No .class files ever touch the project - every compile goes into a temp
    directory that is deleted before exit.

.EXAMPLE
    ./run.ps1 -l 1 -v 1 -p 1 -o init

.EXAMPLE
    ./run.ps1 -l 1 -v 1 -p 1 -o "solution test"

.EXAMPLE
    ./run.ps1 -l 1 -v 1 -p 1 -o pack

.EXAMPLE
    ./run.ps1 -u
#>
param(
    [Alias("l")][int]$Lab = 0,
    [Alias("v")][int]$Level = 0,
    [Alias("p")][int]$Problem = 0,
    [Alias("o")][string]$Operation = "",

    [Alias("u")][switch]$UpdateLib,
    [Alias("j")][int]$RequiredJava = 17,

    [Alias("y")][switch]$Yes,
    [Alias("h")][switch]$Help,
    [Alias("nc")][switch]$NoColor,
    [Alias("nk")][switch]$NoClear
)

$ErrorActionPreference = "Stop"

# ============================================================ configuration
$RootDir      = $PSScriptRoot
$ToolsDir     = Join-Path $RootDir ".tools"
$DistDir      = Join-Path $RootDir "dist"
$ChirpJar     = Join-Path $ToolsDir "chirp-1.0.0.jar"
$JUnitJar     = Join-Path $ToolsDir "junit-platform-console-standalone-1.10.0.jar"
$MetaPath     = Join-Path $ToolsDir ".chirp-meta.json"
$ChirpApiUrl  = "https://api.github.com/repos/HyphenSaad/chirp/releases/latest"
$ChirpUrl     = "https://github.com/HyphenSaad/chirp/releases/download/v1.0.0/chirp-1.0.0.jar"
$JUnitUrl     = "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.0/junit-platform-console-standalone-1.10.0.jar"
$ChirpVersion = "1.0.0"
$MinJava      = $RequiredJava

# ============================================================ colored output
function C-Info    { param($m) if (-not $NoColor) { Write-Host $m -ForegroundColor Cyan }     else { Write-Host $m } }
function C-Success { param($m) if (-not $NoColor) { Write-Host $m -ForegroundColor Green }    else { Write-Host $m } }
function C-Warn    { param($m) if (-not $NoColor) { Write-Host $m -ForegroundColor Yellow }   else { Write-Host $m } }
function C-Error   { param($m) if (-not $NoColor) { Write-Host $m -ForegroundColor Red }      else { Write-Host $m } }
function C-Dim     { param($m) if (-not $NoColor) { Write-Host $m -ForegroundColor DarkGray } else { Write-Host $m } }
function C-Brand   { param($m) if (-not $NoColor) { Write-Host $m -ForegroundColor Magenta }  else { Write-Host $m } }
function C-Head    {
    param($m)
    Write-Host ""
    if (-not $NoColor) {
        Write-Host $m -ForegroundColor Magenta
        Write-Host ("-" * [Math]::Min($m.Length, 70)) -ForegroundColor Magenta
    } else {
        Write-Host $m
        Write-Host ("-" * [Math]::Min($m.Length, 70))
    }
}

function Abort { param($m) C-Error $m; exit 1 }

# ============================================================ help
if ($Help) {
    Get-Help $PSCommandPath -Detailed
    exit 0
}

# ============================================================ banner
function Show-Banner {
    param([int]$JavaReq = 17, [string]$ChirpVersion = "1.0.0")

    $unicode = $true
    try {
        $cp = [Console]::OutputEncoding.CodePage
        $unicode = ($cp -eq 65001 -or $cp -eq 1200)
    } catch { $unicode = $false }

    $tl = if ($unicode) { [char]0x256D } else { '+' }
    $tr = if ($unicode) { [char]0x256E } else { '+' }
    $bl = if ($unicode) { [char]0x2570 } else { '+' }
    $br = if ($unicode) { [char]0x256F } else { '+' }
    $h  = if ($unicode) { [char]0x2500 } else { '-' }
    $v  = if ($unicode) { [char]0x2502 } else { '|' }
    $dot = if ($unicode) { [char]0x00B7 } else { '*' }

    $w = 56
    $title  = "  CHIRP OOP LABS"
    $sub    = "  Object-Oriented Programming in Java"
    $credit = "  Powered by Chirp v$ChirpVersion $dot Java $JavaReq+"

    $top = "  $tl" + ("$h" * $w) + "$tr"
    $bot = "  $bl" + ("$h" * $w) + "$br"

    if ($NoColor) {
        Write-Host ""
        Write-Host $top
        Write-Host "  $v" -NoNewline; Write-Host $title.PadRight($w) -NoNewline;  Write-Host "$v"
        Write-Host "  $v" -NoNewline; Write-Host $sub.PadRight($w) -NoNewline;    Write-Host "$v"
        Write-Host "  $v" -NoNewline; Write-Host $credit.PadRight($w) -NoNewline; Write-Host "$v"
        Write-Host $bot
        return
    }

    Write-Host ""
    Write-Host $top -ForegroundColor Cyan
    Write-Host "  $v" -ForegroundColor Cyan -NoNewline
    Write-Host $title.PadRight($w) -ForegroundColor Magenta -NoNewline
    Write-Host "$v" -ForegroundColor Cyan
    Write-Host "  $v" -ForegroundColor Cyan -NoNewline
    Write-Host $sub.PadRight($w) -ForegroundColor White -NoNewline
    Write-Host "$v" -ForegroundColor Cyan
    Write-Host "  $v" -ForegroundColor Cyan -NoNewline
    Write-Host $credit.PadRight($w) -ForegroundColor DarkGray -NoNewline
    Write-Host "$v" -ForegroundColor Cyan
    Write-Host $bot -ForegroundColor Cyan
}

# ============================================================ clear + banner
if (-not $NoClear) { try { Clear-Host } catch {} }
Show-Banner -JavaReq $RequiredJava -ChirpVersion $ChirpVersion

# ============================================================ text helpers

function Read-FileUtf8 {
    param([string]$Path)
    try {
        return [System.IO.File]::ReadAllText($Path, [System.Text.Encoding]::UTF8)
    } catch {
        return $null
    }
}

function ConvertTo-AsciiSafe {
    param([string]$s)
    if ([string]::IsNullOrEmpty($s)) { return "" }

    $emDash    = [string][char]0x2014
    $enDash    = [string][char]0x2013
    $lSQuote   = [string][char]0x2018
    $rSQuote   = [string][char]0x2019
    $lDQuote   = [string][char]0x201C
    $rDQuote   = [string][char]0x201D
    $ellipsis  = [string][char]0x2026
    $midDot    = [string][char]0x00B7

    $s = $s.Replace($emDash, '-')
    $s = $s.Replace($enDash, '-')
    $s = $s.Replace($lSQuote, "'")
    $s = $s.Replace($rSQuote, "'")
    $s = $s.Replace($lDQuote, '"')
    $s = $s.Replace($rDQuote, '"')
    $s = $s.Replace($ellipsis, '...')
    $s = $s.Replace($midDot, '-')

    $sb = New-Object System.Text.StringBuilder
    foreach ($c in $s.ToCharArray()) {
        if ([int]$c -lt 128) { [void]$sb.Append($c) }
    }
    $clean = $sb.ToString()
    return ($clean -replace '\s+', ' ').Trim()
}

# ============================================================ environment

function Get-ClassSep {
    if ($PSVersionTable.PSVersion.Major -lt 6) { return ";" }
    if ($null -ne $IsWindows -and $IsWindows) { return ";" }
    return ":"
}
$ClassSep = Get-ClassSep

function Quote-Win32Arg {
    param([string]$a)
    if ([string]::IsNullOrEmpty($a)) { return '""' }
    if ($a -notmatch '[\s"]') { return $a }

    $sb = New-Object System.Text.StringBuilder
    [void]$sb.Append('"')
    $backslashes = 0
    foreach ($c in $a.ToCharArray()) {
        if ($c -eq [char]'\') {
            $backslashes++
            [void]$sb.Append($c)
        } elseif ($c -eq [char]'"') {
            [void]$sb.Append([string]::new([char]'\', $backslashes + 1))
            [void]$sb.Append('"')
            $backslashes = 0
        } else {
            $backslashes = 0
            [void]$sb.Append($c)
        }
    }
    if ($backslashes -gt 0) {
        [void]$sb.Append([string]::new([char]'\', $backslashes))
    }
    [void]$sb.Append('"')
    return $sb.ToString()
}

function Run-Process {
    param(
        [Parameter(Mandatory=$true)][string]$Command,
        [Parameter(Mandatory=$true)][string[]]$Arguments,
        [switch]$Capture
    )

    $argLine = ($Arguments | ForEach-Object { Quote-Win32Arg $_ }) -join ' '

    $psi = New-Object System.Diagnostics.ProcessStartInfo
    $psi.FileName               = $Command
    $psi.Arguments              = $argLine
    $psi.UseShellExecute        = $false

    if ($Capture) {
        $psi.RedirectStandardOutput = $true
        $psi.RedirectStandardError  = $true
        $psi.CreateNoWindow         = $true
    }

    $p = [System.Diagnostics.Process]::Start($psi)

    if ($Capture) {
        $stdout = $p.StandardOutput.ReadToEnd()
        $stderr = $p.StandardError.ReadToEnd()
        $p.WaitForExit()
        return [PSCustomObject]@{
            ExitCode = $p.ExitCode
            StdOut   = $stdout
            StdErr   = $stderr
            Combined = "$stdout$stderr"
        }
    }

    $p.WaitForExit()
    return [PSCustomObject]@{ ExitCode = $p.ExitCode; StdOut = ""; StdErr = ""; Combined = "" }
}

function Get-JavaMajor {
    try {
        $r = Run-Process "java" @("-version") -Capture
        $out = $r.Combined
        if ($out -match 'version "1\.(\d+)') { return [int]$matches[1] }
        if ($out -match 'version "(\d+)')   { return [int]$matches[1] }
        return 0
    } catch { return 0 }
}

function Ensure-Java {
    $major = Get-JavaMajor
    if ($major -ge $MinJava) { return }

    if ($major -gt 0) { C-Warn "Java $major found, need $MinJava+. Attempting install..." }
    else              { C-Warn "Java not found. Attempting install..." }

    if (Get-Command winget -ErrorAction SilentlyContinue) {
        C-Info "Installing Microsoft OpenJDK $MinJava via winget..."
        $exit = (Run-Process "winget" @(
            "install","--id","Microsoft.OpenJDK.$MinJava",
            "--accept-source-agreements","--accept-package-agreements","--silent"
        )).ExitCode
        $newMajor = Get-JavaMajor
        if ($newMajor -ge $MinJava) { C-Success "Java $newMajor detected."; return }
        if ($exit -eq 0) {
            C-Warn "Winget reported success, but Java is not yet visible in this shell."
            C-Warn "Close and reopen your terminal, then re-run the script."
            exit 0
        }
        C-Warn "winget install did not succeed. Trying other package managers..."
    }

    if (Get-Command brew -ErrorAction SilentlyContinue) {
        C-Info "Installing OpenJDK $MinJava via brew..."
        [void](Run-Process "brew" @("install","openjdk@$MinJava"))
        if ((Get-JavaMajor) -ge $MinJava) {
            C-Success "Java installed. Please reopen your terminal and re-run."
            exit 0
        }
    }
    if (Get-Command apt-get -ErrorAction SilentlyContinue) {
        C-Warn "Install requires sudo. Please run manually:"
        C-Warn "  sudo apt install openjdk-$MinJava-jdk"
        exit 1
    }
    if (Get-Command dnf -ErrorAction SilentlyContinue) {
        C-Warn "Install requires sudo. Please run manually:"
        C-Warn "  sudo dnf install java-$MinJava-openjdk-devel"
        exit 1
    }

    Abort "Could not find or install Java $MinJava+. Verify with: java -version"
}

function Update-ChirpLib {
    param([switch]$Force)

    if (-not (Test-Path $ToolsDir)) { New-Item -ItemType Directory -Force -Path $ToolsDir | Out-Null }

    $meta = $null
    if (Test-Path $MetaPath) {
        try { $meta = Get-Content $MetaPath -Raw | ConvertFrom-Json } catch { }
    }
    $needCheck = $Force -or -not (Test-Path $ChirpJar)
    if (-not $needCheck -and $meta -and $meta.checked_at) {
        try {
            $age = (Get-Date).ToUniversalTime() - [datetime]::Parse($meta.checked_at).ToUniversalTime()
            if ($age.TotalHours -gt 24) { $needCheck = $true }
        } catch { $needCheck = $true }
    }
    if (-not $needCheck) { return }

    C-Info "Checking Chirp library version..."

    $latestTag = $null
    try {
        $release = Invoke-RestMethod -Uri $ChirpApiUrl `
            -Headers @{ "User-Agent" = "chirp-labs-runner" } `
            -TimeoutSec 10 -ErrorAction Stop
        $latestTag = $release.tag_name
    } catch {
        C-Dim "  (GitHub API unavailable: $($_.Exception.Message))"
    }

    $tempJar = "$ChirpJar.tmp"
    try {
        C-Info "  fetching jar..."
        [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
        Invoke-WebRequest -Uri $ChirpUrl -OutFile $tempJar -UseBasicParsing -ErrorAction Stop | Out-Null
    } catch {
        Remove-Item $tempJar -ErrorAction SilentlyContinue
        C-Warn "Could not download Chirp. Using existing jar if present."
        return
    }

    $newHash = (Get-FileHash $tempJar -Algorithm SHA256).Hash
    $oldHash = $null
    if (Test-Path $ChirpJar) { $oldHash = (Get-FileHash $ChirpJar -Algorithm SHA256).Hash }

    if ($newHash -eq $oldHash) {
        Remove-Item $tempJar -ErrorAction SilentlyContinue
        C-Dim "  Chirp library up to date ($($newHash.Substring(0,12))...)"
    } else {
        if (Test-Path $ChirpJar) { Remove-Item $ChirpJar -Force }
        Move-Item $tempJar $ChirpJar -Force
        if ($oldHash) { C-Success "  Chirp library updated (hash changed)" }
        else          { C-Success "  Chirp library installed" }
    }

    $newMeta = @{
        version    = if ($latestTag) { $latestTag.TrimStart('v') } else { 'unknown' }
        tag        = $latestTag
        hash       = $newHash
        checked_at = (Get-Date).ToUniversalTime().ToString('o')
    }
    $newMeta | ConvertTo-Json | Set-Content $MetaPath
}

function Download-File {
    param($Url, $Dest, $Label)
    [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
    C-Info "Downloading $Label..."
    try {
        Invoke-WebRequest -Uri $Url -OutFile $Dest -UseBasicParsing -ErrorAction Stop
        C-Success "  -> $Dest"
    } catch {
        Remove-Item $Dest -ErrorAction SilentlyContinue
        Abort "Failed to download $Label from $Url. Manual option: download it yourself and save to $Dest"
    }
}

function Ensure-Tools {
    if (-not (Test-Path $ToolsDir)) { New-Item -ItemType Directory -Force -Path $ToolsDir | Out-Null }
    if (-not (Test-Path $JUnitJar)) { Download-File $JUnitUrl $JUnitJar "JUnit console launcher 1.10.0" }
}

# ============================================================ folder helpers

function Get-LabDir {
    param($N)
    $dir = Get-ChildItem -Path $RootDir -Directory |
           Where-Object { $_.Name -match "^0*$N-([a-z0-9\-]+)$" } |
           Select-Object -First 1
    if (-not $dir) { Abort "Lab $N not found in $RootDir" }
    return $dir.FullName
}

function Get-LevelDir {
    param($Lab, $Level)
    $labDir = Get-LabDir $Lab
    $dir = Get-ChildItem -Path $labDir -Directory |
           Where-Object { $_.Name -match "^level-0*$Level$" } |
           Select-Object -First 1
    if (-not $dir) { Abort "Level $Level not found in $labDir" }
    return $dir.FullName
}

function Get-ProblemDir {
    param($Lab, $Level, $Problem)
    $levelDir = Get-LevelDir $Lab $Level
    $dir = Get-ChildItem -Path $levelDir -Directory |
           Where-Object { $_.Name -match "^problem-0*$Problem-" } |
           Select-Object -First 1
    if (-not $dir) { Abort "Problem $Problem not found in $levelDir" }
    return $dir.FullName
}

function Get-AvailableLabs {
    @(Get-ChildItem -Path $RootDir -Directory |
        Where-Object { $_.Name -match '^\d{2}-' } |
        ForEach-Object { [int]($_.Name -replace '^(\d+)-.*', '$1') } |
        Sort-Object)
}

function Get-AvailableLevels {
    param($Lab)
    @(Get-ChildItem -Path (Get-LabDir $Lab) -Directory |
        Where-Object { $_.Name -match '^level-\d+$' } |
        ForEach-Object { [int]($_.Name -replace '^level-(\d+).*', '$1') } |
        Sort-Object)
}

function Get-AvailableProblems {
    param($Lab, $Level)
    @(Get-ChildItem -Path (Get-LevelDir $Lab $Level) -Directory |
        Where-Object { $_.Name -match '^problem-\d+-' } |
        Sort-Object Name)
}

# ------------------------------------------------------------ title helpers

function Get-TitleFromReadme {
    param([string]$ReadmePath, [string]$PrefixPattern, [string]$Fallback)
    if (Test-Path $ReadmePath) {
        $raw = Read-FileUtf8 $ReadmePath
        if ($raw) {
            if ($raw -match '(?m)^#\s+(.+)$') {
                $title = ConvertTo-AsciiSafe $matches[1]
                if ($PrefixPattern -and $title -match $PrefixPattern) {
                    $title = ($title -replace $PrefixPattern, '').Trim()
                }
                if ($title -and $title.Length -le 45) { return $title }
            }
        }
    }
    return $Fallback
}

function Get-SlugTitle {
    param([string]$Slug, [string]$PrefixPattern)
    if (-not $Slug) { return "" }
    $s = $Slug -replace $PrefixPattern, ''
    return (($s -split '-' | ForEach-Object {
        if ($_.Length -gt 0) { $_.Substring(0,1).ToUpper() + $_.Substring(1) }
    }) -join ' ')
}

function Get-LabTitle {
    param([int]$N)
    $labDir = Get-LabDir $N
    $fallback = Get-SlugTitle (Split-Path $labDir -Leaf) '^\d+-'
    $title = Get-TitleFromReadme (Join-Path $labDir "README.md") `
        '^Lab\s+\d+\s*[\-\u2014\u2013\u00b7:]\s*' $fallback
    if ($title -match '^\s*Lab\s+\d+') { return $fallback }
    return $title
}

function Get-LevelTitle {
    param([int]$Lab, [int]$Level)
    $ld = Get-LevelDir $Lab $Level
    $fallback = "Level $Level"
    $title = Get-TitleFromReadme (Join-Path $ld "README.md") `
        '^Level\s+\d+\s*[\-\u2014\u2013\u00b7:]\s*' $fallback
    if ($title -match '^\s*Level\s+\d+') { return $fallback }
    return $title
}

function Get-ProblemTitle {
    param($ProblemDir)
    $fallback = Get-SlugTitle (Split-Path $ProblemDir -Leaf) '^problem-\d+-'
    $title = Get-TitleFromReadme (Join-Path $ProblemDir "README.md") `
        '^Problem\s+\d+\s*[\-\u2014\u2013\u00b7:]\s*' $fallback
    if ($title -match '^\s*Problem\s+\d+') { return $fallback }
    return $title
}

function Write-MenuRow {
    param([int]$Index, [string]$Title, [string]$Info, [int]$Width = 30)
    $num = "{0,2}" -f $Index
    $display = if ($Title.Length -ge $Width) {
        $Title.Substring(0, [Math]::Max(0, $Width - 3)) + "..."
    } else {
        $Title
    }
    $padded = $display.PadRight($Width)

    if (-not $NoColor) {
        Write-Host "    $num   " -ForegroundColor Cyan -NoNewline
        Write-Host "$padded " -ForegroundColor White -NoNewline
        if ($Info) { Write-Host $Info -ForegroundColor DarkGray }
        else        { Write-Host "" }
    } else {
        Write-Host ("    $num   $padded " + $Info)
    }
}

# ============================================================ compile & run

function New-BuildDir {
    $d = Join-Path $env:TEMP ("chirp-build-" + [Guid]::NewGuid().ToString("N").Substring(0, 8))
    New-Item -ItemType Directory -Path $d -Force | Out-Null
    return $d
}

function Compile-To {
    param($SourceDir, $BuildDir, $ClassPath)
    $files = @(Get-ChildItem -Path $SourceDir -Filter *.java -Recurse -File -ErrorAction SilentlyContinue)
    if ($files.Count -eq 0) { return $false }
    $javacArgs = @("-cp", $ClassPath, "-d", $BuildDir) + ($files | ForEach-Object { $_.FullName })

    $r = Run-Process "javac" $javacArgs -Capture

    if ($r.ExitCode -ne 0) {
        C-Error ""
        C-Error "Compilation failed:"
        foreach ($line in ($r.Combined -split "`r?`n")) {
            if ($line) { C-Error ("  " + $line) }
        }
        throw "Compilation failed in $SourceDir"
    }
    return $true
}

function Find-MainClass {
    param($SourceDir)
    $files = Get-ChildItem -Path $SourceDir -Filter *.java -Recurse -File
    foreach ($f in $files) {
        $content = Read-FileUtf8 $f.FullName
        if ($content -and $content -match 'public\s+static\s+void\s+main\s*\(\s*String\s*\[\s*\]') {
            $package = ""
            if ($content -match 'package\s+([\w\.]+)\s*;') { $package = $matches[1] + "." }
            if ($f.Name -match '^(\w+)\.java$') { return $package + $matches[1] }
        }
    }
    return $null
}

function Run-Implementation {
    param($SourceDir, $Label)
    $buildDir = New-BuildDir
    try {
        C-Head "Running $Label"
        $compiled = Compile-To $SourceDir $buildDir $ChirpJar
        if (-not $compiled) { C-Warn "No .java files in $SourceDir - nothing to run."; return }
        $mainClass = Find-MainClass $SourceDir
        if (-not $mainClass) { C-Warn "No class with a main method found in $SourceDir"; return }
        C-Dim "Entry point: $mainClass"
        Write-Host ""

        $r = Run-Process "java" @("-cp", "$ChirpJar$ClassSep$buildDir", $mainClass)

        if ($r.ExitCode -ne 0) { C-Warn "Program exited with code $($r.ExitCode)" }
    } finally {
        if (Test-Path $buildDir) { Remove-Item -Recurse -Force $buildDir -ErrorAction SilentlyContinue }
    }
}

function Test-Implementation {
    param($SourceDir, $TestDir, $Label)
    $buildDir = New-BuildDir
    try {
        C-Head "Testing $Label"
        $cp = "$ChirpJar$ClassSep$JUnitJar"
        $compiled = Compile-To $SourceDir $buildDir $cp
        if (-not $compiled) { C-Warn "No .java files in $SourceDir - nothing to test."; return $true }

        $testCp = "$cp$ClassSep$buildDir"
        $testCompiled = Compile-To $TestDir $buildDir $testCp
        if (-not $testCompiled) { Abort "No test files in $TestDir" }

        Write-Host ""

        $r = Run-Process "java" @(
            "-jar", $JUnitJar,
            "execute",
            "--class-path", "$cp$ClassSep$buildDir",
            "--scan-class-path",
            "--details=tree"
        )

        return ($r.ExitCode -eq 0)
    } finally {
        if (Test-Path $buildDir) { Remove-Item -Recurse -Force $buildDir -ErrorAction SilentlyContinue }
    }
}

# ============================================================ workspace init

# Creates workspace/ from starter/ if it does not exist.
# REFUSES if workspace already exists - prevents accidental overwriting of
# student work. If a student genuinely wants to start over, they delete the
# folder manually and re-run init.
function Init-Workspace {
    param($ProblemDir)
    $workspace = Join-Path $ProblemDir "workspace"
    $starter   = Join-Path $ProblemDir "starter"

    if (Test-Path $workspace) {
        Abort @"
Workspace already exists at:
  $workspace

  This problem has already been initialised. The workspace is safe -
  it was not touched.

  To work on it: run 'run' or 'test'.
  To reset it (destroys your work): delete the workspace folder and re-run 'init'.
"@
    }
    if (-not (Test-Path $starter)) {
        Abort "No starter/ folder in $ProblemDir - cannot initialise."
    }

    C-Info "Creating workspace from starter..."
    Copy-Item -Path $starter -Destination $workspace -Recurse -Force
    C-Success "Workspace created: $workspace"
    C-Dim "  Edit the .java files, then run 'run' or 'test'."
    return $workspace
}

# Ensures the workspace exists; aborts with a clear "run init first" if not.
function Assert-WorkspaceExists {
    param($ProblemDir)
    $workspace = Join-Path $ProblemDir "workspace"
    if (-not (Test-Path $workspace)) {
        Abort @"
Workspace not initialised.

  Run this first:
    ./run.ps1 -l $script:Lab -v $script:Level -p $script:Problem -o init

  Or interactively pick 'init' at the operation step.

  The workspace is where you write your code - it must exist before
  'run' or 'test' can do anything.
"@
    }
    return $workspace
}

# ============================================================ pack

function Pack-Workspace {
    param($Targets, $Label, $ZipBaseName)
    if (-not (Test-Path $DistDir)) { New-Item -ItemType Directory -Force -Path $DistDir | Out-Null }
    $ts  = Get-Date -Format "yyyyMMdd-HHmmss"
    $zip = Join-Path $DistDir "$ZipBaseName-$ts.zip"

    $stage = New-Item -ItemType Directory -Path (Join-Path $env:TEMP "chirp-pack-$ts") -Force
    try {
        foreach ($entry in $Targets) {
            $rel  = $entry.Relative
            $src  = $entry.FullPath
            $dest = Join-Path $stage $rel
            New-Item -ItemType Directory -Force -Path (Split-Path -Parent $dest) | Out-Null
            Copy-Item -Path $src -Destination $dest -Recurse -Force
        }

        $now = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
        $contents = ($Targets | ForEach-Object { "  $($_.Relative)" }) -join "`n"
        $manifest = @"
CHIRP OOP LABS - SUBMISSION MANIFEST
======================================
Label:     $Label
Packed:    $now

Contents
--------
$contents
"@
        Set-Content -Path (Join-Path $stage "MANIFEST.txt") -Value $manifest
        Compress-Archive -Path (Join-Path $stage "*") -DestinationPath $zip -Force
        C-Success "Packed -> $zip"
    } finally {
        if (Test-Path $stage) { Remove-Item -Recurse -Force $stage -ErrorAction SilentlyContinue }
    }
}

function Pack-Operation {
    param($Lab, $Level, $Problem)

    $targets = New-Object System.Collections.ArrayList

    if ($Problem -gt 0) {
        $pd = Get-ProblemDir $Lab $Level $Problem
        $ws = Join-Path $pd "workspace"
        if (-not (Test-Path $ws)) {
            Abort @"
Workspace not initialised for lab $Lab / level $Level / problem $Problem.

  Run 'init' first:
    ./run.ps1 -l $Lab -v $Level -p $Problem -o init

  Nothing to pack until you have a workspace.
"@
        }
        [void]$targets.Add([PSCustomObject]@{
            Relative = "lab$Lab-level$Level-problem$Problem/workspace"
            FullPath = $ws
        })
        Pack-Workspace $targets "Lab $Lab / Level $Level / Problem $Problem" "lab$Lab-level$Level-problem$Problem"
    }
    elseif ($Level -gt 0) {
        $ld = Get-LevelDir $Lab $Level
        foreach ($pd in Get-ChildItem -Path $ld -Directory | Where-Object { $_.Name -match '^problem-\d+-' }) {
            $ws = Join-Path $pd.FullName "workspace"
            if (Test-Path $ws) {
                [void]$targets.Add([PSCustomObject]@{
                    Relative = "$($pd.Name)/workspace"
                    FullPath = $ws
                })
            }
        }
        if ($targets.Count -eq 0) { C-Warn "No workspaces found in level $Level - run 'init' on at least one problem first."; return }
        Pack-Workspace $targets "Lab $Lab / Level $Level" "lab$Lab-level$Level"
    }
    elseif ($Lab -gt 0) {
        $labDir = Get-LabDir $Lab
        foreach ($ld in Get-ChildItem -Path $labDir -Directory | Where-Object { $_.Name -match '^level-\d+$' }) {
            foreach ($pd in Get-ChildItem -Path $ld.FullName -Directory | Where-Object { $_.Name -match '^problem-\d+-' }) {
                $ws = Join-Path $pd.FullName "workspace"
                if (Test-Path $ws) {
                    [void]$targets.Add([PSCustomObject]@{
                        Relative = "$($ld.Name)/$($pd.Name)/workspace"
                        FullPath = $ws
                    })
                }
            }
        }
        if ($targets.Count -eq 0) { C-Warn "No workspaces found in lab $Lab - run 'init' on at least one problem first."; return }
        Pack-Workspace $targets "Lab $Lab" "lab$Lab"
    }
    else {
        Abort "pack requires at least -Lab"
    }
}

# ============================================================ interactive

# Reads a number from the user.
#
# Two modes:
#   -Max N       - accepts any integer in [1..N]         (contiguous range)
#   -Valid array - accepts any integer present in array  (explicit set)
#
# With -AllowBack, "b" / "back" / ".." returns -1.
# "q" / "quit" exits. Read-Host appends ": " so labels must not end with one.
function Read-MenuChoice {
    param(
        [string]$Label,
        [int]$Max = 0,
        [int[]]$Valid = @(),
        [switch]$AllowBack
    )

    $useList = $Valid.Count -gt 0
    $rangeLabel = if ($useList) {
        if ($Valid.Count -eq 1) {
            "$($Valid[0])"
        } elseif (($Valid.Count -ge 2) -and (($Valid[-1] - $Valid[0] + 1) -eq $Valid.Count)) {
            "$($Valid[0])-$($Valid[-1])"
        } else {
            ($Valid -join ", ")
        }
    } else {
        "1-$Max"
    }

    while ($true) {
        Write-Host ""
        $hint = if ($AllowBack) { "$rangeLabel, b=back, q=quit" } else { "$rangeLabel, q=quit" }
        C-Dim "  ($hint)"
        $raw = (Read-Host "  $Label").Trim().ToLower()
        if ($raw -eq "q" -or $raw -eq "quit") {
            Write-Host ""
            C-Dim "  Cancelled."
            exit 0
        }
        if ($AllowBack -and ($raw -eq "b" -or $raw -eq "back" -or $raw -eq "..")) {
            return -1
        }
        $n = 0
        if ([int]::TryParse($raw, [ref]$n)) {
            if ($useList -and ($Valid -contains $n)) { return $n }
            if (-not $useList -and $n -ge 1 -and $n -le $Max) { return $n }
        }
        C-Error "  Invalid - enter $rangeLabel"
    }
}

# Prints a bordered "context panel" containing:
#   - environment summary (Java version, lab/problem counts)
#   - the breadcrumb trail of user choices, if any have been made
# Uses the same 56-char inner width as the banner for visual alignment.
# A horizontal divider separates summary (static) from breadcrumb (dynamic).
function Show-ContextPanel {
    $w = 56

    $unicode = $true
    try {
        $cp = [Console]::OutputEncoding.CodePage
        $unicode = ($cp -eq 65001 -or $cp -eq 1200)
    } catch { $unicode = $false }

    $tl  = if ($unicode) { [char]0x256D } else { '+' }
    $tr  = if ($unicode) { [char]0x256E } else { '+' }
    $bl  = if ($unicode) { [char]0x2570 } else { '+' }
    $br  = if ($unicode) { [char]0x256F } else { '+' }
    $h   = if ($unicode) { [char]0x2500 } else { '-' }
    $v   = if ($unicode) { [char]0x2502 } else { '|' }
    $tm  = if ($unicode) { [char]0x251C } else { '+' }
    $bm  = if ($unicode) { [char]0x2524 } else { '+' }
    $mid = if ($unicode) { [char]0x00B7 } else { '*' }
    $sep = if ($unicode) { [char]0x203A } else { '>' }

    $javaMajor = $script:JavaMajor
    $labCount  = $script:LabCount
    $prCount   = $script:ProblemCount
    if (-not $javaMajor -or $javaMajor -le 0) { return }

    $labWord = "lab" + $(if ($labCount -ne 1) { "s" } else { "" })
    $prWord  = "problem" + $(if ($prCount -ne 1) { "s" } else { "" })
    $summaryText = "  Java $javaMajor  $mid  $labCount $labWord  $mid  $prCount $prWord"

    $items = @()
    if ($script:Lab -ne 0)     { $items += @{ Text = "Lab $($script:Lab)";         IsOp = $false } }
    if ($script:Level -ne 0)   { $items += @{ Text = "Level $($script:Level)";     IsOp = $false } }
    if ($script:Problem -ne 0) { $items += @{ Text = "Problem $($script:Problem)"; IsOp = $false } }
    if (-not [string]::IsNullOrWhiteSpace($script:Operation)) {
        $items += @{ Text = $script:Operation; IsOp = $true }
    }
    $hasBreadcrumb = ($items.Count -gt 0)

    $breadcrumbText = ""
    if ($hasBreadcrumb) {
        $breadcrumbText = "  " + (($items | ForEach-Object { $_.Text }) -join "  $sep  ")
        if ($breadcrumbText.Length -gt $w) {
            $breadcrumbText = $breadcrumbText.Substring(0, $w - 3) + "..."
        }
    }

    $top     = "  $tl" + ("$h" * $w) + "$tr"
    $bot     = "  $bl" + ("$h" * $w) + "$br"
    $divider = "  $tm" + ("$h" * $w) + "$bm"

    if ($NoColor) {
        Write-Host ""
        Write-Host $top
        $pad1 = [Math]::Max(0, $w - $summaryText.Length)
        Write-Host ("  $v" + $summaryText + (" " * $pad1) + "$v")
        if ($hasBreadcrumb) {
            Write-Host $divider
            $pad2 = [Math]::Max(0, $w - $breadcrumbText.Length)
            Write-Host ("  $v" + $breadcrumbText + (" " * $pad2) + "$v")
        }
        Write-Host $bot
        return
    }

    Write-Host ""
    Write-Host $top -ForegroundColor DarkGray

    $pad1 = [Math]::Max(0, $w - $summaryText.Length)
    Write-Host "  $v" -ForegroundColor DarkGray -NoNewline
    Write-Host "  Java $javaMajor" -ForegroundColor Green -NoNewline
    Write-Host "  $mid  " -ForegroundColor DarkGray -NoNewline
    Write-Host "$labCount $labWord" -ForegroundColor Cyan -NoNewline
    Write-Host "  $mid  " -ForegroundColor DarkGray -NoNewline
    Write-Host "$prCount $prWord" -ForegroundColor Cyan -NoNewline
    Write-Host (" " * $pad1) -NoNewline
    Write-Host "$v" -ForegroundColor DarkGray

    if ($hasBreadcrumb) {
        Write-Host $divider -ForegroundColor DarkGray
        $pad2 = [Math]::Max(0, $w - $breadcrumbText.Length)
        Write-Host "  $v" -ForegroundColor DarkGray -NoNewline
        Write-Host "  " -NoNewline
        for ($i = 0; $i -lt $items.Count; $i++) {
            if ($i -gt 0) { Write-Host "  $sep  " -ForegroundColor DarkGray -NoNewline }
            $color = if ($items[$i].IsOp) { "Magenta" } else { "Cyan" }
            Write-Host $items[$i].Text -ForegroundColor $color -NoNewline
        }
        Write-Host (" " * $pad2) -NoNewline
        Write-Host "$v" -ForegroundColor DarkGray
    }

    Write-Host $bot -ForegroundColor DarkGray
}

function Interactive-Resolve {
    # Determine which step to start at.
    #   1=Lab 2=Level 3=Problem 4=Operation 5=Summary
    $step = 1
    if ($script:Lab -ne 0)     { $step = 2 }
    if ($script:Level -ne 0)   { $step = 3 }
    if ($script:Problem -ne 0) { $step = 4 }
    if (-not [string]::IsNullOrWhiteSpace($script:Operation)) { $step = 5 }

    $maxStep = if ($Yes) { 4 } else { 5 }
    $isFirstLoop = $true

    # IMPORTANT: use if/elseif, NOT switch. In PowerShell, `continue` inside
    # a `switch` only exits the switch - it does NOT skip $step++ at the
    # bottom of the enclosing while. Using if/elseif, `continue` correctly
    # skips $step++ and restarts the while with the new step value.
    while ($step -ge 1 -and $step -le $maxStep) {
        $isInitialRender = $isFirstLoop
        $isFirstLoop = $false

        if (-not $isInitialRender) {
            if (-not $NoClear) { try { Clear-Host } catch {} }
            Show-Banner -JavaReq $RequiredJava -ChirpVersion $ChirpVersion
        }
        Show-ContextPanel

        # ---------------------------------------------------------- STEP 1: LAB
        if ($step -eq 1) {
            $labs = @(Get-AvailableLabs)
            C-Head "STEP 1 of 4 - LAB"
            Write-Host ""
            for ($i = 0; $i -lt $labs.Count; $i++) {
                $lab = $labs[$i]
                $title = Get-LabTitle $lab
                $lvls = @(Get-AvailableLevels $lab)
                $prCount = 0
                foreach ($lv in $lvls) { $prCount += @(Get-AvailableProblems $lab $lv).Count }
                $info = "$($lvls.Count) level$(if ($lvls.Count -ne 1) {'s'}), $prCount problem$(if ($prCount -ne 1) {'s'})"
                Write-MenuRow -Index ($i + 1) -Title $title -Info $info
            }

            if ($labs.Count -eq 1 -and $isInitialRender) {
                C-Dim "  Auto-selected the only lab."
                $script:Lab = $labs[0]
                $step++
                continue
            }

            $choice = Read-MenuChoice "Lab [1-$($labs.Count)]" -Max $labs.Count
            if ($choice -eq -1) { continue }
            $script:Lab = $labs[$choice - 1]
            $step++
            continue
        }

        # -------------------------------------------------------- STEP 2: LEVEL
        if ($step -eq 2) {
            $levels = @(Get-AvailableLevels $script:Lab)
            $labTitle = Get-LabTitle $script:Lab
            C-Head "STEP 2 of 4 - LEVEL - $labTitle"
            Write-Host ""
            for ($i = 0; $i -lt $levels.Count; $i++) {
                $lv = $levels[$i]
                $title = Get-LevelTitle $script:Lab $lv
                $prCount = @(Get-AvailableProblems $script:Lab $lv).Count
                $info = if ($prCount -eq 0) { "no problems yet" } else { "$prCount problem$(if ($prCount -ne 1) {'s'})" }
                Write-MenuRow -Index ($i + 1) -Title $title -Info $info
            }
            $choice = Read-MenuChoice "Level [1-$($levels.Count)]" -Max $levels.Count -AllowBack
            if ($choice -eq -1) {
                $script:Lab = 0
                $script:Level = 0
                $script:Problem = 0
                $script:Operation = ""
                $step = 1
                continue
            }
            $chosen = $levels[$choice - 1]
            if (@(Get-AvailableProblems $script:Lab $chosen).Count -eq 0) {
                C-Warn "  Level $chosen has no problems yet - try another."
                continue
            }
            $script:Level = $chosen
            $step++
            continue
        }

        # ------------------------------------------------------ STEP 3: PROBLEM
        if ($step -eq 3) {
            $probs = @(Get-AvailableProblems $script:Lab $script:Level)
            if ($probs.Count -eq 0) { Abort "No problems in level $($script:Level) yet." }
            $levelTitle = Get-LevelTitle $script:Lab $script:Level
            C-Head "STEP 3 of 4 - PROBLEM - $levelTitle"
            Write-Host ""

            $problemNumbers = @()
            for ($i = 0; $i -lt $probs.Count; $i++) {
                $folderName = $probs[$i].Name
                $actualNum = 0
                if ($folderName -match '^problem-0*(\d+)-') {
                    $actualNum = [int]$matches[1]
                    $problemNumbers += $actualNum
                }
                $title = Get-ProblemTitle $probs[$i].FullName
                Write-MenuRow -Index $actualNum -Title $title -Width 34
            }

            $choice = Read-MenuChoice "Problem" -Valid $problemNumbers -AllowBack
            if ($choice -eq -1) {
                $script:Level = 0
                $script:Problem = 0
                $script:Operation = ""
                $step = 2
                continue
            }
            $script:Problem = $choice
            $step++
            continue
        }

        # ---------------------------------------------------- STEP 4: OPERATION
        if ($step -eq 4) {
            $problemDir = Get-ProblemDir $script:Lab $script:Level $script:Problem
            $hasWorkspace = Test-Path (Join-Path $problemDir "workspace")

            C-Head "STEP 4 of 4 - OPERATION"
            Write-Host ""
            if (-not $hasWorkspace) {
                if (-not $NoColor) {
                    Write-Host "  Workspace not initialised yet - pick 'init' first." -ForegroundColor Yellow
                } else {
                    Write-Host "  Workspace not initialised yet - pick 'init' first."
                }
                Write-Host ""
            }

            $opList = @(
                @{ Key = "init";           Desc = "Create workspace from starter (once)" },
                @{ Key = "run";            Desc = "Compile & run your workspace" },
                @{ Key = "test";           Desc = "Compile & run JUnit tests" },
                @{ Key = "solution";       Desc = "Run the reference solution (no init)" },
                @{ Key = "run,test";       Desc = "Run, then test (asks first)" },
                @{ Key = "solution,test";  Desc = "Run reference, then test" },
                @{ Key = "pack";           Desc = "Zip your workspace for submission" }
            )
            for ($i = 0; $i -lt $opList.Count; $i++) {
                $op = $opList[$i]
                Write-MenuRow -Index ($i + 1) -Title $op.Key -Info $op.Desc -Width 16
            }
            $choice = Read-MenuChoice "Operation [1-$($opList.Count)]" -Max $opList.Count -AllowBack
            if ($choice -eq -1) {
                $script:Problem = 0
                $script:Operation = ""
                $step = 3
                continue
            }
            $script:Operation = $opList[$choice - 1].Key
            $step++
            continue
        }

        # ------------------------------------------------------ STEP 5: SUMMARY
        if ($step -eq 5) {
            $labTitle   = Get-LabTitle $script:Lab
            $levelTitle = Get-LevelTitle $script:Lab $script:Level
            $pd         = Get-ProblemDir $script:Lab $script:Level $script:Problem
            $probTitle  = Get-ProblemTitle $pd

            C-Head "SUMMARY - CONFIRM AND PROCEED"
            Write-Host ""
            Write-Host ("    Lab {0,-3}  {1}" -f $script:Lab, $labTitle)
            Write-Host ("    Level {0,-1}  {1}" -f $script:Level, $levelTitle)
            Write-Host ("    Problem {0}  {1}" -f $script:Problem, $probTitle)
            Write-Host ""
            if (-not $NoColor) {
                Write-Host ("    Operation: {0}" -f $script:Operation) -ForegroundColor Magenta
            } else {
                Write-Host ("    Operation: {0}" -f $script:Operation)
            }
            Write-Host ""

            $decision = $null
            while ($null -eq $decision) {
                C-Dim "  (enter=proceed, b=back to operation, n=cancel)"
                $answer = (Read-Host "  Proceed?").Trim().ToLower()
                if ($answer -eq "" -or $answer -eq "y" -or $answer -eq "yes") {
                    $decision = "proceed"
                } elseif ($answer -eq "n" -or $answer -eq "no") {
                    $decision = "cancel"
                } elseif ($answer -eq "b" -or $answer -eq "back") {
                    $decision = "back"
                } else {
                    C-Error "  Invalid - press Enter to proceed, b to go back, n to cancel"
                }
            }

            if ($decision -eq "cancel") {
                C-Warn "  Cancelled."
                exit 0
            }
            if ($decision -eq "back") {
                $script:Operation = ""
                $step = 4
                continue
            }
            $step++
            continue
        }

        Abort "Internal error: invalid wizard step $step"
    }
}

# ============================================================ dispatch

function Parse-Ops {
    param($Op)
    if ([string]::IsNullOrWhiteSpace($Op)) { return @() }
    $result = $Op -split '[,\s]+' | ForEach-Object { $_.Trim().ToLower() } | Where-Object { $_ }
    return @($result)
}

function Confirm-TestPhase {
    param($After)
    if ($Yes) { return $true }
    Write-Host ""
    C-Warn "About to run tests after '$After'."
    $answer = (Read-Host "Run tests now? [Y/n]").Trim().ToLower()
    # Empty input (bare Enter) counts as yes - tests should always follow a run
    if ([string]::IsNullOrEmpty($answer)) { return $true }
    return ($answer -match '^(y|yes)$')
}

function Execute-Problem {
    param($Lab, $Level, $Problem, $Ops)

    $problemDir = Get-ProblemDir $Lab $Level $Problem
    $solution   = Join-Path $problemDir "solution"
    $tests      = Join-Path $problemDir "tests"
    $workspace  = Join-Path $problemDir "workspace"

    C-Head "Lab $Lab / Level $Level / Problem $Problem"

    $hasInit     = $Ops -contains "init"
    $hasRun      = $Ops -contains "run"
    $hasSolution = $Ops -contains "solution"
    $hasTest     = $Ops -contains "test"

    # ---------------------------------------------------------- init (standalone)
    # init is a one-shot setup operation. It cannot be combined with other
    # operations because it changes the filesystem state that the other
    # operations depend on; forcing two invocations keeps the flow linear
    # and predictable.
    if ($hasInit) {
        if ($Ops.Count -gt 1) {
            Abort "Cannot combine 'init' with other operations.`n  Run 'init' first, then run/test/solution/pack separately."
        }
        [void](Init-Workspace $problemDir)
        return
    }

    # ------------------------------------------- operations needing a workspace
    # run, test, and pack all operate on the student's workspace. They must
    # not silently auto-initialise - the student must be told to run 'init'
    # first, so they are always aware of when a workspace was created.
    if ($hasRun -or $hasTest) {
        [void](Assert-WorkspaceExists $problemDir)
    }

    # ---------------------------------------------------- run / solution (+ test)
    if ($hasRun -or $hasSolution) {
        $sourceDir = if ($hasRun) { $workspace } else { $solution }
        $label     = if ($hasRun) { "your workspace" } else { "reference solution" }
        if (-not (Test-Path $sourceDir)) { Abort "Source directory missing: $sourceDir" }
        Run-Implementation $sourceDir $label
        if ($hasTest) {
            if (Confirm-TestPhase $label) {
                [void](Test-Implementation $sourceDir $tests $label)
            }
        }
        return
    }

    # ------------------------------------------------------------ test only
    if ($hasTest) {
        [void](Test-Implementation $workspace $tests "your workspace")
        return
    }

    Abort "Unknown operation combination: $($Ops -join ', ')"
}

# ============================================================ positional compatibility

if ($args.Count -gt 0) {
    $extra = ($args | ForEach-Object { [string]$_ }) -join ' '
    if ([string]::IsNullOrWhiteSpace($Operation)) {
        $Operation = $extra
    } else {
        $Operation = "$Operation $extra"
    }
}

# ============================================================ main

Ensure-Java
Update-ChirpLib -Force:$UpdateLib
Ensure-Tools

# -UpdateLib alone (no lab/level/problem/operation) -> refresh and exit
if ($UpdateLib -and $Lab -eq 0 -and $Level -eq 0 -and $Problem -eq 0 -and [string]::IsNullOrWhiteSpace($Operation)) {
    C-Success "Chirp library updated. Exiting."
    exit 0
}

# Cache environment facts once - used by Show-ContextPanel on every re-render
$script:JavaMajor = Get-JavaMajor
$script:LabCount = @(Get-AvailableLabs).Count
$script:ProblemCount = 0
foreach ($l in @(Get-AvailableLabs)) {
    foreach ($lv in @(Get-AvailableLevels $l)) {
        $script:ProblemCount += @(Get-AvailableProblems $l $lv).Count
    }
}

$needsInteractive = ($Lab -eq 0 -or $Level -eq 0 -or $Problem -eq 0 -or [string]::IsNullOrWhiteSpace($Operation))
if (-not $needsInteractive -and $script:JavaMajor -gt 0) { Show-ContextPanel }

if ($needsInteractive) {
    Interactive-Resolve
}

$ops = Parse-Ops $Operation
if ($ops.Count -eq 0) { Abort "No operation specified." }

if (($ops -contains "run") -and ($ops -contains "solution")) {
    Abort "Cannot combine 'run' with 'solution' - pick one."
}

if (($ops -contains "init") -and ($ops.Count -gt 1)) {
    Abort "Cannot combine 'init' with other operations.`n  Run 'init' first, then run/test/solution/pack separately."
}

if ($ops -contains "pack") {
    Pack-Operation $Lab $Level $Problem
    exit 0
}

Execute-Problem $Lab $Level $Problem $ops

Write-Host ""
C-Success "Done."