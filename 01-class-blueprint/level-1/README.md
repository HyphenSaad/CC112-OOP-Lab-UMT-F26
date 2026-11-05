![Level 1](https://img.shields.io/badge/level-1_easy-22c55e?style=for-the-badge)
![Problems](https://img.shields.io/badge/problems-5-8b5cf6?style=for-the-badge)

# Level 1 — Getting the Shape Right

**Goal:** create a class, add private attributes, write validating setters, read state via getters.

## The five problems

| # | Problem | Attributes | Focus |
|---|---|---|---|
| 1 | [Student](problem-01-student/) | name, age, gpa | Basic getters/setters with validation |
| 2 | [Rectangle](problem-02-rectangle/) | length, width | Computed values (area, perimeter) |
| 3 | [BankAccount](problem-03-bank-account/) | accountNumber, balance | State changed only via operations |
| 4 | [Circle](problem-04-circle/) | radius | Constants and Math.PI |
| 5 | [LightBulb](problem-05-light-bulb/) | isOn | Simplest possible state |

## What the starter contains

Each problem's `starter/` folder has **two files**, and **you write both**:

1. **`Xyz.java`** — ships as an empty `public class Xyz {}`. You add fields, getters, setters, and behaviour, guided by the README's UML and requirements.

2. **`XyzDemo.java`** — ships with the parts you do not modify (Session setup, completeness check, and the closing watermark) already filled in. Between the `START HERE` and `END` markers you will find a series of **guided comments** describing each step you must write.

**Read the demo first.** The guided comments tell you *what* the program must do at each step and *which Chirp method* to reach for. Then implement the class. Then fill in the demo body.

## The workflow

```bash
# 1. Create your workspace (once per problem)
./run.ps1 -l 1 -v 1 -p 1 -o init

# 2. Write both files under workspace/
#    - Xyz.java      (implement the class)
#    - XyzDemo.java  (write the main body, following the guided comments)

# 3. Run to see your code work
./run.ps1 -l 1 -v 1 -p 1 -o run

# 4. Test against the JUnit suite
./run.ps1 -l 1 -v 1 -p 1 -o test

# 5. Pack when all tests pass
./run.ps1 -l 1 -v 1 -p 1 -o pack
```

## Rules for every problem in this level

- All attributes `private`, getters/setters `public`.
- Every setter uses `Check` and **silently rejects** invalid values.
- Every class has `isValid()`.
- No direct constructor — the default no-arg constructor is used.
- Demo reads input with `readNonEmpty` / `readInt` / `readDouble`.
- Session signature at the top of every demo, watermark at the bottom.
