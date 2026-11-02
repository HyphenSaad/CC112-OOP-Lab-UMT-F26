import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureTest {

    private Temperature t;

    @BeforeEach
    void setUp() {
        t = new Temperature();
        t.setCelsius(25);
    }

    @Test
    void validTemperatureReportsIsValid() {
        assertTrue(t.isValid());
    }

    @Test
    void celsiusStoredCorrectly() {
        assertEquals(25, t.getCelsius(), 0.001);
    }

    @Test
    void toFahrenheitIsCorrect() {
        assertEquals(77.0, t.toFahrenheit(), 0.001);
    }

    @Test
    void toKelvinIsCorrect() {
        assertEquals(298.15, t.toKelvin(), 0.001);
    }

    @Test
    void isFreezingAtZeroOrBelow() {
        assertFalse(t.isFreezing());
        t.setCelsius(0);
        assertTrue(t.isFreezing());
        t.setCelsius(-10);
        assertTrue(t.isFreezing());
    }

    @Test
    void isBoilingAt100OrAbove() {
        assertFalse(t.isBoiling());
        t.setCelsius(100);
        assertTrue(t.isBoiling());
    }

    @Test
    void belowAbsoluteZeroRejected() {
        t.setCelsius(-300);
        assertEquals(25, t.getCelsius(), 0.001);
    }
}
