import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    private Circle c;

    @BeforeEach
    void setUp() {
        c = new Circle();
        c.setRadius(5);
    }

    @Test
    void validCircleReportsIsValid() {
        assertTrue(c.isValid());
    }

    @Test
    void diameterIsDoubleRadius() {
        assertEquals(10.0, c.diameter(), 0.001);
    }

    @Test
    void areaIsPiRSquared() {
        assertEquals(Math.PI * 25, c.area(), 0.001);
    }

    @Test
    void circumferenceIs2PiR() {
        assertEquals(2 * Math.PI * 5, c.circumference(), 0.001);
    }

    @Test
    void zeroRadiusRejected() {
        c.setRadius(0);
        assertEquals(5.0, c.getRadius(), 0.001);
    }

    @Test
    void negativeRadiusRejected() {
        c.setRadius(-3);
        assertEquals(5.0, c.getRadius(), 0.001);
    }

    @Test
    void defaultRadiusIsOne() {
        Circle fresh = new Circle();
        assertEquals(1.0, fresh.getRadius(), 0.001);
    }
}
