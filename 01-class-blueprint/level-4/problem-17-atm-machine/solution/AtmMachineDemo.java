import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class AtmMachineDemo {

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

        AtmMachine atm = new AtmMachine();
        Console.println("ATM PIN is 1234, balance " + rupees(atm.getBalance()));

        Console.println();
        for (int i = 1; i <= 3; i++) {
            String attempt = readString("Attempt " + i + " (wrong PIN)");
            boolean ok = atm.authenticate(attempt);
            Console.println("Attempt " + i + " -> " + ok + "  status: " + atm.getStatus());
            if (atm.isLocked()) break;
        }

        Console.println();
        Console.println("Locked? " + atm.isLocked());

        double before = atm.getBalance();
        atm.deposit(100);
        if (atm.getBalance() == before) {
            Console.warn("deposit(100) was rejected — ATM is locked");
        }

        Console.println();
        Console.watermark();
    }
}
