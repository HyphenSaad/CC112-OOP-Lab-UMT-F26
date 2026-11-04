import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class CircleDemo {

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

        double radius = readDouble("Radius", 0.01, Double.MAX_VALUE);

        Circle c = new Circle(radius);

        Console.println();
        Console.println("Diameter: " + decimal(c.diameter(), 2));
        Console.println("Area: " + decimal(c.area(), 2));
        Console.println("Circumference: " + decimal(c.circumference(), 2));

        Circle broken = new Circle(-3);
        Console.warn("Constructed with bad data — isValid? " + broken.isValid());

        Console.println();
        Console.watermark();
    }
}
