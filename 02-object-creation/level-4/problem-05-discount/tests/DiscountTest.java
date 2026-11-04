import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {

    @Test
    void percentDiscountAppliesCorrectly() {
        Discount d = new Discount(10, "PERCENT");
        assertEquals(900, d.apply(1000), 0.001);
    }

    @Test
    void fixedDiscountAppliesCorrectly() {
        Discount d = new Discount(250, "FIXED");
        assertEquals(750, d.apply(1000), 0.001);
    }

    @Test
    void fixedDiscountCappedAtSubtotal() {
        Discount d = new Discount(250, "FIXED");
        assertEquals(0, d.apply(100), 0.001, "discount can't make total negative");
    }

    @Test
    void percentAbove100Rejected() {
        assertFalse(new Discount(150, "PERCENT").isValid());
    }

    @Test
    void percentNegativeRejected() {
        assertFalse(new Discount(-5, "PERCENT").isValid());
    }

    @Test
    void fixedNegativeRejected() {
        assertFalse(new Discount(-5, "FIXED").isValid());
    }

    @Test
    void unknownTypeRejected() {
        assertFalse(new Discount(10, "BOGUS").isValid());
    }

    @Test
    void typeSynonymNormalizedToCanonical() {
        assertEquals("PERCENT", new Discount(10, "pct").getType());
        assertEquals("PERCENT", new Discount(10, "%").getType());
        assertEquals("FIXED", new Discount(100, "rs").getType());
        assertEquals("FIXED", new Discount(100, "amount").getType());
    }
}
