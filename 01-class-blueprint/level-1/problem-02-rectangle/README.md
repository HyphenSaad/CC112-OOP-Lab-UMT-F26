![Level 1](https://img.shields.io/badge/level-1_easy-22c55e?style=for-the-badge)
![Problem 2](https://img.shields.io/badge/problem-02-e11d48?style=for-the-badge)
![Tests](https://img.shields.io/badge/tests-7-25a162?style=for-the-badge&logo=junit5&logoColor=white)

# Problem 2 — Rectangle

**Level:** 1 | **Lab:** Class Blueprint

## Context

A small geometry tool. A `Rectangle` stores length and width, and reports its area, perimeter, and whether it is a square.

## UML

```mermaid
classDiagram
    class Rectangle {
        -double length
        -double width
        +getLength() double
        +setLength(double) void
        +getWidth() double
        +setWidth(double) void
        +area() double
        +perimeter() double
        +isSquare() boolean
        +isValid() boolean
    }
```

## Requirements

| Attribute | Type | Rule |
|---|---|---|
| length | double | > 0 |
| width | double | > 0 |

Defaults: `length = 1.0`, `width = 1.0`.

### Behaviour

- `area()` = `length * width`
- `perimeter()` = `2 * (length + width)`
- `isSquare()` = `length == width`

## Sample interaction

```
Length: 8
Width: 5

Area:      40.00
Perimeter: 26.00
Is square: false
```

## Chirp methods used

- [`Chirp.readDouble(prompt).min(...).read()`](https://hyphensaad.github.io/chirp/javadoc/chirp/Chirp.html#readDouble())
- [`Chirp.decimal(value, 2)`](https://hyphensaad.github.io/chirp/javadoc/chirp/Chirp.html#decimal(double,int))
- [`Check.inRange(double, double, double)`](https://hyphensaad.github.io/chirp/javadoc/chirp/text/Check.html#inRange(double,double,double))

## Tests to pass

- `validRectangleReportsIsValid`
- `areaIsCorrect` — 8×5 = 40
- `perimeterIsCorrect` — 2×(8+5) = 26
- `notASquareWhenLengthDiffersFromWidth`
- `squareWhenLengthEqualsWidth` — 8×8 is a square
- `zeroLengthRejected`
- `negativeWidthRejected`

## Files

`starter/` · `solution/` · `tests/`
