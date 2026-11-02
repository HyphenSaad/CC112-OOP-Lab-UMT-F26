import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LightBulbTest {

    private LightBulb b;

    @BeforeEach
    void setUp() {
        b = new LightBulb();
    }

    @Test
    void initialStateIsOff() {
        assertFalse(b.isOn());
    }

    @Test
    void isValidAlwaysTrue() {
        assertTrue(b.isValid());
        b.turnOn();
        assertTrue(b.isValid());
        b.turnOff();
        assertTrue(b.isValid());
    }

    @Test
    void turnOnMakesItOn() {
        b.turnOn();
        assertTrue(b.isOn());
    }

    @Test
    void turnOnIsIdempotent() {
        b.turnOn();
        b.turnOn();
        assertTrue(b.isOn());
    }

    @Test
    void toggleFromOnToOff() {
        b.turnOn();
        b.toggle();
        assertFalse(b.isOn());
    }

    @Test
    void toggleFromOffToOn() {
        b.toggle();
        assertTrue(b.isOn());
    }

    @Test
    void turnOffMakesItOff() {
        b.turnOn();
        b.turnOff();
        assertFalse(b.isOn());
    }
}
