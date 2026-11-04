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

        String accNo = readNonEmpty("Account number");

        BankAccount original = new BankAccount(accNo);
        Console.println();
        Console.println("Created original.");
        Console.println("Original balance: " + rupees(original.getBalance()));

        double dep1 = readDouble("Deposit to original", 0.01, Double.MAX_VALUE);
        original.deposit(dep1);
        Console.println("Original balance: " + rupees(original.getBalance()));

        BankAccount copy = new BankAccount(original);
        Console.println();
        Console.println("Copy created from original.");
        Console.println("Copy balance: " + rupees(copy.getBalance()));

        double dep2 = readDouble("Deposit again to original", 0.01, Double.MAX_VALUE);
        original.deposit(dep2);
        Console.println();
        Console.println("Original balance: " + rupees(original.getBalance()));
        Console.println("Copy balance: " + rupees(copy.getBalance()) + "   <-- independent!");

        Console.println();
        Console.watermark();
    }
}
