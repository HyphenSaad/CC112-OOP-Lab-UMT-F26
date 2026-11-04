import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void squareConstructorDelegatesToRectangle() {
        Rectangle r = new Rectangle(5);
        assertEquals(5, r.getLength(), 0.001);
        assertEquals(5, r.getWidth(), 0.001);
    }

    @Test
    void rectangleConstructorValidatesIndependently() {
        Rectangle r = new Rectangle(0, 5);
        assertEquals(0, r.getLength(), 0.001);
        assertEquals(5, r.getWidth(), 0.001);
    }

    @Test
    void invalidSideLeavesBothZero() {
        Rectangle r = new Rectangle(-3);
        assertEquals(0, r.getLength(), 0.001);
        assertEquals(0, r.getWidth(), 0.001);
        assertFalse(r.isValid());
    }

    @Test
    void squareIsSquare() {
        assertTrue(new Rectangle(5).isSquare());
    }

    @Test
    void rectangleConstructorOfSquareProducesSquare() {
        assertTrue(new Rectangle(5, 5).isSquare());
    }

    @Test
    void bothConstructorsUseSameValidationRange() {
        // A side of -1 must be rejected by the delegating constructor
        // because the canonical one rejects it.
        Rectangle r = new Rectangle(-1);
        assertFalse(r.isValid(), "single-arg constructor must inherit canonical validation");
    }
}
