![Level 4](https://img.shields.io/badge/level-4_hard-ef4444?style=for-the-badge)
![Problems](https://img.shields.io/badge/problems-5-8b5cf6?style=for-the-badge)

# Level 4 — Constructor Chaining & Complex Validation

**Goal:** eliminate the duplication from Level 3, and handle rules that span multiple fields.

## The five problems

| # | Problem | Teaching focus |
|---|---|---|
| 1 | [Rectangle](problem-01-rectangle/) | First `this(...)` chain — square delegates to rectangle |
| 2 | [BankAccount](problem-02-bank-account/) | Chaining for default values — `this(acc, 0)` |
| 3 | [Employee](problem-03-employee/) | Chaining + **cross-field rule** — "Manager" requires salary ≥ 100k |
| 4 | [Temperature](problem-04-temperature/) | **`this(...)` can't call a method first** — static helper workaround |
| 5 | [Discount](problem-05-discount/) | **Multi-field validation** — rule depends on `type` |

## The `this(...)` rule

A constructor that delegates to another constructor must do so **as the very first statement**:

```java
public Rectangle(double side) {
    this(side, side);   // first statement — mandatory
    // (nothing else before this line)
}
```

Why first? Because Java needs to know the object is fully initialized on the delegated path before it runs the rest of this constructor's body. If you put logic before `this(...)`, the compiler will refuse to compile.

## The three chaining shapes

**Shape 1 — delegating to fill a default:**
```java
public BankAccount(String acc) {
    this(acc, 0.0);
}
public BankAccount(String acc, double initialBalance) {
    // real validation here
}
```

**Shape 2 — delegating to remove duplicated validation:**
```java
public Rectangle(double side) {
    this(side, side);   // the real work is in the two-arg version
}
```

**Shape 3 — delegating to a computed value via static helper:**
```java
public Temperature(double value, String unit) {
    this(convertToCelsius(value, unit));   // static helper — because this(...) needs first statement
}
private static double convertToCelsius(double value, String unit) { ... }
```

Shape 3 is the odd one. You can't write `this(calculateConversion(value, unit))` where `calculateConversion` is an instance method — it must be `static` because no instance exists yet.

## Cross-field validation

Some rules can't be checked field-by-field. They live at the intersection of two or more fields:

```java
// Validating "Manager" alone is impossible without knowing salary
if ("Manager".equals(department) && salary < 100000) {
    // invalid
}
```

The pattern: validate individual fields first, then apply cross-field rules at the end. If a cross-field rule fails, leave one of the fields at its default so `isValid()` returns false.

## Rules for every problem

- **One canonical constructor** holds the real validation.
- **Every other constructor delegates** via `this(...)` as the first statement.
- **No duplicated `Check` calls** — they live in exactly one place.
- **Cross-field rules** run after the individual checks, using the now-validated fields.

## Before you start

This level is a **refactor + extension**. Problems 1–4 start from the Level 3 solutions and reduce them. Problem 5 is new — it introduces cross-field validation that none of the earlier levels needed.

Read each problem's starter carefully: for problems 1–4, the starter is the Level 3 solution with TODOs added. Your job is to *remove* the duplication.

## Run any problem

```bash
cd ../..
./run.ps1 -l 2 -v 4 -p 1 -o run,test
```
