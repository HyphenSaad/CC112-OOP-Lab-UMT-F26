import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount acc;

    @BeforeEach
    void setUp() {
        acc = new BankAccount();
        acc.setAccountNumber("ACC-1001");
    }

    @Test
    void validAccountReportsIsValid() {
        assertTrue(acc.isValid());
    }

    @Test
    void initialBalanceIsZero() {
        assertEquals(0.0, acc.getBalance(), 0.0001);
    }

    @Test
    void depositIncreasesBalance() {
        acc.deposit(500);
        acc.deposit(300);
        assertEquals(800.0, acc.getBalance(), 0.0001);
    }

    @Test
    void withdrawReducesBalanceAndReturnsTrue() {
        acc.deposit(800);
        assertTrue(acc.withdraw(200));
        assertEquals(600.0, acc.getBalance(), 0.0001);
    }

    @Test
    void overWithdrawReturnsFalseAndKeepsBalance() {
        acc.deposit(600);
        assertFalse(acc.withdraw(999));
        assertEquals(600.0, acc.getBalance(), 0.0001);
    }

    @Test
    void negativeDepositRejected() {
        acc.deposit(100);
        acc.deposit(-50);
        assertEquals(100.0, acc.getBalance(), 0.0001);
    }

    @Test
    void zeroWithdrawReturnsFalse() {
        acc.deposit(100);
        assertFalse(acc.withdraw(0));
        assertEquals(100.0, acc.getBalance(), 0.0001);
    }

    @Test
    void blankAccountNumberRejected() {
        acc.setAccountNumber("   ");
        assertEquals("ACC-1001", acc.getAccountNumber());
    }

    @Test
    void freshAccountWithoutNumberNotValid() {
        BankAccount fresh = new BankAccount();
        assertFalse(fresh.isValid());
    }
}
