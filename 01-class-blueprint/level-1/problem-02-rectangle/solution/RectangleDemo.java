import chirp.session.Session;
import static chirp.Chirp.*;

public class RectangleDemo {

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

        double length = readDouble("Length", 0.01, Double.MAX_VALUE);
        double width = readDouble("Width", 0.01, Double.MAX_VALUE);

        Rectangle r = new Rectangle();
        r.setLength(length);
        r.setWidth(width);

        println();
        println("Area:      " + decimal(r.area(), 2));
        println("Perimeter: " + decimal(r.perimeter(), 2));
        println("Is square: " + r.isSquare());

        double before = r.getLength();
        r.setLength(0);
        if (r.getLength() == before) {
            warn("setLength(0) was rejected — length is still " + decimal(before, 2));
        }

        println();
        watermark();
    }
}
