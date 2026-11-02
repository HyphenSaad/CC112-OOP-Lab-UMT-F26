import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setName("Ayesha");
        student.setAge(20);
        student.setGpa(3.75);
    }

    @Test
    void validStudentReportsIsValid() {
        assertTrue(student.isValid());
    }

    @Test
    void attributesStoredCorrectly() {
        assertEquals("Ayesha", student.getName());
        assertEquals(20, student.getAge());
        assertEquals(3.75, student.getGpa(), 0.001);
    }

    @Test
    void introduceContainsNameAndGpa() {
        String intro = student.introduce();
        assertTrue(intro.contains("Ayesha"), "introduce() must mention the name");
        assertTrue(intro.contains("3.75"), "introduce() must mention the GPA");
        assertTrue(intro.contains("20"), "introduce() must mention the age");
    }

    @Test
    void ageAboveMaximumRejected() {
        student.setAge(200);
        assertEquals(20, student.getAge(), "setAge(200) must be silently rejected");
    }

    @Test
    void ageBelowMinimumRejected() {
        student.setAge(0);
        assertEquals(20, student.getAge(), "setAge(0) must be silently rejected");
    }

    @Test
    void negativeGpaRejected() {
        student.setGpa(-1);
        assertEquals(3.75, student.getGpa(), 0.001, "negative GPA must be rejected");
    }

    @Test
    void gpaAboveMaximumRejected() {
        student.setGpa(5.0);
        assertEquals(3.75, student.getGpa(), 0.001, "GPA > 4.0 must be rejected");
    }

    @Test
    void blankNameRejected() {
        student.setName("   ");
        assertEquals("Ayesha", student.getName(), "blank name must be rejected");
    }

    @Test
    void nameWithDigitsRejected() {
        student.setName("Ayesha123");
        assertEquals("Ayesha", student.getName(), "name with digits must be rejected");
    }

    @Test
    void freshStudentIsNotValid() {
        Student fresh = new Student();
        assertFalse(fresh.isValid(), "a fresh Student has empty name and is therefore not valid");
    }
}
