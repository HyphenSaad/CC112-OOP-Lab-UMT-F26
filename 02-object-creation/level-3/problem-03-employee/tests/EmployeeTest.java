import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void threeArgUsesGeneralDepartment() {
        Employee e = new Employee("EMP-101", "John", 50000);
        assertEquals("General", e.getDepartment());
    }

    @Test
    void fourArgStoresProvidedDepartment() {
        Employee e = new Employee("EMP-102", "Sara", 70000, "Engineering");
        assertEquals("Engineering", e.getDepartment());
    }

    @Test
    void fourArgRejectsBlankDepartment() {
        Employee e = new Employee("EMP-102", "Sara", 70000, "   ");
        assertNull(e.getDepartment());
        assertFalse(e.isValid());
    }

    @Test
    void annualSalaryCorrectForBoth() {
        assertEquals(600000, new Employee("E1", "John", 50000).annualSalary(), 0.001);
        assertEquals(840000, new Employee("E2", "Sara", 70000, "Engineering").annualSalary(), 0.001);
    }

    @Test
    void invalidBasicFieldsRejectedInBothConstructors() {
        Employee e3 = new Employee("", "John", 50000);
        assertNull(e3.getId());

        Employee e4 = new Employee("E4", "John", -100, "Engineering");
        assertEquals(0.0, e4.getSalary(), 0.001);
    }

    @Test
    void threeArgEmployeeIsValid() {
        assertTrue(new Employee("EMP-101", "John", 50000).isValid());
    }
}
