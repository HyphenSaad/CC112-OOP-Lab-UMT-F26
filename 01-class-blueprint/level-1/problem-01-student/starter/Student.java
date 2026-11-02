// Problem 1 — Student
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class Student {

    private String name = "";
    private int age = 1;
    private double gpa = 0.0;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        // TODO: use Check.isName(value)
        // If it passes, assign this.name = value
    }

    public int getAge() {
        return age;
    }

    public void setAge(int value) {
        // TODO: use Check.inRange(value, 1, 120)
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double value) {
        // TODO: use Check.inRange(value, 0.0, 4.0)
    }

    public String introduce() {
        // TODO: return "Hi, I'm {name}. I'm {age} years old with a GPA of {gpa}."
        return "";
    }

    public boolean isValid() {
        // TODO: true only when name, age, gpa are all in range
        return false;
    }
}
