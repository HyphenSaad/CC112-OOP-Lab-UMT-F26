import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AtmMachineTest {

    private AtmMachine atm;

    @BeforeEach
    void setUp() {
        atm = new AtmMachine();
    }

    @Test
    void defaultPinIsValid() {
        assertTrue(atm.isValid());
        assertEquals("Active", atm.getStatus());
    }

    @Test
    void setPinRejectsInvalidFormat() {
        atm.setPin("12");       // too short
        assertFalse(atm.authenticate("12"));
        atm.setPin("abcd");     // not digits
        assertFalse(atm.authenticate("abcd"));
        atm.setPin("12345");    // too long
        assertFalse(atm.authenticate("12345"));
    }

    @Test
    void authenticateCorrectPinReturnsTrue() {
        assertTrue(atm.authenticate("1234"));
    }

    @Test
    void authenticateWrongPinReturnsFalse() {
        assertFalse(atm.authenticate("9999"));
        assertEquals("Active", atm.getStatus());
    }

    @Test
    void threeWrongAttemptsLocksAtm() {
        atm.authenticate("0000");
        atm.authenticate("1111");
        atm.authenticate("9999");
        assertEquals("Locked", atm.getStatus());
        assertTrue(atm.isLocked());
    }

    @Test
    void correctPinBeforeLockResetsCounter() {
        atm.authenticate("0000");
        atm.authenticate("1111");
        assertTrue(atm.authenticate("1234"));  // resets
        // Counter is now 0, so three more wrong attempts are needed to lock
        atm.authenticate("0000");
        atm.authenticate("1111");
        assertEquals("Active", atm.getStatus());
    }

    @Test
    void depositWorksWhenActive() {
        atm.deposit(500);
        assertEquals(500, atm.getBalance(), 0.001);
    }

    @Test
    void depositRejectedWhenLocked() {
        atm.authenticate("0000");
        atm.authenticate("1111");
        atm.authenticate("9999");
        atm.deposit(100);
        assertEquals(0, atm.getBalance(), 0.001);
    }

    @Test
    void withdrawWorksWhenActive() {
        atm.deposit(500);
        assertTrue(atm.withdraw(200));
        assertEquals(300, atm.getBalance(), 0.001);
    }

    @Test
    void withdrawRejectedWhenLockedOrInsufficient() {
        atm.deposit(100);
        assertFalse(atm.withdraw(200));       // insufficient
        assertEquals(100, atm.getBalance(), 0.001);

        atm.authenticate("0000");
        atm.authenticate("1111");
        atm.authenticate("9999");
        assertFalse(atm.withdraw(50));        // locked
        assertEquals(100, atm.getBalance(), 0.001);
    }
}
