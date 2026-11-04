import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void constructorStoresAccountNumber() {
        BankAccount acc = new BankAccount("ACC-1001");
        assertEquals("ACC-1001", acc.getAccountNumber());
        assertTrue(acc.isValid());
    }

    @Test
    void balanceStartsAtZero() {
        assertEquals(0.0, new BankAccount("ACC-1001").getBalance(), 0.001);
    }

    @Test
    void invalidAccountNumberLeavesFieldNull() {
        BankAccount acc = new BankAccount("");
        assertNull(acc.getAccountNumber());
        assertFalse(acc.isValid());
    }

    @Test
    void depositIncreasesBalance() {
        BankAccount acc = new BankAccount("ACC-1001");
        acc.deposit(500);
        acc.deposit(300);
        assertEquals(800, acc.getBalance(), 0.001);
    }

    @Test
    void withdrawReducesBalanceAndReturnsTrue() {
        BankAccount acc = new BankAccount("ACC-1001");
        acc.deposit(800);
        assertTrue(acc.withdraw(200));
        assertEquals(600, acc.getBalance(), 0.001);
    }

    @Test
    void overWithdrawReturnsFalseAndKeepsBalance() {
        BankAccount acc = new BankAccount("ACC-1001");
        acc.deposit(600);
        assertFalse(acc.withdraw(999));
        assertEquals(600, acc.getBalance(), 0.001);
    }

    @Test
    void invalidAccountIsNotValid() {
        assertFalse(new BankAccount("   ").isValid());
    }
}
