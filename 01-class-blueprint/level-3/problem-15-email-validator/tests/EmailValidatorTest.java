import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    private EmailValidator v;

    @BeforeEach
    void setUp() {
        v = new EmailValidator();
    }

    @Test
    void freshValidatorIsValid() {
        assertTrue(v.isValid());
        assertEquals("", v.getEmail());
        assertFalse(v.isValidEmail());
    }

    @Test
    void setEmailStoresTrimmedValue() {
        v.setEmail("  Saad@Example.COM  ");
        assertEquals("Saad@Example.COM", v.getEmail());
    }

    @Test
    void setEmailNullRejected() {
        v.setEmail("hello@example.com");
        v.setEmail(null);
        assertEquals("hello@example.com", v.getEmail());
    }

    @Test
    void validEmailReturnsTrue() {
        v.setEmail("Saad@Example.COM");
        assertTrue(v.isValidEmail());
    }

    @Test
    void invalidEmailReturnsFalse() {
        v.setEmail("not-an-email");
        assertFalse(v.isValidEmail());
    }

    @Test
    void normalizeLowercasesAfterAt() {
        v.setEmail("Saad@Example.COM");
        assertEquals("Saad@example.com", v.normalize());
    }

    @Test
    void domainReturnsAfterAt() {
        v.setEmail("Saad@Example.COM");
        assertEquals("Example.COM", v.domain());
    }

    @Test
    void domainReturnsEmptyForInvalid() {
        v.setEmail("not-an-email");
        assertEquals("", v.domain());
    }
}
