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

        // ============================================================
        // START HERE — write the demo body below.
        // Your LightBulb class must expose: turnOn, turnOff, toggle,
        // isOn, isValid.
        // ============================================================
        // Step 1: Create a LightBulb — it starts off.
        //         Print "Initial: on? " + bulb.isOn().
        
        
        // Step 2: Call bulb.turnOn() and print
        //         "turnOn()  -> on? " + bulb.isOn().
        
        
        // Step 3: Call bulb.toggle() twice, printing after each:
        //         "toggle()  -> on? " + bulb.isOn().
        
        
        // Step 4: Call bulb.turnOff() and print
        //         "turnOff() -> on? " + bulb.isOn().
        
        
        // ============================================================
        // END — the block below prints your signature and closes the demo.
        // ============================================================

        println();
        watermark();
    }
}
