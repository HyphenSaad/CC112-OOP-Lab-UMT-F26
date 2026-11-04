import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class MoneyDemo {

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

        Console.println("--- Whole rupees ---");
        long rupees1 = readLong("Rupees", 0, Long.MAX_VALUE / 200);
        Money m1 = new Money(rupees1);
        Console.println("Money: Rs." + m1.getRupees() + "." + pad2(m1.getPaise())
                + "  (total paise: " + m1.totalPaise() + ")");

        Console.println();
        Console.println("--- Rupees + paise ---");
        long rupees2 = readLong("Rupees", 0, Long.MAX_VALUE / 200);
        long paise2 = readLong("Paise", 0, 99);
        Money m2 = new Money(rupees2, paise2);
        Console.println("Money: Rs." + m2.getRupees() + "." + pad2(m2.getPaise())
                + "  (total paise: " + m2.totalPaise() + ")");

        Console.println();
        Money sum = m1.plus(m2);
        Console.println("Sum of the two: Rs." + sum.getRupees() + "." + pad2(sum.getPaise()));

        Console.println();
        Console.watermark();
    }

    private static String pad2(long n) {
        return n < 10 ? "0" + n : "" + n;
    }
}
