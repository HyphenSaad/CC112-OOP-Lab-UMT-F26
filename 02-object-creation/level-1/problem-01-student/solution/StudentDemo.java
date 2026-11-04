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

        Student s = new Student(name, age, gpa);

        Console.println();
        Console.println(s.introduce());
        Console.println("Valid: " + s.isValid());

        // Safety net — construct with bad data, observe the invalid object
        Student broken = new Student("", 500, -1.0);
        Console.warn("Constructed with bad data — isValid? " + broken.isValid());
        Console.println("  introduce() on invalid: " + broken.introduce());

        Console.println();
        Console.watermark();
    }
}
