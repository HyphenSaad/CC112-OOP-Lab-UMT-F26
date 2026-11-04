![Level 1](https://img.shields.io/badge/level-1_easy-22c55e?style=for-the-badge)
![Problems](https://img.shields.io/badge/problems-5-8b5cf6?style=for-the-badge)

# Level 1 — Parameterized Constructors

**Goal:** replace setter-based creation with a parameterized constructor. The object is valid the moment it exists.

## The five problems

| # | Problem | Constructor | What's new vs Lab 1 |
|---|---|---|---|
| 1 | [Student](problem-01-student/) | `Student(name, age, gpa)` | No default constructor, no setters |
| 2 | [Rectangle](problem-02-rectangle/) | `Rectangle(length, width)` | Same pattern, geometry domain |
| 3 | [BankAccount](problem-03-bank-account/) | `BankAccount(accountNumber)` | Balance still starts at 0, changes only via `deposit`/`withdraw` |
| 4 | [Circle](problem-04-circle/) | `Circle(radius)` | One-field constructor |
| 5 | [Employee](problem-05-employee/) | `Employee(id, name, salary)` | Salary mutates via `giveRaise` — the constructor sets the starting value |

## Rules for every problem

- **No default constructor.** The parameterized constructor is the only way to create the object.
- **No setters.** Fields that never change after construction are read-only. Fields that change (like `balance`) change via dedicated behaviour methods, not naked setters.
- **`Check` validates inside the constructor.** Invalid values leave the field at its Java default.
- **`isValid()` reports the truth.**
- Demo uses `readNonEmpty(...)` / `readInt(...)` / `readDouble(...)` — valid input reaches the constructor every time.

## Run any problem

```bash
cd ../..
./run.ps1 -l 2 -v 1 -p 1 -o run,test
```
