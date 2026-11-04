import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureTest {

    @Test
    void celsiusConstructorStoresValue() {
        assertEquals(25, new Temperature(25).getCelsius(), 0.001);
    }

    @Test
    void fahrenheitConstructorDelegatesAndConverts() {
        assertEquals(100, new Temperature(212, "F").getCelsius(), 0.001);
    }

    @Test
    void kelvinConstructorDelegatesAndConverts() {
        assertEquals(25, new Temperature(298.15, "K").getCelsius(), 0.001);
    }

    @Test
    void unitLongNamesAccepted() {
        assertEquals(25, new Temperature(25, "Celsius").getCelsius(), 0.001);
        assertEquals(25, new Temperature(77, "Fahrenheit").getCelsius(), 0.001);
        assertEquals(25, new Temperature(298.15, "Kelvin").getCelsius(), 0.001);
    }

    @Test
    void unknownUnitResultsInDefaultZero() {
        assertEquals(0, new Temperature(100, "Rankine").getCelsius(), 0.001);
    }

    @Test
    void belowAbsoluteZeroResultsInDefaultZero() {
        assertEquals(0, new Temperature(-300).getCelsius(), 0.001);
    }

    @Test
    void validTemperatureConvertsCorrectly() {
        Temperature t = new Temperature(100);
        assertEquals(212, t.getFahrenheit(), 0.001);
        assertEquals(373.15, t.getKelvin(), 0.001);
    }
}
