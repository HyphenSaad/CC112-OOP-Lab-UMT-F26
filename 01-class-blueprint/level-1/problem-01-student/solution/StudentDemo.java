import chirp.session.Session;
import static chirp.Chirp.*;

public class StudentDemo {

    public static void main(String[] args) {
        start();

        Session.me()
                .name("Your Name")
                .rollNumber("2024-CS-XXX")
                .degree("BSCS")
                .batch("2024")
                .section("A");

        if (!Session.me().isComplete()) {
            warn("Session incomplete. Missing: " + Session.me().missingFields());
        }

        String name = readNonEmpty("Name");
        int age = readInt("Age", 1, 120);
        double gpa = readDouble("GPA", 0.0, 4.0);

        Student s = new Student();
        s.setName(name);
        s.setAge(age);
        s.setGpa(gpa);

        println();
        println(s.introduce());
        println("Valid: " + s.isValid());

        int before = s.getAge();
        s.setAge(200);
        if (s.getAge() == before) {
            warn("setAge(200) was rejected — age is still " + before);
        }

        println();
        watermark();
    }
}
