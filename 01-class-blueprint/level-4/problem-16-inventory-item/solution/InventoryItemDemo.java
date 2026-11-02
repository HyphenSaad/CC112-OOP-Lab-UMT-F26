import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class InventoryItemDemo {

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

        String id = readNonEmpty("Item ID");
        String name = readNonEmpty("Item name");
        double price = readDouble("Unit price", 0.01, Double.MAX_VALUE);
        int reorderLevel = readInt("Reorder level", 0, 100000);

        InventoryItem item = new InventoryItem();
        item.setId(id);
        item.setName(name);
        item.setUnitPrice(price);
        item.setReorderLevel(reorderLevel);

        Console.println();
        Console.println("Item: " + item.getName() + " (" + item.getId() + ") @ "
                + rupees(item.getUnitPrice()));
        Console.println("Initial quantity: " + item.getQuantity()
                + ", reorder level: " + item.getReorderLevel());
        Console.println("isLowStock? " + item.isLowStock());
        Console.println("totalValue: " + rupees(item.totalValue()));

        int amount = readInt("Reorder amount", 1, 100000);
        item.reorder(amount);
        Console.println("After reorder: " + item.getQuantity());
        Console.println("isLowStock? " + item.isLowStock());
        Console.println("totalValue: " + rupees(item.totalValue()));

        int before = item.getQuantity();
        item.reorder(0);
        if (item.getQuantity() == before) {
            Console.warn("reorder(0) was rejected — quantity is still " + before);
        }

        Console.println();
        Console.watermark();
    }
}
