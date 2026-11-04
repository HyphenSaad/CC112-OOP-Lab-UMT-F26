import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class BankAccountDemo {

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

        Console.println("--- Open without deposit ---");
        String accNo1 = readNonEmpty("Account number");
        BankAccount acc1 = new BankAccount(accNo1);
        Console.println("Balance: " + rupees(acc1.getBalance()));

        Console.println();
        Console.println("--- Open with deposit ---");
        String accNo2 = readNonEmpty("Account number");
        double initial = readDouble("Initial balance", 0, Double.MAX_VALUE);
        BankAccount acc2 = new BankAccount(accNo2, initial);
        Console.println("Balance: " + rupees(acc2.getBalance()));

        Console.println();
        Console.watermark();
    }
}
