// Problem 3 — Employee (Chaining + Cross-Field Rule)
// Refactor with this(...), then add the Manager cross-field rule.

import chirp.text.Check;

public class Employee {

    private String id;
    private String name;
    private double salary;
    private String department;

    // TODO: delegate via this(id, name, salary, "General");
    public Employee(String id, String name, double salary) {
    }

    // Canonical constructor.
    public Employee(String id, String name, double salary, String department) {
        if (Check.notBlank(id)) this.id = id;
        if (Check.isName(name)) this.name = name;
        if (Check.inRange(salary, 0.01, Double.MAX_VALUE)) this.salary = salary;

        // Individual validation first
        if (Check.notBlank(department)) this.department = department;

        // TODO: cross-field rule — if department is "Manager" but salary < 100000,
        //       reject by resetting department to null.
        //       Careful: only apply if department was already set.
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
