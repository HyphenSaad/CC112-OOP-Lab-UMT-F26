![Level 1](https://img.shields.io/badge/level-1_easy-22c55e?style=for-the-badge)
![Problem 4](https://img.shields.io/badge/problem-04-e11d48?style=for-the-badge)
![Tests](https://img.shields.io/badge/tests-7-25a162?style=for-the-badge&logo=junit5&logoColor=white)

# Problem 4 — Circle

**Level:** 1 | **Lab:** Class Blueprint

## Context

Another shape for the geometry tool. A `Circle` stores a radius and reports diameter, area, and circumference.

## UML

```mermaid
classDiagram
    class Circle {
        -double radius
        +getRadius() double
        +setRadius(double) void
        +area() double
        +circumference() double
        +diameter() double
        +isValid() boolean
    }
```

## Requirements

| Attribute | Type | Rule |
|---|---|---|
| radius | double | `> 0` |

Default: `radius = 1.0`.

### Behaviour

- `area()` = `Math.PI * radius * radius`
- `circumference()` = `2 * Math.PI * radius`
- `diameter()` = `2 * radius`

## Chirp methods used

| Method | What it does |
|---|---|
| `readDouble(prompt, min, max)` | Read a decimal; loops until in range |
| `println(value)` | Print a value followed by a newline |
| `warn(message)` | Print a yellow warning line |
| `decimal(value, places)` | Format a decimal to fixed places |
| `Check.inRange(value, min, max)` | Return `true` if `value` is within `[min, max]` |

## Sample interaction

```
Radius: 5

Diameter:      10.00
Area:          78.54
Circumference: 31.42
⚠ setRadius(-3) was rejected — radius is still 5.00
```

## Tests to pass

- `validCircleReportsIsValid`
- `diameterIsDoubleRadius`
- `areaIsPiRSquared`
- `circumferenceIs2PiR`
- `zeroRadiusRejected`
- `negativeRadiusRejected`
- `defaultRadiusIsOne`

## Files

`starter/` (Circle.java empty + demo guide) · `solution/` · `tests/`
