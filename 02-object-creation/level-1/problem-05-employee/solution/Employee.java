import chirp.text.Check;

public class Employee {

    private String id;
    private String name;
    private double salary;

    public Employee(String id, String name, double salary) {
        if (Check.notBlank(id)) this.id = id;
        if (Check.isName(name)) this.name = name;
        if (Check.inRange(salary, 0.01, Double.MAX_VALUE)) this.salary = salary;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    public double annualSalary() { return salary * 12; }

    public void giveRaise(double percent) {
        if (Check.inRange(percent, 0, 100)) {
            salary += salary * percent / 100;
        }
    }

    public boolean isValid() {
        return Check.notBlank(id)
                && Check.isName(name)
                && Check.inRange(salary, 0.01, Double.MAX_VALUE);
    }
}
