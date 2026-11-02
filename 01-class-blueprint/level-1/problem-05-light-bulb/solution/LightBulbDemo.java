import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class LightBulbDemo {

    public static void main(String[] args) {
        start();

        Session.me()
                .name("Ayesha Khan")
                .rollNumber("2024-CS-001")
                .degree("BSCS")
                .batch("2024")
                .section("A");

        LightBulb bulb = new LightBulb();

        Console.println("Initial: on? " + bulb.isOn());

        bulb.turnOn();
        Console.println("turnOn()  -> on? " + bulb.isOn());

        bulb.toggle();
        Console.println("toggle()  -> on? " + bulb.isOn());

        bulb.toggle();
        Console.println("toggle()  -> on? " + bulb.isOn());

        bulb.turnOff();
        Console.println("turnOff() -> on? " + bulb.isOn());

        Console.println();
        Console.watermark();
    }
}
