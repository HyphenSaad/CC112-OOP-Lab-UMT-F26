import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void constructorStoresValidValues() {
        Employee e = new Employee("EMP-101", "Ali", 50000);
        assertEquals("EMP-101", e.getId());
        assertEquals("Ali", e.getName());
        assertEquals(50000, e.getSalary(), 0.001);
        assertTrue(e.isValid());
    }

    @Test
    void invalidSalaryLeavesFieldZero() {
        Employee e = new Employee("EMP-101", "Ali", -500);
        assertEquals(0, e.getSalary(), 0.001);
        assertFalse(e.isValid());
    }

    @Test
    void annualSalaryIsMonthlyTimesTwelve() {
        Employee e = new Employee("EMP-101", "Ali", 50000);
        assertEquals(600000, e.annualSalary(), 0.001);
    }

    @Test
    void giveRaiseIncreasesSalary() {
        Employee e = new Employee("EMP-101", "Ali", 50000);
        e.giveRaise(10);
        assertEquals(55000, e.getSalary(), 0.001);
    }

    @Test
    void giveRaiseOutOfRangeIgnored() {
        Employee e = new Employee("EMP-101", "Ali", 50000);
        e.giveRaise(-5);
        assertEquals(50000, e.getSalary(), 0.001);
        e.giveRaise(150);
        assertEquals(50000, e.getSalary(), 0.001);
    }

    @Test
    void isValidFalseForZeroSalary() {
        assertFalse(new Employee("EMP-101", "Ali", 0).isValid());
    }
}
