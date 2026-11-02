import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee emp;

    @BeforeEach
    void setUp() {
        emp = new Employee();
        emp.setId("EMP-101");
        emp.setName("Ali");
        emp.setSalary(50000);
    }

    @Test
    void validEmployeeReportsIsValid() {
        assertTrue(emp.isValid());
    }

    @Test
    void attributesStoredCorrectly() {
        assertEquals("EMP-101", emp.getId());
        assertEquals("Ali", emp.getName());
        assertEquals(50000, emp.getSalary(), 0.001);
    }

    @Test
    void annualSalaryIsMonthlyTimesTwelve() {
        assertEquals(600000, emp.annualSalary(), 0.001);
    }

    @Test
    void giveRaiseIncreasesSalary() {
        emp.giveRaise(10);
        assertEquals(55000, emp.getSalary(), 0.001);
    }

    @Test
    void giveRaiseZeroLeavesSalaryUnchanged() {
        emp.giveRaise(0);
        assertEquals(50000, emp.getSalary(), 0.001);
    }

    @Test
    void negativeRaiseRejected() {
        emp.giveRaise(-5);
        assertEquals(50000, emp.getSalary(), 0.001);
    }

    @Test
    void raiseAboveHundredRejected() {
        emp.giveRaise(150);
        assertEquals(50000, emp.getSalary(), 0.001);
    }

    @Test
    void zeroSalaryRejected() {
        emp.setSalary(0);
        assertEquals(50000, emp.getSalary(), 0.001);
    }
}
