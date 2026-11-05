import chirp.session.Session;
import static chirp.Chirp.*;

public class LightBulbDemo {

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

        LightBulb bulb = new LightBulb();
        println("Initial: on? " + bulb.isOn());
        bulb.turnOn();
        println("turnOn()  -> on? " + bulb.isOn());
        bulb.toggle();
        println("toggle()  -> on? " + bulb.isOn());
        bulb.toggle();
        println("toggle()  -> on? " + bulb.isOn());
        bulb.turnOff();
        println("turnOff() -> on? " + bulb.isOn());

        println();
        watermark();
    }
}
