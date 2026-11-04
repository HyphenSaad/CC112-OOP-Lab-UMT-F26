// Problem 5 — Employee
// Fill in the constructor and methods below. See README.md for requirements.

import chirp.text.Check;

public class Employee {

    private String id;
    private String name;
    private double salary;

    public Employee(String id, String name, double salary) {
        // TODO: if Check.notBlank(id) -> this.id = id
        // TODO: if Check.isName(name) -> this.name = name
        // TODO: if Check.inRange(salary, 0.01, Double.MAX_VALUE) -> this.salary = salary
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    public double annualSalary() { return 0; /* TODO: salary * 12 */ }

    public void giveRaise(double percent) {
        // TODO: if Check.inRange(percent, 0, 100), salary += salary * percent / 100
    }

    public boolean isValid() { return false; /* TODO */ }
}
