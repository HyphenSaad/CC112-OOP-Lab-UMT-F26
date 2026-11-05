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
        assertTrue(intro.contains("Ayesha"), "introduce must mention the name");
        assertTrue(intro.contains("20"), "introduce must mention the age");
        assertTrue(intro.contains("3.75"), "introduce must mention the GPA");
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
        assertEquals(3.75, student.getGpa(), 0.001);
    }

    @Test
    void gpaAboveMaximumRejected() {
        student.setGpa(5.0);
        assertEquals(3.75, student.getGpa(), 0.001);
    }

    @Test
    void blankNameRejected() {
        student.setName("   ");
        assertEquals("Ayesha", student.getName());
    }

    @Test
    void nameWithDigitsRejected() {
        student.setName("Ayesha123");
        assertEquals("Ayesha", student.getName(), "Check.isName must reject digits");
    }

    @Test
    void freshStudentIsNotValid() {
        Student fresh = new Student();
        assertFalse(fresh.isValid(), "default name is empty so isName fails");
    }
}
