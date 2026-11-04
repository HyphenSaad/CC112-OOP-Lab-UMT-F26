![Lab 2](https://img.shields.io/badge/lab-02_object_creation-e11d48?style=for-the-badge)
![Levels](https://img.shields.io/badge/levels-4-6366f1?style=for-the-badge)
![Problems](https://img.shields.io/badge/problems-20-8b5cf6?style=for-the-badge)
![Java](https://img.shields.io/badge/java-17-f89820?style=for-the-badge&logo=openjdk&logoColor=white)

# Lab 2 — Object Creation

Where objects are born, and where they're born *right*.

## What changes from Lab 1

In Lab 1, you built classes where you could `new Student()` and fill in fields via setters. That worked, but it has a flaw: **there's a window where the object exists but is invalid**.

```java
Student s = new Student();  // name = "", age = 0, gpa = 0 — invalid!
s.setName("John");         // still invalid — age and gpa out of range
s.setAge(20);                // still invalid — gpa out of range
s.setGpa(3.75);              // finally valid
```

Between the first line and the last, `s` is a broken object. If any code runs in that window, it sees bad data.

**Lab 2 closes that window with constructors.** A parameterized constructor takes all the data an object needs — so the object is valid the moment it exists, or it never exists at all.

```java
Student s = new Student("John", 20, 3.75);  // valid immediately
```

## The two big rules of this lab

### Rule 1 — No default constructor

If a class needs parameters to be meaningful, do **not** provide a `public Student()`. The parameterized constructor is the *only* way to create a `Student`.

### Rule 2 — No setters (usually)

If a field is set once in the constructor and never changes, remove its setter. You can't change it after creation — that's the point.

Exception: fields that *are* meant to change (`balance` in `BankAccount`, `salary` in `Employee`) still get mutation methods (`deposit`, `withdraw`, `giveRaise`). Just no naked setters.

## What about validation?

Setters used to validate silently. Constructors do the same:

```java
public Student(String name, int age, double gpa) {
    if (Check.isName(name))         this.name = name;
    if (Check.inRange(age, 1, 120)) this.age = age;
    if (Check.inRange(gpa, 0.0, 4.0)) this.gpa = gpa;
}
```

If any value is invalid, that field stays at its default (`null` for `String`, `0` for `int`, `0.0` for `double`). The object is then **invalid** — and `isValid()` returns `false`.

**The contract:** call `isValid()` before trusting an object built from untrusted input. Better still — use `readNonEmpty(...)` / `readInt(1, 120)` / `readDouble(0, 4)` to guarantee valid input in the first place, so `isValid()` is always `true`.

## Reference example — read this first

The `Student` class *without* a constructor (Lab 1) vs *with* one (Lab 2):

```java
// Lab 1 — setters only
public class Student {
    private String name = "";
    private int age = 1;
    private double gpa = 0.0;
    public void setName(String v) { if (Check.isName(v)) name = v; }
    public void setAge(int v) { if (Check.inRange(v, 1, 120)) age = v; }
    public void setGpa(double v) { if (Check.inRange(v, 0, 4)) gpa = v; }
    // ...
}

// Lab 2 — constructor only
public class Student {
    private String name;
    private int age;
    private double gpa;
    public Student(String name, int age, double gpa) {
        if (Check.isName(name))             this.name = name;
        if (Check.inRange(age, 1, 120))     this.age = age;
        if (Check.inRange(gpa, 0.0, 4.0))   this.gpa = gpa;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGpa() { return gpa; }
    // ...
}
```

Notice what disappeared: the empty-string defaults, the setters. What remains is **getters** (to read state) and any **behaviour** (like `introduce()`).

## The safety net still applies

Just like Lab 1, if the constructor receives bad data, the field stays at its Java default. `isValid()` catches it.

The demo proves it:

```java
Student broken = new Student("", 500, -1.0);
Console.warn("Constructed with bad data — isValid? " + broken.isValid());
// ⚠ Constructed with bad data — isValid? false
```

The object exists, but you know not to trust it.

## How to submit

- `workspace/` (created by the runner from `starter/`)
- PASS output of `./run.ps1 -l 2 -v 1 -p N -o test`
- A screenshot of `./run.ps1 -l 2 -v 1 -p N -o run`
- The UML for each problem

## The four levels

| Level | Badge | Focus |
|---|---|---|
| **1** | ![L1](https://img.shields.io/badge/level-1_easy-22c55e?style=flat-square) | Parameterized constructors — the big shift |
| **2** | ![L2](https://img.shields.io/badge/level-2_easy+-f59e0b?style=flat-square) | Copy constructors |
| **3** | ![L3](https://img.shields.io/badge/level-3_medium-f97316?style=flat-square) | Constructor overloading |
| **4** | ![L4](https://img.shields.io/badge/level-4_hard-ef4444?style=flat-square) | `this(...)` chaining + complex validation |

---

Go to [`level-1/`](level-1/) to begin.
