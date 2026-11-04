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

        String id = readNonEmpty("Employee ID");
        String name = readNonEmpty("Name");
        double salary = readDouble("Monthly salary", 0.01, Double.MAX_VALUE);

        Employee emp = new Employee(id, name, salary);

        Console.println();
        Console.println("Employee: " + emp.getName() + " (" + emp.getId() + ")");
        Console.println("Monthly: " + rupees(emp.getSalary()));
        Console.println("Annual: " + rupees(emp.annualSalary()));

        double percent = readDouble("Raise %", 0, 100);
        emp.giveRaise(percent);
        Console.println("After " + decimal(percent, 1) + "% raise: " + rupees(emp.getSalary()));

        Employee broken = new Employee("", "Bad123Name", -500);
        Console.warn("Constructed with bad data — isValid? " + broken.isValid());

        Console.println();
        Console.watermark();
    }
}
