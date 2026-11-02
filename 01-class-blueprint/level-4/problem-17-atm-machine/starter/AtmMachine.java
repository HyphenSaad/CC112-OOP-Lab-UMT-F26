// Problem 17 — AtmMachine
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class AtmMachine {

    private String pin = "1234";
    private double balance = 0.0;
    private boolean isLocked = false;
    private int failedAttempts = 0;

    public void setPin(String value) {
        // TODO: if Check.isDigits(value) && Check.hasLength(value, 4), pin = value
    }

    public double getBalance() { return balance; }
    public boolean isLocked() { return isLocked; }

    public String getStatus() {
        // TODO: "Locked" if isLocked, else "Active"
        return "";
    }

    public boolean authenticate(String enteredPin) {
        // TODO: if locked return false
        // if enteredPin.equals(pin) { failedAttempts = 0; return true }
        // else { failedAttempts++; if (failedAttempts >= 3) isLocked = true; return false }
        return false;
    }

    public void deposit(double amount) {
        // TODO: if locked or amount <= 0, silent reject
        // else balance += amount
    }

    public boolean withdraw(double amount) {
        // TODO: if locked or amount <= 0, return false
        // if amount > balance, return false
        // else balance -= amount, return true
        return false;
    }

    public boolean isValid() {
        // TODO: pin valid AND balance >= 0 AND failedAttempts in [0, 3]
        return false;
    }
}
