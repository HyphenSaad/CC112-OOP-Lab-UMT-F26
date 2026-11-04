import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    void constructorStoresRadius() {
        Circle c = new Circle(5);
        assertEquals(5, c.getRadius(), 0.001);
        assertTrue(c.isValid());
    }

    @Test
    void invalidRadiusLeavesFieldZero() {
        Circle c = new Circle(-3);
        assertEquals(0, c.getRadius(), 0.001);
        assertFalse(c.isValid());
    }

    @Test
    void areaCircumfrenceDiameterCorrect() {
        Circle c = new Circle(5);
        assertEquals(10, c.diameter(), 0.001);
        assertEquals(Math.PI * 25, c.area(), 0.001);
        assertEquals(2 * Math.PI * 5, c.circumference(), 0.001);
    }

    @Test
    void isValidFalseForZeroRadius() {
        assertFalse(new Circle(0).isValid());
    }

    @Test
    void isValidTrueForPositiveRadius() {
        assertTrue(new Circle(0.01).isValid());
        assertTrue(new Circle(1000).isValid());
    }
}
