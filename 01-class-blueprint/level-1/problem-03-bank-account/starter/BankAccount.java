// Problem 3 — BankAccount
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class BankAccount {

    private String accountNumber = "";
    private double balance = 0.0;

    public String getAccountNumber() { return accountNumber; }

    public void setAccountNumber(String value) {
        // TODO: use Check.notBlank(value)
    }

    public double getBalance() { return balance; }

    public void deposit(double amount) {
        // TODO: if amount > 0, add to balance
    }

    public boolean withdraw(double amount) {
        // TODO: returns true when 0 < amount <= balance
        // Otherwise return false and leave balance unchanged
        return false;
    }

    public boolean isValid() {
        // TODO: accountNumber not blank AND balance >= 0
        return false;
    }
}
