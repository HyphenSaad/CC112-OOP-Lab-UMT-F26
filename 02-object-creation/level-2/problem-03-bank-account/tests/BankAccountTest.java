import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void copyConstructorCopiesAccountNumberAndBalance() {
        BankAccount original = new BankAccount("ACC-1001");
        original.deposit(500);
        BankAccount copy = new BankAccount(original);
        assertEquals("ACC-1001", copy.getAccountNumber());
        assertEquals(500, copy.getBalance(), 0.001);
    }

    @Test
    void copyOfNullHasDefaults() {
        BankAccount copy = new BankAccount((BankAccount) null);
        assertNull(copy.getAccountNumber());
        assertEquals(0.0, copy.getBalance(), 0.001);
        assertFalse(copy.isValid());
    }

    @Test
    void depositToOriginalDoesNotAffectCopy() {
        BankAccount original = new BankAccount("ACC-1001");
        original.deposit(500);
        BankAccount copy = new BankAccount(original);
        original.deposit(300);
        assertEquals(500, copy.getBalance(), 0.001);
    }

    @Test
    void depositToCopyDoesNotAffectOriginal() {
        BankAccount original = new BankAccount("ACC-1001");
        original.deposit(500);
        BankAccount copy = new BankAccount(original);
        copy.deposit(200);
        assertEquals(500, original.getBalance(), 0.001);
    }

    @Test
    void withdrawFromCopyDoesNotAffectOriginal() {
        BankAccount original = new BankAccount("ACC-1001");
        original.deposit(500);
        BankAccount copy = new BankAccount(original);
        copy.withdraw(200);
        assertEquals(500, original.getBalance(), 0.001);
        assertEquals(300, copy.getBalance(), 0.001);
    }

    @Test
    void copyOfInvalidIsInvalid() {
        BankAccount invalid = new BankAccount("");
        assertFalse(new BankAccount(invalid).isValid());
    }
}
