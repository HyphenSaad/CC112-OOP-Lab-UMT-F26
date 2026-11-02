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
        double salary = readDouble("Monthly salary").min(0.01).read();

        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        emp.setSalary(salary);

        Console.println();
        Console.println("Employee: " + emp.getName() + " (" + emp.getId() + ")");
        Console.println("Monthly: " + rupees(emp.getSalary()));
        Console.println("Annual:  " + rupees(emp.annualSalary()));

        double percent = readDouble("Raise (%)", 0, 100);
        emp.giveRaise(percent);
        Console.println("After " + decimal(percent, 1) + "% raise: " + rupees(emp.getSalary()));

        double before = emp.getSalary();
        emp.giveRaise(-5);
        if (emp.getSalary() == before) {
            Console.warn("giveRaise(-5) was rejected — salary is still " + rupees(before));
        }

        Console.println();
        Console.watermark();
    }
}
