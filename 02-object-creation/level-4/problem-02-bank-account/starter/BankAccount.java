// Problem 2 — BankAccount (Chaining for Default Values)
// Refactor: make the single-arg constructor delegate via this(acc, 0.0);

import chirp.text.Check;

public class BankAccount {

    private String accountNumber;
    private double balance;

    // TODO: delegate via this(accountNumber, 0.0);
    public BankAccount(String accountNumber) {
    }

    // Canonical constructor — keeps all the validation.
    public BankAccount(String accountNumber, double initialBalance) {
        if (Check.notBlank(accountNumber)) this.accountNumber = accountNumber;
        if (Check.inRange(initialBalance, 0, Double.MAX_VALUE)) this.balance = initialBalance;
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
