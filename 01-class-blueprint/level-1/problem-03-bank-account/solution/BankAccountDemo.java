import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class BankAccountDemo {

    public static void main(String[] args) {
        start();

        Session.me()
                .name("Ayesha Khan")
                .rollNumber("2024-CS-001")
                .degree("BSCS")
                .batch("2024")
                .section("A");

        String accNo = readNonEmpty("Account number");

        BankAccount acc = new BankAccount();
        acc.setAccountNumber(accNo);

        double d1 = readDouble("Deposit 1").min(0.01).read();
        acc.deposit(d1);
        Console.println("Balance after deposit 1: " + rupees(acc.getBalance()));

        double d2 = readDouble("Deposit 2").min(0.01).read();
        acc.deposit(d2);
        Console.println("Balance after deposit 2: " + rupees(acc.getBalance()));

        double w1 = readDouble("Withdraw 1").min(0.01).read();
        boolean ok = acc.withdraw(w1);
        Console.println("Withdraw 1: " + (ok ? "accepted" : "rejected")
                + " — balance: " + rupees(acc.getBalance()));

        double before = acc.getBalance();
        acc.deposit(-100);
        if (acc.getBalance() == before) {
            Console.warn("deposit(-100) was rejected — balance is still " + rupees(before));
        }

        Console.println();
        Console.watermark();
    }
}
