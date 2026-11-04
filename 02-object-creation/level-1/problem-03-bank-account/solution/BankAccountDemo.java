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

        BankAccount acc = new BankAccount(accNo);

        Console.println();
        Console.println("Account: " + acc.getAccountNumber() + ", balance " + rupees(acc.getBalance()));

        double d1 = readDouble("Deposit", 0.01, Double.MAX_VALUE);
        acc.deposit(d1);
        Console.println("Balance: " + rupees(acc.getBalance()));

        double w1 = readDouble("Withdraw", 0.01, Double.MAX_VALUE);
        boolean ok = acc.withdraw(w1);
        Console.println("Withdraw: " + (ok ? "accepted" : "rejected")
                + " — balance " + rupees(acc.getBalance()));

        BankAccount broken = new BankAccount("");
        Console.warn("Constructed with bad data — isValid? " + broken.isValid());

        Console.println();
        Console.watermark();
    }
}
