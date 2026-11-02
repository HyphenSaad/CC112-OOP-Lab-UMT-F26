import chirp.text.Check;

public class AtmMachine {

    private String pin = "1234";
    private double balance = 0.0;
    private boolean isLocked = false;
    private int failedAttempts = 0;

    public void setPin(String value) {
        if (Check.isDigits(value) && Check.hasLength(value, 4)) {
            this.pin = value;
        }
    }

    public double getBalance() { return balance; }

    public boolean isLocked() { return isLocked; }

    public String getStatus() {
        return isLocked ? "Locked" : "Active";
    }

    public boolean authenticate(String enteredPin) {
        if (isLocked) return false;
        if (enteredPin != null && enteredPin.equals(pin)) {
            failedAttempts = 0;
            return true;
        }
        failedAttempts++;
        if (failedAttempts >= 3) isLocked = true;
        return false;
    }

    public void deposit(double amount) {
        if (isLocked) return;
        if (amount <= 0) return;
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (isLocked) return false;
        if (amount <= 0) return false;
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    public boolean isValid() {
        return Check.isDigits(pin) && Check.hasLength(pin, 4)
                && balance >= 0
                && Check.inRange(failedAttempts, 0, 3);
    }
}
