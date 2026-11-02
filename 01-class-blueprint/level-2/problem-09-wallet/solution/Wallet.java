public class Wallet {

    private double balance = 0.0;

    public double getBalance() { return balance; }

    public void addMoney(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean spendMoney(double amount) {
        if (amount <= 0) return false;
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    public boolean canAfford(double amount) {
        return balance >= amount;
    }

    public boolean isEmpty() {
        return balance == 0;
    }

    public boolean isValid() {
        return balance >= 0;
    }
}
