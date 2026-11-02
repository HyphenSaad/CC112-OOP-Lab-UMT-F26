// Problem 6 — Employee
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class Employee {

    private String id = "";
    private String name = "";
    private double salary = 0.0;

    public String getId() { return id; }

    public void setId(String value) {
        // TODO: use Check.notBlank(value)
    }

    public String getName() { return name; }

    public void setName(String value) {
        // TODO: use Check.isName(value)
    }

    public double getSalary() { return salary; }

    public void setSalary(double value) {
        // TODO: use Check.inRange(value, 0.01, Double.MAX_VALUE)
    }

    public double annualSalary() {
        // TODO: return salary * 12
        return 0;
    }

    public void giveRaise(double percent) {
        // TODO: if Check.inRange(percent, 0, 100), do salary += salary * percent / 100
    }

    public boolean isValid() {
        // TODO: id not blank AND name is valid AND salary > 0
        return false;
    }
}
