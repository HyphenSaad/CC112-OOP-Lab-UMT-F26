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

    public Student(Student other) {
        if (other != null) {
            this.name = other.name;
            this.age = other.age;
            this.gpa = other.gpa;
        }
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
