import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void threeArgDelegatesWithGeneralDepartment() {
        Employee e = new Employee("E1", "John", 50000);
        assertEquals("General", e.getDepartment());
        assertTrue(e.isValid());
    }

    @Test
    void fourArgStoresProvidedDepartment() {
        Employee e = new Employee("E1", "John", 150000, "Manager");
        assertEquals("Manager", e.getDepartment());
        assertTrue(e.isValid());
    }

    @Test
    void managerWithHighSalaryAccepted() {
        Employee e = new Employee("E1", "John", 100000, "Manager");
        assertTrue(e.isValid());
    }

    @Test
    void managerWithLowSalaryRejected() {
        Employee e = new Employee("E1", "John", 50000, "Manager");
        assertNull(e.getDepartment(), "Manager with salary < 100000 must be rejected");
        assertFalse(e.isValid());
    }

    @Test
    void nonManagerWithAnySalaryAccepted() {
        Employee e = new Employee("E1", "John", 50000, "Engineer");
        assertTrue(e.isValid());
    }

    @Test
    void invalidIdRejectedByBothConstructors() {
        assertNull(new Employee("", "John", 50000).getId());
        assertNull(new Employee("", "John", 50000, "Engineer").getId());
    }

    @Test
    void invalidDepartmentRejected() {
        Employee e = new Employee("E1", "John", 150000, "   ");
        assertNull(e.getDepartment());
        assertFalse(e.isValid());
    }
}
