import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void constructorStoresValidValues() {
        Rectangle r = new Rectangle(8, 5);
        assertEquals(8, r.getLength(), 0.001);
        assertEquals(5, r.getWidth(), 0.001);
        assertTrue(r.isValid());
    }

    @Test
    void invalidLengthLeavesFieldZero() {
        Rectangle r = new Rectangle(0, 5);
        assertEquals(0, r.getLength(), 0.001);
        assertFalse(r.isValid());
    }

    @Test
    void invalidWidthLeavesFieldZero() {
        Rectangle r = new Rectangle(8, -2.5);
        assertEquals(0, r.getWidth(), 0.001);
        assertFalse(r.isValid());
    }

    @Test
    void areaAndPerimeterCorrect() {
        Rectangle r = new Rectangle(8, 5);
        assertEquals(40, r.area(), 0.001);
        assertEquals(26, r.perimeter(), 0.001);
    }

    @Test
    void isSquareWhenLengthEqualsWidth() {
        assertTrue(new Rectangle(8, 8).isSquare());
        assertFalse(new Rectangle(8, 5).isSquare());
    }

    @Test
    void isValidFalseWhenAnyFieldZero() {
        assertFalse(new Rectangle(0, 5).isValid());
        assertFalse(new Rectangle(5, 0).isValid());
    }
}
