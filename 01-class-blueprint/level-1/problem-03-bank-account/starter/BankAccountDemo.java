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

        // ============================================================
        // START HERE — write the demo body below.
        // Your BankAccount class must expose: setAccountNumber,
        // getBalance, deposit, withdraw, isValid.
        // ============================================================
        // Step 1: Ask for the account number with readNonEmpty.
        
        
        // Step 2: Create a BankAccount and set the account number.
        
        
        // Step 3: Read a first deposit amount (min = 0.01) using
        //         readDouble, call acc.deposit(…), print
        //         "Balance after deposit 1: " + rupees(acc.getBalance()).
        
        
        // Step 4: Same for a second deposit — label the line
        //         "Balance after deposit 2: ".
        
        
        // Step 5: Read a withdrawal amount (min = 0.01), call
        //         acc.withdraw(…) which returns a boolean. Print
        //         "Withdraw 1: accepted — balance: …" or
        //         "Withdraw 1: rejected — balance: …" using rupees.
        
        
        // Step 6: Verify the safety net. Call acc.deposit(-100) — it
        //         must be rejected silently, so getBalance() is
        //         unchanged. If unchanged, warn the user.
        
        
        // ============================================================
        // END — the block below prints your signature and closes the demo.
        // ============================================================

        println();
        watermark();
    }
}
