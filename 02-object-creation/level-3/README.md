![Level 3](https://img.shields.io/badge/level-3_medium-f97316?style=for-the-badge)
![Problems](https://img.shields.io/badge/problems-5-8b5cf6?style=for-the-badge)

# Level 3 — Constructor Overloading

**Goal:** one class, multiple ways to be created — each producing a valid object.

## The five problems

| # | Problem | Overloads | Teaching focus |
|---|---|---|---|
| 1 | [Rectangle](problem-01-rectangle/) | `(side)` / `(length, width)` | Different arity, same type |
| 2 | [BankAccount](problem-02-bank-account/) | `(accountNumber)` / `(accountNumber, initialBalance)` | Different arity, different types |
| 3 | [Employee](problem-03-employee/) | `(id, name, salary)` / `(id, name, salary, department)` | Optional attribute |
| 4 | [Money](problem-04-money/) | `(rupees)` / `(rupees, paise)` | Same type, different arity |
| 5 | [Temperature](problem-05-temperature/) | `(celsius)` / `(value, unit)` | Different types, different meaning |

## The rule Java uses

Java picks the overload to call based on the **arguments you pass**. It considers:

- **Number** of arguments
- **Type** of each argument
- **Order** of arguments

**It never considers the return type.** Two constructors can't have the same signature and different return types — that wouldn't be an overload.

```java
new Rectangle(5.0)         // calls Rectangle(double side)
new Rectangle(8.0, 5.0)    // calls Rectangle(double length, double width)
```

## Rules for every problem

- Multiple constructors, all yielding **valid or invalid** — never undefined state.
- Each overload validates its own arguments with `Check` — invalid values leave fields at defaults.
- No constructor delegates to another yet — that's Level 4.
- All the duplication is deliberate — you'll see why in Level 4.

## What to notice

Read the solution for each problem carefully. Every overload repeats the same `if (Check.inRange(...))` block. That's ~3–5 lines duplicated per overload. When you're done with all five, you'll have written ~40 lines of duplicated validation across the level.

**Level 4 introduces `this(...)`** — a way to call one constructor from another. That's how the duplication gets fixed. But you can't appreciate the fix until you've felt the pain.

## Run any problem

```bash
cd ../..
./run.ps1 -l 2 -v 3 -p 1 -o run,test
```
