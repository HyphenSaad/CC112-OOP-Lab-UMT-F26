import chirp.text.Check;

public class Employee {

    private String id;
    private String name;
    private double salary;
    private String department;

    public Employee(String id, String name, double salary) {
        if (Check.notBlank(id)) this.id = id;
        if (Check.isName(name)) this.name = name;
        if (Check.inRange(salary, 0.01, Double.MAX_VALUE)) this.salary = salary;
        this.department = "General";
    }

    public Employee(String id, String name, double salary, String department) {
        if (Check.notBlank(id)) this.id = id;
        if (Check.isName(name)) this.name = name;
        if (Check.inRange(salary, 0.01, Double.MAX_VALUE)) this.salary = salary;
        if (Check.notBlank(department)) this.department = department;
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
