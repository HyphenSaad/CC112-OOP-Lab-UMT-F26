import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class ShoppingCartItemDemo {

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

        String name = readNonEmpty("Item name");
        double price = readDouble("Unit price", 0.01, Double.MAX_VALUE);
        int qty = readInt("Quantity", 1, Integer.MAX_VALUE);

        ShoppingCartItem item = new ShoppingCartItem();
        item.setName(name);
        item.setPrice(price);
        item.setQuantity(qty);

        Console.println();
        Console.println("Item: " + item.getName() + " x " + item.getQuantity()
                + " @ " + rupees(item.getPrice()));
        Console.println("Line total: " + rupees(item.lineTotal()));
        Console.println("Bulk order? " + item.isBulkOrder());

        double percent = readDouble("Discount (%)", 0, 100);
        item.applyDiscount(percent);
        Console.println("After " + decimal(percent, 0) + "%: " + rupees(item.lineTotal()));

        double before = item.lineTotal();
        item.applyDiscount(150);
        if (item.lineTotal() == before) {
            Console.warn("applyDiscount(150) was rejected — discount is still "
                    + decimal(item.getDiscount(), 0) + "%");
        }

        Console.println();
        Console.watermark();
    }
}
