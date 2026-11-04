// Problem 1 — Student (Copy Constructor)
// Fill in the copy constructor. See README.md for requirements.

import chirp.text.Check;

public class Student {

    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
        if (Check.isName(name)) this.name = name;
        if (Check.inRange(age, 1, 120)) this.age = age;
        if (Check.inRange(gpa, 0.0, 4.0)) this.gpa = gpa;
    }

    // TODO: copy constructor — copy all three fields from `other`
    // If other is null, do nothing (fields keep their defaults).
    public Student(Student other) {
    }

    public String getName() { return name; }

    public int getAge() { return age; }

    public double getGpa() { return gpa; }

    public String introduce() {
        String safeName = name != null ? name : "?";
        return "Hi, I'm " + safeName + ". I'm " + age + " years old with a GPA of " + gpa + ".";
    }

    public boolean isValid() {
        return Check.isName(name)
                && Check.inRange(age, 1, 120)
                && Check.inRange(gpa, 0.0, 4.0);
    }
}
