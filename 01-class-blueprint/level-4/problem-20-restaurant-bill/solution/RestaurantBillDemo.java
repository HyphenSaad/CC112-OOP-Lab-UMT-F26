import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class RestaurantBillDemo {

    public static void main(String[] args) {
        start();

        Session.me()
                .name("Ayesha Khan")
                .rollNumber("2024-CS-001")
                .degree("BSCS")
                .batch("2024")
                .section("A");

        if (!Session.me().isComplete()) {
            Console.warn("Session incomplete. Missing: " + Session.me().missingFields());
        }

        double subtotal = readDouble("Subtotal", 0, Double.MAX_VALUE);
        double taxPct = readDouble("Tax %", 0, 30);
        double servicePct = readDouble("Service %", 0, 20);

        RestaurantBill bill = new RestaurantBill();
        bill.setSubtotal(subtotal);
        bill.setTaxPercent(taxPct);
        bill.setServicePercent(servicePct);

        Console.println();
        Console.println("Bill — subtotal " + rupees(bill.getSubtotal()));
        Console.println("Tax:     " + rupees(bill.tax())
                + "  (" + percent(bill.getTaxPercent(), 1) + ")");
        Console.println("Service: " + rupees(bill.service())
                + "  (" + percent(bill.getServicePercent(), 1) + ")");
        Console.println("Total:   " + rupees(bill.total()));

        Console.println();
        Console.watermark();
    }
}
