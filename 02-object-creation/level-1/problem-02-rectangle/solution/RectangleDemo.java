import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class RectangleDemo {

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

        double length = readDouble("Length", 0.01, Double.MAX_VALUE);
        double width = readDouble("Width", 0.01, Double.MAX_VALUE);

        Rectangle r = new Rectangle(length, width);

        Console.println();
        Console.println("Area: " + decimal(r.area(), 2));
        Console.println("Perimeter: " + decimal(r.perimeter(), 2));
        Console.println("Is square: " + r.isSquare());

        Rectangle broken = new Rectangle(0, -2.5);
        Console.warn("Constructed with bad data — isValid? " + broken.isValid());

        Console.println();
        Console.watermark();
    }
}
