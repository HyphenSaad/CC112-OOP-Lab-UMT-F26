![Level 2](https://img.shields.io/badge/level-2_easy+-f59e0b?style=for-the-badge)
![Problem 10](https://img.shields.io/badge/problem-10-e11d48?style=for-the-badge)
![Tests](https://img.shields.io/badge/tests-7-25a162?style=for-the-badge&logo=junit5&logoColor=white)

# Problem 10 — Password

**Level:** 2 | **Lab:** Class Blueprint

## Context

A sign-up form needs to check password strength. The password itself must never be shown — only a masked version.

## UML

```mermaid
classDiagram
    class Password {
        -String value
        +getValue() String
        +setValue(String) void
        +length() int
        +hasDigit() boolean
        +isStrong() boolean
        +masked() String
        +isValid() boolean
    }
```

## Requirements

| Attribute | Type | Rule |
|---|---|---|
| value | String | not null (empty allowed to start) |

Default: `value = ""`.

### Behaviour

- `length()` returns `value.length()`.
- `hasDigit()` uses `Check.containsDigit(value)`.
- `isStrong()` returns `true` when `Check.minLength(value, 8)` **and** `hasDigit()`.
- `masked()` returns one `*` per character — using `Text.mask(value, 0)`.

## Sample interaction

```
Password: abc

Length:    3
Has digit: false
Is strong: false
Masked:    ***
```

## Chirp methods used

- [`Check.containsDigit(String)`](https://hyphensaad.github.io/chirp/javadoc/chirp/text/Check.html#containsDigit(java.lang.String))
- [`Check.minLength(String, int)`](https://hyphensaad.github.io/chirp/javadoc/chirp/text/Check.html#minLength(java.lang.String,int))
- [`Text.mask(String, int)`](https://hyphensaad.github.io/chirp/javadoc/chirp/text/Text.html#mask(java.lang.String,int))
- [`Chirp.readString(prompt).read()`](https://hyphensaad.github.io/chirp/javadoc/chirp/Chirp.html#readString())

## Tests to pass

- `freshPasswordIsValid`
- `setValueStoresCorrectly`
- `lengthReturnsCorrectValue`
- `hasDigitDetectsDigits`
- `isStrongRequiresLengthAndDigit`
- `maskedReturnsStarsOnly`
- `nullValueRejected`

## Files

`starter/` · `solution/` · `tests/`
