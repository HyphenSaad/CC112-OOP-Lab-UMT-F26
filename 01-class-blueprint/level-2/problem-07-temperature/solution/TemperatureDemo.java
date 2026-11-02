import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class TemperatureDemo {

    public static void main(String[] args) {
        start();

        Session.me()
                .name("John Baba")
                .rollNumber("2024-CS-001")
                .degree("BSCS")
                .batch("2024")
                .section("A");

        if (!Session.me().isComplete()) {
            Console.warn("Session incomplete. Missing: " + Session.me().missingFields());
        }

        double celsius = readDouble("Celsius", -273.15, Double.MAX_VALUE);

        Temperature t = new Temperature();
        t.setCelsius(celsius);

        Console.println();
        Console.println("Fahrenheit: " + decimal(t.toFahrenheit(), 2));
        Console.println("Kelvin:     " + decimal(t.toKelvin(), 2));
        Console.println("Freezing?   " + t.isFreezing());
        Console.println("Boiling?    " + t.isBoiling());

        double before = t.getCelsius();
        t.setCelsius(-300);
        if (t.getCelsius() == before) {
            Console.warn("setCelsius(-300) was rejected — below absolute zero");
        }

        Console.println();
        Console.watermark();
    }
}
