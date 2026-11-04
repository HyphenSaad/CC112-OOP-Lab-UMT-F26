// Problem 3 — BankAccount
// Fill in the constructor and methods below. See README.md for requirements.

import chirp.text.Check;

public class BankAccount {

    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber) {
        // TODO: if Check.notBlank(accountNumber) -> this.accountNumber = accountNumber
        // balance stays at 0.0 (do not assign — that is the default)
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        // TODO: if amount > 0, balance += amount
    }

    public boolean withdraw(double amount) {
        // TODO: if 0 < amount <= balance, balance -= amount, return true; else false
        return false;
    }

    public boolean isValid() {
        // TODO: accountNumber not blank AND balance >= 0
        return false;
    }
}
