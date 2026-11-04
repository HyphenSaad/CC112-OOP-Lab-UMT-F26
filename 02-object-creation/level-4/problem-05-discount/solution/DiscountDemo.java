import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class DiscountDemo {

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

        Console.println("--- Percentage discount ---");
        double pctAmt = readDouble("Amount", 0, 100);
        Discount d1 = new Discount(pctAmt, "PERCENT");
        Console.println("Discount: " + percent(d1.getAmount(), 2) + " off ("
                + d1.getType() + ")");

        Console.println();
        Console.println("--- Fixed discount ---");
        double fixedAmt = readDouble("Amount", 0, Double.MAX_VALUE);
        Discount d2 = new Discount(fixedAmt, "FIXED");
        Console.println("Discount: " + rupees(d2.getAmount()) + " off ("
                + d2.getType() + ")");

        Console.println();
        double subtotal = readDouble("Subtotal to apply discounts to", 0, Double.MAX_VALUE);
        Console.println("Subtotal: " + rupees(subtotal));
        Console.println("After 10%: " + rupees(d1.apply(subtotal)));
        Console.println("After fixed: " + rupees(d2.apply(subtotal)));

        Console.println();
        Console.println("--- Invalid discount ---");
        Discount bad = new Discount(150, "PERCENT");
        Console.println("150% discount — isValid " + bad.isValid());
        Console.println("applied to subtotal: " + rupees(bad.apply(subtotal))
                + " (unchanged)");

        Console.println();
        Console.watermark();
    }
}
