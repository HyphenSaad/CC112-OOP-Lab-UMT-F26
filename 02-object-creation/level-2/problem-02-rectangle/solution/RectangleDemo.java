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

        Rectangle original = new Rectangle(length, width);
        Rectangle copy = new Rectangle(original);

        Console.println();
        Console.println("Original: " + decimal(original.getLength(), 2) + " x "
                + decimal(original.getWidth(), 2) + " — area " + decimal(original.area(), 2));
        Console.println("Copy:     " + decimal(copy.getLength(), 2) + " x "
                + decimal(copy.getWidth(), 2) + " — area " + decimal(copy.area(), 2));
        Console.println("Both isValid: " + original.isValid() + " / " + copy.isValid());

        Console.println();
        Console.watermark();
    }
}
