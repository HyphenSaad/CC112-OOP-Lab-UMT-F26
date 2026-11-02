![Chirp](https://img.shields.io/badge/library-chirp-e11d48?style=for-the-badge&logo=java&logoColor=white)
![Java](https://img.shields.io/badge/java-17-f89820?style=for-the-badge&logo=openjdk&logoColor=white)
![JUnit](https://img.shields.io/badge/junit-5.10-25a162?style=for-the-badge&logo=junit5&logoColor=white)

# Chirp OOP Labs

Hands-on labs for the Object-Oriented Programming course. Each lab targets one cluster of OOP concepts and contains 20 problems spread across four difficulty levels.

## The four labs

| # | Lab | Focus | Status |
|---|---|---|---|
| **01** | **Class Blueprint** | class, attributes, methods, encapsulation, validation | ✅ Ready |
| 02 | Object Creation | constructors, parameterized, copy, overloads | 🚧 Next |
| 03 | Static, Instance, Overloading | static vs object members, method overloading | 🔜 Planned |
| 04 | Inheritance | single, multilevel, hierarchical, `super`, override | 🔜 Planned |

## The one command you need

```bash
# From the labs/ folder
./run.bat            # Windows (under development)
./run.sh             # macOS / Linux (under development)
./run.ps1            # Any platform (requires pwsh)
```

The script prompts for **lab → level → problem → operation**. If you know what you want, pass arguments directly:

```bash
./run.ps1 -Lab 1 -Level 1 -Problem 1 -Operation run,test
```

**Operations:** `run` · `test` · `solution` · `pack` · `run,test` · `solution,test`

## Folder layout of a lab

```
01-class-blueprint/
├── README.md                 ← lab intro, reference example, workflow
├── level-1/                  ← five problems: "getting the shape right"
│   ├── README.md             ← level intro + problem index
│   ├── problem-01-student/
│   │   ├── README.md         ← problem statement, UML, requirements
│   │   ├── starter/          ← skeleton to begin from (copied to workspace/ on first run)
│   │   ├── solution/         ← reference implementation
│   │   └── tests/            ← JUnit tests that must pass
│   ├── problem-02-rectangle/
│   └── ...
├── level-2/                  ← "computing answers"
├── level-3/                  ← "branching on state"
└── level-4/                  ← "mini systems"
```

The `workspace/` folder is created automatically on first `run` or `test` by copying `starter/`. **Never edit `starter/` — it is your starting point, not your work.** Your finished code lives in `workspace/`.

## What the runner does for you

On every run:

1. **Checks Java 17** — auto-installs via winget/brew/apt if missing
2. **Downloads prerequisites** — Chirp + JUnit jars into `.tools/`
3. **Creates your workspace** — copies `starter/` → `workspace/` on first use
4. **Compiles to a temp folder** — no `.class` files ever touch the project
5. **Cleans up on exit** — even on Ctrl+C, error, or `Abort`

## Folder structure at a glance

```
labs/
├── run.ps1                   ← the main runner 
├── .gitignore                ← ignores .tools/, dist/, workspace/, *.class
├── README.md                 ← this file
└── 01-class-blueprint/
    ├── README.md
    ├── level-1/…level-4/
    └── ...
```

## Prerequisites

- **PowerShell 7+** (`pwsh`) on macOS / Linux — the script tells you exactly how to install it if missing. Windows ships with PowerShell 5.1 which also works.
- **Java 17+** — the script installs it automatically if missing.

## The golden rule: validate — but never with exceptions

Every attribute is protected by a validation check. If an invalid value arrives at a setter, the setter **silently rejects** — the field keeps its previous value. No `throw`, no `try`/`catch`.

```java
public void setAge(int value) {
    if (Check.inRange(value, 1, 120)) {
        this.age = value;
    }
}
```

Two consequences:

1. The class never enters an invalid state.
2. Every other method can trust the attribute — no redundant checks.

### The safety net pattern

In demos, use Chirp's readers (`readInt`, `readString`, …) so the user can never type an invalid value. The setter's silent-reject is a safety net for programmatic misuse — you will test it in every demo by deliberately calling a setter with a bad value and observing the field did not move.

```java
int before = student.getAge();
student.setAge(200);
if (student.getAge() == before) {
    Console.warn("setAge(200) was rejected — age is still " + before);
}
```

No exception. Just an observation: the field did not move.

## Sign your work

Every demo starts with:

```java
start();
Session.me()
        .name("Your Name")
        .rollNumber("2024-CS-XXX")
        .degree("BSCS")
        .batch("2024")
        .section("A");
```

If any required field is missing, the demo warns you using `Session.missingFields()`. At the end, `Console.watermark()` prints your identity block — so every screenshot you submit carries your name.

## API reference

Every method you need is documented at **https://hyphensaad.github.io/chirp/**. Each problem's README lists the Chirp methods used, with a direct link.

## Get started

Open [`01-class-blueprint/`](01-class-blueprint/) and read the lab intro. Then run:

```bash
./run.ps1
```
