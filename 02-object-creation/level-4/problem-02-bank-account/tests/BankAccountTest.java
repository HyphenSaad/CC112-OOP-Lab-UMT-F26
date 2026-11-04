import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void oneArgDelegatesWithZeroBalance() {
        BankAccount acc = new BankAccount("ACC-1001");
        assertEquals(0.0, acc.getBalance(), 0.001);
        assertTrue(acc.isValid());
    }

    @Test
    void twoArgStoresInitialBalance() {
        BankAccount acc = new BankAccount("ACC-1002", 5000);
        assertEquals(5000, acc.getBalance(), 0.001);
        assertTrue(acc.isValid());
    }

    @Test
    void invalidAccountNumberRejectedByBothConstructors() {
        assertFalse(new BankAccount("").isValid());
        assertFalse(new BankAccount("", 5000).isValid());
    }

    @Test
    void negativeInitialBalanceRejected() {
        BankAccount acc = new BankAccount("ACC-1003", -100);
        assertEquals(0.0, acc.getBalance(), 0.001);
    }

    @Test
    void delegateConstructorStillEnforcesAccountNumberRule() {
        // The delegating constructor must NOT bypass the blank-number rule
        BankAccount bad = new BankAccount("   ");
        assertNull(bad.getAccountNumber());
        assertFalse(bad.isValid());
    }

    @Test
    void delegateConstructorStillEnforcesBalanceRule() {
        // And it must not bypass the zero-balance rule — but zero is valid
        BankAccount ok = new BankAccount("ACC-1004");
        assertEquals(0.0, ok.getBalance(), 0.001);
        assertTrue(ok.isValid());
    }
}
