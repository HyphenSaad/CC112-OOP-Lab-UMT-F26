import chirp.session.Session;
import static chirp.Chirp.*;

public class CircleDemo {

    public static void main(String[] args) {
        start();

        Session.me()
                .name("Your Name")
                .rollNumber("2024-CS-XXX")
                .degree("BSCS")
                .batch("2024")
                .section("A");

        if (!Session.me().isComplete()) {
            warn("Session incomplete. Missing: " + Session.me().missingFields());
        }

        double r = readDouble("Radius", 0.0001, Double.MAX_VALUE);

        Circle c = new Circle();
        c.setRadius(r);

        println();
        println("Diameter:      " + decimal(c.diameter(), 2));
        println("Area:          " + decimal(c.area(), 2));
        println("Circumference: " + decimal(c.circumference(), 2));

        double before = c.getRadius();
        c.setRadius(-3);
        if (c.getRadius() == before) {
            warn("setRadius(-3) was rejected — radius is still " + decimal(before, 2));
        }

        println();
        watermark();
    }
}
