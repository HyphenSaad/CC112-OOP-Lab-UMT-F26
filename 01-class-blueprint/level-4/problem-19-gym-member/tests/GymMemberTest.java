import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GymMemberTest {

    private GymMember member;

    @BeforeEach
    void setUp() {
        member = new GymMember();
        member.setName("Bilal");
        member.setMonthlyFee(1500);
    }

    @Test
    void validMemberReportsIsValid() {
        assertTrue(member.isValid());
    }

    @Test
    void startsInactive() {
        assertFalse(member.isActive());
        assertEquals(0, member.getMonthsActive());
    }

    @Test
    void renewIncreasesMonthsAndTotalPaid() {
        member.renew(3);
        assertEquals(3, member.getMonthsActive());
        assertEquals(4500, member.getTotalPaid(), 0.001);
    }

    @Test
    void renewZeroOrNegativeRejected() {
        member.renew(0);
        assertEquals(0, member.getMonthsActive());
        member.renew(-2);
        assertEquals(0, member.getMonthsActive());
    }

    @Test
    void cancelSetsMonthsActiveToZero() {
        member.renew(5);
        member.cancel();
        assertEquals(0, member.getMonthsActive());
        assertFalse(member.isActive());
    }

    @Test
    void cancelKeepsTotalPaid() {
        member.renew(5);
        double paidBefore = member.getTotalPaid();
        member.cancel();
        assertEquals(paidBefore, member.getTotalPaid(), 0.001);
    }

    @Test
    void monthlyCostWhenActive() {
        member.renew(3);
        assertEquals(1500, member.monthlyCost(), 0.001);
    }

    @Test
    void monthlyCostWhenInactiveIsZero() {
        member.renew(3);
        member.cancel();
        assertEquals(0, member.monthlyCost(), 0.001);
    }

    @Test
    void zeroMonthlyFeeRejected() {
        member.setMonthlyFee(0);
        assertEquals(1500, member.getMonthlyFee(), 0.001);
    }
}
