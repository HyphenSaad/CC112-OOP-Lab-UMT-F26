import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    private Password password;

    @BeforeEach
    void setUp() {
        password = new Password();
    }

    @Test
    void freshPasswordIsValid() {
        assertTrue(password.isValid());
        assertEquals("", password.getValue());
    }

    @Test
    void setValueStoresCorrectly() {
        password.setValue("hello");
        assertEquals("hello", password.getValue());
    }

    @Test
    void lengthReturnsCorrectValue() {
        password.setValue("abc");
        assertEquals(3, password.length());
        password.setValue("pass1234");
        assertEquals(8, password.length());
    }

    @Test
    void hasDigitDetectsDigits() {
        password.setValue("abc");
        assertFalse(password.hasDigit());
        password.setValue("abc1");
        assertTrue(password.hasDigit());
    }

    @Test
    void isStrongRequiresLengthAndDigit() {
        password.setValue("abc");
        assertFalse(password.isStrong());
        password.setValue("abcdefgh");
        assertFalse(password.isStrong());
        password.setValue("pass1234");
        assertTrue(password.isStrong());
    }

    @Test
    void maskedReturnsStarsOnly() {
        password.setValue("abc");
        assertEquals("***", password.masked());
        password.setValue("pass1234");
        assertEquals("********", password.masked());
    }

    @Test
    void nullValueRejected() {
        password.setValue("hello");
        password.setValue(null);
        assertEquals("hello", password.getValue());
    }
}
