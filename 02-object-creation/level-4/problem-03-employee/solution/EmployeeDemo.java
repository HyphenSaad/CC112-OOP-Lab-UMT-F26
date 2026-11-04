import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class EmployeeDemo {

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

        Console.println("--- Valid Manager ---");
        String id1 = readNonEmpty("ID");
        String name1 = readNonEmpty("Name");
        double salary1 = readDouble("Salary", 0.01, Double.MAX_VALUE);
        Employee e1 = new Employee(id1, name1, salary1, "Manager");
        Console.println(e1.getName() + " (" + e1.getId() + "), dept="
                + e1.getDepartment() + ", salary " + rupees(e1.getSalary())
                + ", isValid " + e1.isValid());

        Console.println();
        Console.println("--- Manager with salary too low ---");
        String id2 = readNonEmpty("ID");
        String name2 = readNonEmpty("Name");
        double salary2 = readDouble("Salary", 0.01, 99999);
        Employee e2 = new Employee(id2, name2, salary2, "Manager");
        Console.println(e2.getName() + " (" + e2.getId() + "), dept="
                + e2.getDepartment() + ", isValid " + e2.isValid());

        Console.println();
        Console.println("--- Engineer with same salary ---");
        Employee e3 = new Employee(id2, name2, salary2, "Engineer");
        Console.println(e3.getName() + " (" + e3.getId() + "), dept="
                + e3.getDepartment() + ", isValid " + e3.isValid());

        Console.println();
        Console.watermark();
    }
}
