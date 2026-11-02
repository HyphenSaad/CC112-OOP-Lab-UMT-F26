import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class RectangleDemo {

    public static void main(String[] args) {
        start();

        Session.me()
                .name("Ayesha Khan")
                .rollNumber("2024-CS-001")
                .degree("BSCS")
                .batch("2024")
                .section("A");

        double length = readDouble("Length", 0.0001, Double.MAX_VALUE);
        double width  = readDouble("Width",  0.0001, Double.MAX_VALUE);

        Rectangle r = new Rectangle();
        r.setLength(length);
        r.setWidth(width);

        Console.println();
        Console.println("Area:      " + decimal(r.area(), 2));
        Console.println("Perimeter: " + decimal(r.perimeter(), 2));
        Console.println("Is square: " + r.isSquare());

        double before = r.getLength();
        r.setLength(0);
        if (r.getLength() == before) {
            Console.warn("setLength(0) was rejected -- length is still " + before);
        }

        Console.println();
        Console.watermark();
    }
}
