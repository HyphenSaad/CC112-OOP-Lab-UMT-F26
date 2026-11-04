import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void singleArgConstructorStartsAtZero() {
        BankAccount acc = new BankAccount("ACC-1001");
        assertEquals("ACC-1001", acc.getAccountNumber());
        assertEquals(0.0, acc.getBalance(), 0.001);
        assertTrue(acc.isValid());
    }

    @Test
    void twoArgConstructorStoresInitialBalance() {
        BankAccount acc = new BankAccount("ACC-1002", 5000);
        assertEquals("ACC-1002", acc.getAccountNumber());
        assertEquals(5000, acc.getBalance(), 0.001);
        assertTrue(acc.isValid());
    }

    @Test
    void invalidAccountNumberInEitherConstructorLeavesNull() {
        assertNull(new BankAccount("").getAccountNumber());
        assertNull(new BankAccount("", 5000).getAccountNumber());
    }

    @Test
    void invalidInitialBalanceInTwoArgLeavesZero() {
        BankAccount acc = new BankAccount("ACC-1003", -100);
        assertEquals(0.0, acc.getBalance(), 0.001);
    }

    @Test
    void twoAccountsAreIndependent() {
        BankAccount a = new BankAccount("A", 1000);
        BankAccount b = new BankAccount("B", 500);
        a.deposit(500);
        assertEquals(1500, a.getBalance(), 0.001);
        assertEquals(500, b.getBalance(), 0.001);
    }

    @Test
    void negativeInitialBalanceRejected() {
        BankAccount acc = new BankAccount("ACC-1004", -1);
        assertFalse(acc.getBalance() < 0);
    }
}
