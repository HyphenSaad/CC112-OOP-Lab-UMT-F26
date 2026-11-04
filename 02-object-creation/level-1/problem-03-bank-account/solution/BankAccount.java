import chirp.text.Check;

public class BankAccount {

    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber) {
        if (Check.notBlank(accountNumber)) this.accountNumber = accountNumber;
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
