// Problem 2 — BankAccount (With or Without Initial Balance)
// Fill in both constructors. See README.md for requirements.

import chirp.text.Check;

public class BankAccount {

    private String accountNumber;
    private double balance;

    // Open with zero balance.
    public BankAccount(String accountNumber) {
        // TODO: if Check.notBlank(accountNumber) this.accountNumber = accountNumber
        // balance stays 0.0 (Java default)
    }

    // Open with a starting balance.
    public BankAccount(String accountNumber, double initialBalance) {
        // TODO: validate accountNumber
        // TODO: if Check.inRange(initialBalance, 0, Double.MAX_VALUE) this.balance = initialBalance
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    public boolean isValid() {
        return Check.notBlank(accountNumber) && balance >= 0;
    }
}
