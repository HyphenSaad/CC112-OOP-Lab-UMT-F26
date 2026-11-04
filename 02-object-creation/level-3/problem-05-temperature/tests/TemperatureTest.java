import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureTest {

    @Test
    void celsiusConstructorStoresValue() {
        Temperature t = new Temperature(25);
        assertEquals(25, t.getCelsius(), 0.001);
    }

    @Test
    void fahrenheitConstructorConverts() {
        Temperature t = new Temperature(212, "F");
        assertEquals(100, t.getCelsius(), 0.001);
    }

    @Test
    void kelvinConstructorConverts() {
        Temperature t = new Temperature(298.15, "K");
        assertEquals(25, t.getCelsius(), 0.001);
    }

    @Test
    void unitCaseAndLongNameAccepted() {
        assertEquals(25, new Temperature(25, "C").getCelsius(), 0.001);
        assertEquals(25, new Temperature(25, "Celsius").getCelsius(), 0.001);
        assertEquals(25, new Temperature(77, "F").getCelsius(), 0.001);
        assertEquals(25, new Temperature(77, "Fahrenheit").getCelsius(), 0.001);
        assertEquals(25, new Temperature(298.15, "K").getCelsius(), 0.001);
        assertEquals(25, new Temperature(298.15, "Kelvin").getCelsius(), 0.001);
    }

    @Test
    void belowAbsoluteZeroLeavesZero() {
        Temperature t = new Temperature(-300);
        assertEquals(0, t.getCelsius(), 0.001);
    }

    @Test
    void belowAbsoluteZeroFahrenheitRejected() {
        Temperature t = new Temperature(-500, "F");
        assertEquals(0, t.getCelsius(), 0.001);
    }

    @Test
    void unknownUnitLeavesZero() {
        Temperature t = new Temperature(100, "Rankine");
        assertEquals(0, t.getCelsius(), 0.001);
    }
}
