import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class WalletDemo {

    public static void main(String[] args) {
        start();

        Session.me()
                .name("Ayesha Khan")
                .rollNumber("2024-CS-001")
                .degree("BSCS")
                .batch("2024")
                .section("A");

        if (!Session.me().isComplete()) {
            Console.warn("Session incomplete. Missing: " + Session.me().missingFields());
        }

        Wallet wallet = new Wallet();

        Console.println("Start balance: " + rupees(wallet.getBalance()));
        Console.println("isEmpty? " + wallet.isEmpty());

        double amount1 = readDouble("Add amount").min(0.01).read();
        wallet.addMoney(amount1);
        Console.println("Balance after add: " + rupees(wallet.getBalance()));

        double amount2 = readDouble("Spend amount").min(0.01).read();
        boolean spent = wallet.spendMoney(amount2);
        Console.println("Spend: " + (spent ? "accepted" : "rejected")
                + " — balance: " + rupees(wallet.getBalance()));

        Console.println("canAfford(100)? " + wallet.canAfford(100));

        double before = wallet.getBalance();
        wallet.addMoney(-50);
        if (wallet.getBalance() == before) {
            Console.warn("addMoney(-50) was rejected — balance is still " + rupees(before));
        }

        Console.println();
        Console.watermark();
    }
}
