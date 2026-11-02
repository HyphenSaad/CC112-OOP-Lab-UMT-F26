// Problem 9 — Wallet
// Fill in the missing methods below. See README.md for requirements.

public class Wallet {

    private double balance = 0.0;

    public double getBalance() { return balance; }

    public void addMoney(double amount) {
        // TODO: if amount > 0, add to balance
    }

    public boolean spendMoney(double amount) {
        // TODO: returns true when 0 < amount <= balance
        // Otherwise return false and leave balance unchanged
        return false;
    }

    public boolean canAfford(double amount) {
        // TODO: return balance >= amount
        return false;
    }

    public boolean isEmpty() {
        // TODO: return balance == 0
        return false;
    }

    public boolean isValid() {
        // TODO: balance >= 0
        return false;
    }
}
