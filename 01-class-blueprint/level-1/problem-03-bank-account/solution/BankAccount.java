import chirp.text.Check;

public class BankAccount {

    private String accountNumber = "";
    private double balance = 0.0;

    public String getAccountNumber() { return accountNumber; }

    public void setAccountNumber(String value) {
        if (Check.notBlank(value)) {
            this.accountNumber = value;
        }
    }

    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
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
