![Level 2](https://img.shields.io/badge/level-2_easy+-f59e0b?style=for-the-badge)
![Problems](https://img.shields.io/badge/problems-5-8b5cf6?style=for-the-badge)

# Level 2 — Copy Constructors

**Goal:** create a second object that starts with the same data as the first, but evolves independently.

## The five problems

| # | Problem | Copy constructor | What's new vs Level 1 |
|---|---|---|---|
| 1 | [Student](problem-01-student/) | `Student(Student other)` | First copy constructor — scalar fields |
| 2 | [Rectangle](problem-02-rectangle/) | `Rectangle(Rectangle other)` | Reinforces syntax |
| 3 | [BankAccount](problem-03-bank-account/) | `BankAccount(BankAccount other)` | Independence test — mutate copy, original stays put |
| 4 | [Playlist](problem-04-playlist/) | `Playlist(Playlist other)` | **First `List<String>` — shallow vs. deep copy** |
| 5 | [Inventory](problem-05-inventory/) | `Inventory(Inventory other)` | **Array + list together — both must be deep-copied** |

## Rules for every problem

- **Copy constructor signature:** `public ClassName(ClassName other)`
- **Null-safe:** if `other` is `null`, fields stay at their defaults.
- **Scalars are copied directly:** `this.name = other.name;`
- **Collections/arrays are deep-copied:** `this.songs = new ArrayList<>(other.songs);` and `this.quantities = other.quantities.clone();`
- **`isValid()` still applies** — a copy of a valid object should be valid; a copy of an invalid object should be invalid.

## The key demonstration in every demo

Every demo shows the same three-step proof of independence:

```java
Original original = new Original(...);
Original copy     = new Original(original);   // copy via copy ctor
// mutate ONE, then verify the OTHER is unchanged
original.mutateSomething();
Console.println("Copy still has old value: " + copy.getValue());
```

If the copy shows the mutation, the copy constructor is wrong. If it shows the old value, the copy is truly independent.

## Run any problem

```bash
cd ../..
./run.ps1 -l 2 -v 2 -p 1 -o run,test
```
