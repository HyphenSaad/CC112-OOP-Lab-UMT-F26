import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class ParkingSlotDemo {

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

        int slotNum = readInt("Slot number", 1, 999);

        ParkingSlot slot = new ParkingSlot();
        slot.setSlotNumber(slotNum);

        Console.println();
        Console.println("Slot " + slot.getSlotNumber() + ", available: " + slot.isAvailable());

        String plate1 = readNonEmpty("First plate");
        Console.println("Park (" + plate1 + "): " + slot.park(plate1)
                + " — plate: " + slot.getVehiclePlate());

        String plate2 = readNonEmpty("Second plate");
        Console.println("Park (" + plate2 + "): " + slot.park(plate2)
                + " (slot is " + (slot.isAvailable() ? "free" : "occupied") + ")");

        Console.println("Unpark: " + slot.unpark()
                + " — available again: " + slot.isAvailable());
        Console.println("Unpark again: " + slot.unpark());

        Console.println();
        Console.watermark();
    }
}
