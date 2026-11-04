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

        Console.println("--- Create a square ---");
        double side = readDouble("Side", 0.01, Double.MAX_VALUE);
        Rectangle square = new Rectangle(side);
        Console.println("Square: " + decimal(square.getLength(), 2) + " x "
                + decimal(square.getWidth(), 2) + " — area " + decimal(square.area(), 2)
                + ", isSquare " + square.isSquare());

        Console.println();
        Console.println("--- Create a general rectangle ---");
        double length = readDouble("Length", 0.01, Double.MAX_VALUE);
        double width = readDouble("Width", 0.01, Double.MAX_VALUE);
        Rectangle rect = new Rectangle(length, width);
        Console.println("Rectangle: " + decimal(rect.getLength(), 2) + " x "
                + decimal(rect.getWidth(), 2) + " — area " + decimal(rect.area(), 2)
                + ", isSquare " + rect.isSquare());

        Console.println();
        Console.println("Both isValid: " + square.isValid() + " / " + rect.isValid());

        Console.println();
        Console.watermark();
    }
}
