import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void constructorStoresValidValues() {
        Student s = new Student("John", 20, 3.75);
        assertEquals("John", s.getName());
        assertEquals(20, s.getAge());
        assertEquals(3.75, s.getGpa(), 0.001);
        assertTrue(s.isValid());
    }

    @Test
    void invalidNameLeavesFieldNull() {
        Student s = new Student("", 20, 3.75);
        assertNull(s.getName(), "empty name must be rejected — field stays null");
        assertFalse(s.isValid());
    }

    @Test
    void invalidAgeLeavesFieldZero() {
        Student s = new Student("John", 200, 3.75);
        assertEquals(0, s.getAge(), "age 200 must be rejected — field stays at 0");
        assertFalse(s.isValid());
    }

    @Test
    void invalidGpaLeavesFieldZero() {
        Student s = new Student("John", 20, -1.0);
        assertEquals(0.0, s.getGpa(), 0.001, "gpa -1 must be rejected — field stays at 0.0");
        assertFalse(s.isValid());
    }

    @Test
    void isValidTrueForValidStudent() {
        assertTrue(new Student("John", 20, 3.75).isValid());
    }

    @Test
    void isValidFalseForAnyInvalidField() {
        assertFalse(new Student("John", 200, 3.75).isValid());
        assertFalse(new Student("John", 20, -1.0).isValid());
        assertFalse(new Student("", 20, 3.75).isValid());
    }

    @Test
    void introduceIncludesAllFields() {
        String intro = new Student("John", 20, 3.75).introduce();
        assertTrue(intro.contains("John"));
        assertTrue(intro.contains("20"));
        assertTrue(intro.contains("3.75"));
    }

    @Test
    void introduceHandlesNullName() {
        String intro = new Student("", 20, 3.75).introduce();
        assertTrue(intro.contains("?"), "null name should render as ?");
    }
}
