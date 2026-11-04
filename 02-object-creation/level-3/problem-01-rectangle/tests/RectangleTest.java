import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void squareConstructorSetsBothSides() {
        Rectangle r = new Rectangle(5);
        assertEquals(5, r.getLength(), 0.001);
        assertEquals(5, r.getWidth(), 0.001);
    }

    @Test
    void squareConstructorProducesValidSquare() {
        Rectangle r = new Rectangle(5);
        assertTrue(r.isValid());
        assertTrue(r.isSquare());
    }

    @Test
    void rectangleConstructorStoresBothSides() {
        Rectangle r = new Rectangle(8, 5);
        assertEquals(8, r.getLength(), 0.001);
        assertEquals(5, r.getWidth(), 0.001);
        assertTrue(r.isValid());
    }

    @Test
    void rectangleConstructorOfSquareProducesSquare() {
        Rectangle r = new Rectangle(5, 5);
        assertTrue(r.isSquare());
    }

    @Test
    void invalidSideLeavesBothZero() {
        Rectangle r = new Rectangle(-3);
        assertEquals(0, r.getLength(), 0.001);
        assertEquals(0, r.getWidth(), 0.001);
        assertFalse(r.isValid());
    }

    @Test
    void invalidLengthOrWidthLeavesRespectiveFieldZero() {
        Rectangle badLength = new Rectangle(0, 5);
        assertEquals(0, badLength.getLength(), 0.001);
        assertEquals(5, badLength.getWidth(), 0.001);

        Rectangle badWidth = new Rectangle(8, -2.5);
        assertEquals(8, badWidth.getLength(), 0.001);
        assertEquals(0, badWidth.getWidth(), 0.001);
    }
}
