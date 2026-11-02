import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class StudentDemo {

    public static void main(String[] args) {
        start();

        Session.me()
                .name("John Baba")
                .rollNumber("2024-CS-001")
                .degree("BSCS")
                .batch("2024")
                .section("A");

        if (!Session.me().isComplete()) {
            Console.warn("Session incomplete. Missing: " + Session.me().missingFields());
        }

        String name = readNonEmpty("Name");
        int age = readInt("Age", 1, 120);
        double gpa = readDouble("GPA", 0.0, 4.0);

        Student s = new Student();
        s.setName(name);
        s.setAge(age);
        s.setGpa(gpa);

        Console.println();
        Console.println(s.introduce());
        Console.println("Valid: " + s.isValid());

        int before = s.getAge();
        s.setAge(200);
        if (s.getAge() == before) {
            Console.warn("setAge(200) was rejected — age is still " + before);
        }

        String beforeName = s.getName();
        s.setName("Bad123Name");
        if (s.getName().equals(beforeName)) {
            Console.warn("setName(\"Bad123Name\") was rejected");
        }

        Console.println();
        Console.watermark();
    }
}
