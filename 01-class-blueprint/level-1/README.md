![Level 1](https://img.shields.io/badge/level-1_easy-22c55e?style=for-the-badge)
![Problems](https://img.shields.io/badge/problems-5-8b5cf6?style=for-the-badge)

# Level 1 — Getting the Shape Right

**Goal:** create a class, add private attributes, write validating setters, read state via getters.

## The five problems

| # | Problem | Attributes | Special method |
|---|---|---|---|
| 1 | [Student](problem-01-student/) | name, age, gpa | `introduce()` |
| 2 | [Rectangle](problem-02-rectangle/) | length, width | `area()`, `perimeter()`, `isSquare()` |
| 3 | [BankAccount](problem-03-bank-account/) | accountNumber, balance | `deposit()`, `withdraw()` |
| 4 | [Circle](problem-04-circle/) | radius | `area()`, `circumference()`, `diameter()` |
| 5 | [LightBulb](problem-05-light-bulb/) | isOn | `turnOn()`, `turnOff()`, `toggle()` |

## Rules for every problem in this level

- All attributes `private`, all getters/setters `public`.
- Every setter uses `Check` and silently rejects invalid values.
- Every class has `isValid()`.
- Use the **default constructor** — no constructors yet (that is Lab 2).
- Demo uses `readInt` / `readDouble` / `readString` for input.
- Demo starts with `start()` + `Session.me(...)` and ends with `Console.watermark()`.

## Run any problem

```bash
cd ..
./run.ps1 -Lab 1 -Level 1 -Problem 1 -Operation run
./run.ps1 -Lab 1 -Level 1 -Problem 1 -Operation test
```

Or just `./run.ps1` (no arguments) for the interactive menu.
