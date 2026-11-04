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

        Console.println("--- Celsius ---");
        double c = readDouble("Celsius", -273.15, Double.MAX_VALUE);
        Temperature t1 = new Temperature(c);
        show(t1);

        Console.println();
        Console.println("--- Fahrenheit ---");
        double f = readDouble("Fahrenheit", -459.67, Double.MAX_VALUE);
        Temperature t2 = new Temperature(f, "F");
        show(t2);

        Console.println();
        Console.println("--- Below absolute zero (rejected) ---");
        Temperature bad = new Temperature(-300);
        Console.println("celsius = " + decimal(bad.getCelsius(), 2)
                + "  (invalid input, constructor kept default 0)");

        Console.println();
        Console.watermark();
    }

    private static void show(Temperature t) {
        Console.println(decimal(t.getCelsius(), 2) + " C = "
                + decimal(t.getFahrenheit(), 2) + " F = "
                + decimal(t.getKelvin(), 2) + " K");
    }
}
