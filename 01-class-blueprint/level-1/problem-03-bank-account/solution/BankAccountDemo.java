import chirp.session.Session;
import static chirp.Chirp.*;

public class BankAccountDemo {

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

        String accNo = readNonEmpty("Account number");

        BankAccount acc = new BankAccount();
        acc.setAccountNumber(accNo);

        double d1 = readDouble("Deposit 1", 0.01, Double.MAX_VALUE);
        acc.deposit(d1);
        println("Balance after deposit 1: " + rupees(acc.getBalance()));

        double d2 = readDouble("Deposit 2", 0.01, Double.MAX_VALUE);
        acc.deposit(d2);
        println("Balance after deposit 2: " + rupees(acc.getBalance()));

        double w1 = readDouble("Withdraw 1", 0.01, Double.MAX_VALUE);
        boolean ok = acc.withdraw(w1);
        println("Withdraw 1: " + (ok ? "accepted" : "rejected")
                + " — balance: " + rupees(acc.getBalance()));

        double before = acc.getBalance();
        acc.deposit(-100);
        if (acc.getBalance() == before) {
            warn("deposit(-100) was rejected — balance is still " + rupees(before));
        }

        println();
        watermark();
    }
}
