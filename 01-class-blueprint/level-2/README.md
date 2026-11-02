![Level 2](https://img.shields.io/badge/level-2_easy+-f59e0b?style=for-the-badge)
![Problems](https://img.shields.io/badge/problems-5-8b5cf6?style=for-the-badge)

# Level 2 — Computing Answers

**Goal:** methods that take attributes as input and produce a computed result.

Rules from Level 1 still apply — private attributes, `Check`-validated setters with silent reject, `isValid()`, no exceptions. What is new: methods now perform a **calculation**, not just return a field.

## The five problems

| # | Problem | Attributes | Special methods |
|---|---|---|---|
| 6 | [Employee](problem-06-employee/) | id, name, salary | `annualSalary()`, `giveRaise(percent)` |
| 7 | [Temperature](problem-07-temperature/) | celsius | `toFahrenheit()`, `toKelvin()`, `isFreezing()`, `isBoiling()` |
| 8 | [Book](problem-08-book/) | title, author, pages | `isLong()`, `readingMinutes(secondsPerPage)` |
| 9 | [Wallet](problem-09-wallet/) | balance | `addMoney`, `spendMoney`, `canAfford`, `isEmpty` |
| 10 | [Password](problem-10-password/) | value | `length()`, `hasDigit()`, `isStrong()`, `masked()` |

## Run any problem

```bash
cd ..
./run.ps1 -Lab 1 -Level 2 -Problem 6 -Operation run,test
```

Or `./run.ps1` with no arguments for the interactive wizard.
