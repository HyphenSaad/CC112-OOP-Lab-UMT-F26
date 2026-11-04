import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class InventoryDemo {

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

        String invName = readNonEmpty("Inventory name");

        Inventory original = new Inventory(invName);
        Console.println();
        Console.println("Created original.");

        String item1 = readNonEmpty("First item");
        int qty1 = readInt("Quantity for " + item1, 0, 100000);
        original.addItem(item1, qty1);

        String item2 = readNonEmpty("Second item");
        int qty2 = readInt("Quantity for " + item2, 0, 100000);
        original.addItem(item2, qty2);

        Console.println("Original items: " + original.itemCount());
        for (int i = 0; i < original.itemCount(); i++) {
            Console.println("  [" + i + "] " + original.getItems().get(i)
                    + " = " + original.getQuantity(i));
        }

        Inventory copy = new Inventory(original);
        Console.println();
        Console.println("Copy created from original.");
        Console.println("Copy items: " + copy.itemCount());
        for (int i = 0; i < copy.itemCount(); i++) {
            Console.println("  [" + i + "] " + copy.getItems().get(i)
                    + " = " + copy.getQuantity(i));
        }

        int newQty = readInt("Update original item 0 to", 0, 100000);
        original.updateQuantity(0, newQty);
        Console.println();
        Console.println("Original item 0: " + original.getQuantity(0));
        Console.println("Copy item 0:     " + copy.getQuantity(0) + "   <-- array deep-copied!");

        String item3 = readNonEmpty("Add to original");
        int qty3 = readInt("Quantity for " + item3, 0, 100000);
        original.addItem(item3, qty3);
        Console.println();
        Console.println("Original items: " + original.itemCount());
        Console.println("Copy items:     " + copy.itemCount() + "   <-- list deep-copied!");

        Console.println();
        Console.watermark();
    }
}
