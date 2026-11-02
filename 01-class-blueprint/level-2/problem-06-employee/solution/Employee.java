import chirp.text.Check;

public class Employee {

    private String id = "";
    private String name = "";
    private double salary = 0.0;

    public String getId() { return id; }

    public void setId(String value) {
        if (Check.notBlank(value)) {
            this.id = value;
        }
    }

    public String getName() { return name; }

    public void setName(String value) {
        if (Check.isName(value)) {
            this.name = value;
        }
    }

    public double getSalary() { return salary; }

    public void setSalary(double value) {
        if (Check.inRange(value, 0.01, Double.MAX_VALUE)) {
            this.salary = value;
        }
    }

    public double annualSalary() {
        return salary * 12;
    }

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
