import chirp.text.Check;

public class Student {

    private String name = "";
    private int age = 1;
    private double gpa = 0.0;

    public String getName() { return name; }

    public void setName(String value) {
        if (Check.isName(value)) {
            this.name = value;
        }
    }

    public int getAge() { return age; }

    public void setAge(int value) {
        if (Check.inRange(value, 1, 120)) {
            this.age = value;
        }
    }

    public double getGpa() { return gpa; }

    public void setGpa(double value) {
        if (Check.inRange(value, 0.0, 4.0)) {
            this.gpa = value;
        }
    }

    public String introduce() {
        return "Hi, I'm " + name + ". I'm " + age + " years old with a GPA of " + gpa + ".";
    }

    public boolean isValid() {
        return Check.isName(name)
                && Check.inRange(age, 1, 120)
                && Check.inRange(gpa, 0.0, 4.0);
    }
}
