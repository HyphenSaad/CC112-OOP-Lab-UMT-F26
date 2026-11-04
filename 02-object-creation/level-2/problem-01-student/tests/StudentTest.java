import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void copyConstructorCopiesAllFields() {
        Student original = new Student("John", 20, 3.75);
        Student copy = new Student(original);
        assertEquals("John", copy.getName());
        assertEquals(20, copy.getAge());
        assertEquals(3.75, copy.getGpa(), 0.001);
    }

    @Test
    void copyOfValidIsValid() {
        Student original = new Student("John", 20, 3.75);
        assertTrue(new Student(original).isValid());
    }

    @Test
    void copyOfInvalidIsInvalid() {
        Student invalid = new Student("", 500, -1.0);
        assertFalse(new Student(invalid).isValid());
    }

    @Test
    void copyOfNullHasDefaults() {
        Student copy = new Student(null);
        assertNull(copy.getName());
        assertEquals(0, copy.getAge());
        assertEquals(0.0, copy.getGpa(), 0.001);
        assertFalse(copy.isValid());
    }

    @Test
    void originalUnaffectedByCopyCreation() {
        Student original = new Student("John", 20, 3.75);
        new Student(original);
        assertEquals("John", original.getName());
        assertEquals(20, original.getAge());
    }
}
