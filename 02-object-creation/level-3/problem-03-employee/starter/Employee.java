// Problem 3 — Employee (With or Without Department)
// Fill in both constructors. See README.md for requirements.

import chirp.text.Check;

public class Employee {

    private String id;
    private String name;
    private double salary;
    private String department;

    // Default department "General".
    public Employee(String id, String name, double salary) {
        // TODO: validate id, name, salary
        // TODO: this.department = "General";
    }

    // Explicit department.
    public Employee(String id, String name, double salary, String department) {
        // TODO: validate id, name, salary
        // TODO: if Check.notBlank(department) this.department = department
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }

    public double annualSalary() { return salary * 12; }

    public boolean isValid() {
        return Check.notBlank(id)
                && Check.isName(name)
                && Check.inRange(salary, 0.01, Double.MAX_VALUE)
                && Check.notBlank(department);
    }
}
