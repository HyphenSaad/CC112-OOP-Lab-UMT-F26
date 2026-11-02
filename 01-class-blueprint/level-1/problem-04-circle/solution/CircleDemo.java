import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class CircleDemo {

    public static void main(String[] args) {
        start();

        Session.me()
                .name("Ayesha Khan")
                .rollNumber("2024-CS-001")
                .degree("BSCS")
                .batch("2024")
                .section("A");

        double r = readDouble("Radius", 0.0001, Double.MAX_VALUE);

        Circle c = new Circle();
        c.setRadius(r);

        Console.println();
        Console.println("Diameter:      " + decimal(c.diameter(), 2));
        Console.println("Area:          " + decimal(c.area(), 2));
        Console.println("Circumference: " + decimal(c.circumference(), 2));

        double before = c.getRadius();
        c.setRadius(-3);
        if (c.getRadius() == before) {
            Console.warn("setRadius(-3) was rejected -- radius is still " + before);
        }

        Console.println();
        Console.watermark();
    }
}
