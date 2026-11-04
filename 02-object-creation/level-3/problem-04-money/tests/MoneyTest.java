import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void singleArgConstructorStoresRupeesTimes100Paise() {
        Money m = new Money(100);
        assertEquals(10000, m.totalPaise());
        assertEquals(100, m.getRupees());
        assertEquals(0, m.getPaise());
    }

    @Test
    void twoArgConstructorAddsRupeesAndPaise() {
        Money m = new Money(99, 50);
        assertEquals(9950, m.totalPaise());
        assertEquals(99, m.getRupees());
        assertEquals(50, m.getPaise());
    }

    @Test
    void twoArgRejectsPaiseAbove99() {
        Money m = new Money(100, 100);
        assertEquals(0, m.totalPaise());
        assertFalse(m.isValid());
    }

    @Test
    void negativeRupeesLeavesZero() {
        Money m = new Money(-5);
        assertEquals(0, m.totalPaise());
    }

    @Test
    void plusReturnsNewIndependentMoney() {
        Money a = new Money(100);
        Money b = new Money(99, 50);
        Money sum = a.plus(b);
        assertEquals(19950, sum.totalPaise());
        assertEquals(10000, a.totalPaise());
        assertEquals(9950, b.totalPaise());
    }

    @Test
    void getRupeesAndGetPaiseDecomposeCorrectly() {
        Money m = new Money(42, 17);
        assertEquals(42, m.getRupees());
        assertEquals(17, m.getPaise());
    }
}
