import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    private Rectangle r;

    @BeforeEach
    void setUp() {
        r = new Rectangle();
        r.setLength(8);
        r.setWidth(5);
    }

    @Test
    void validRectangleReportsIsValid() {
        assertTrue(r.isValid());
    }

    @Test
    void areaIsCorrect() {
        assertEquals(40, r.area(), 0.001);
    }

    @Test
    void perimeterIsCorrect() {
        assertEquals(26, r.perimeter(), 0.001);
    }

    @Test
    void notASquareWhenLengthDiffersFromWidth() {
        assertFalse(r.isSquare());
    }

    @Test
    void squareWhenLengthEqualsWidth() {
        r.setWidth(8);
        assertTrue(r.isSquare());
    }

    @Test
    void zeroLengthRejected() {
        r.setLength(0);
        assertEquals(8, r.getLength(), 0.001);
    }

    @Test
    void negativeWidthRejected() {
        r.setWidth(-2.5);
        assertEquals(5, r.getWidth(), 0.001);
    }
}
