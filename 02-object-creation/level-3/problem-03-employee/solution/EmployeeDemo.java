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

        Console.println("--- Employee without explicit department ---");
        String id1 = readNonEmpty("ID");
        String name1 = readNonEmpty("Name");
        double salary1 = readDouble("Salary", 0.01, Double.MAX_VALUE);
        Employee e1 = new Employee(id1, name1, salary1);
        Console.println("Employee: " + e1.getName() + " (" + e1.getId()
                + "), dept=" + e1.getDepartment() + ", annual=" + rupees(e1.annualSalary()));

        Console.println();
        Console.println("--- Employee with explicit department ---");
        String id2 = readNonEmpty("ID");
        String name2 = readNonEmpty("Name");
        double salary2 = readDouble("Salary", 0.01, Double.MAX_VALUE);
        String dept2 = readNonEmpty("Department");
        Employee e2 = new Employee(id2, name2, salary2, dept2);
        Console.println("Employee: " + e2.getName() + " (" + e2.getId()
                + "), dept=" + e2.getDepartment() + ", annual=" + rupees(e2.annualSalary()));

        Console.println();
        Console.watermark();
    }
}
