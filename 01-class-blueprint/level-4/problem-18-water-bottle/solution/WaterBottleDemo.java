import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class WaterBottleDemo {

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

        int capacity = readInt("Capacity (ml)", 1, 100000);

        WaterBottle bottle = new WaterBottle();
        bottle.setCapacityMl(capacity);

        Console.println();
        Console.println("Bottle: " + bottle.getCapacityMl() + "ml capacity, currently "
                + bottle.getCurrentMl() + "ml");

        int fillAmt = readInt("Fill (ml)", 1, 100000);
        bottle.fill(fillAmt);
        Console.println("Fill: " + bottle.getCurrentMl() + " / " + bottle.getCapacityMl());

        bottle.fill(capacity);  // overflow
        Console.println("Fill: " + bottle.getCurrentMl() + " / " + bottle.getCapacityMl()
                + " (capped)");
        Console.println("isFull? " + bottle.isFull());

        int drinkAmt = readInt("Drink (ml)", 1, 100000);
        int got = bottle.drink(drinkAmt);
        Console.println("Drink: got " + got + ", now "
                + bottle.getCurrentMl() + " / " + bottle.getCapacityMl());

        got = bottle.drink(capacity);
        Console.println("Drink: got " + got + ", now "
                + bottle.getCurrentMl() + " / " + bottle.getCapacityMl());
        Console.println("isEmpty? " + bottle.isEmpty());

        Console.println();
        Console.watermark();
    }
}
