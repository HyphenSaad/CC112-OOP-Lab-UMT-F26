// Problem 1 — Student
// Fill in the constructor and methods below. See README.md for requirements.

import chirp.text.Check;

public class Student {

    private String name;
    private int age;
    private double gpa;

    // No default constructor. This is the only way to create a Student.
    public Student(String name, int age, double gpa) {
        // TODO: if Check.isName(name) -> this.name = name
        // TODO: if Check.inRange(age, 1, 120) -> this.age = age
        // TODO: if Check.inRange(gpa, 0.0, 4.0) -> this.gpa = gpa
    }

    public String getName() { return name; }

    public int getAge() { return age; }

    public double getGpa() { return gpa; }

    public String introduce() {
        // TODO: return "Hi, I'm {name}. I'm {age} years old with a GPA of {gpa}."
        // If name is null, use "?" instead.
        return "";
    }

    public boolean isValid() {
        // TODO: all three fields in range
        return false;
    }
}
