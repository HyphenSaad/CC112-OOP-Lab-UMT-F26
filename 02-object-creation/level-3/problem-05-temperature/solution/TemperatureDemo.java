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

        Console.println("--- Celsius reading ---");
        double c = readDouble("Celsius", -273.15, Double.MAX_VALUE);
        Temperature t1 = new Temperature(c);
        Console.println("Temperature: " + decimal(t1.getCelsius(), 2) + " C  (= "
                + decimal(t1.getFahrenheit(), 2) + " F, "
                + decimal(t1.getKelvin(), 2) + " K)");

        Console.println();
        Console.println("--- Fahrenheit reading ---");
        double f = readDouble("Fahrenheit", -459.67, Double.MAX_VALUE);
        Temperature t2 = new Temperature(f, "F");
        Console.println("Temperature: " + decimal(t2.getCelsius(), 2) + " C  (= "
                + decimal(t2.getFahrenheit(), 2) + " F, "
                + decimal(t2.getKelvin(), 2) + " K)");

        Console.println();
        Console.watermark();
    }
}
