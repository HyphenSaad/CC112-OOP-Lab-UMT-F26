import chirp.session.Session;
import static chirp.Chirp.*;

public class CircleDemo {

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
        // Your Circle class must expose: setRadius, getRadius,
        // diameter, area, circumference, isValid.
        // ============================================================
        // Step 1: Ask for the radius with readDouble and min = 0.0001.
        
        
        // Step 2: Create a Circle and set the radius.
        
        
        // Step 3: Print a blank line, then three lines with
        //         decimal(…, 2) formatting:
        //           "Diameter:      " + decimal(c.diameter(), 2)
        //           "Area:          " + decimal(c.area(), 2)
        //           "Circumference: " + decimal(c.circumference(), 2)
        
        
        // Step 4: Verify the safety net. Call c.setRadius(-3) — it must
        //         be rejected silently, so getRadius() is unchanged.
        //         If unchanged, warn the user.
        
        
        // ============================================================
        // END — the block below prints your signature and closes the demo.
        // ============================================================

        println();
        watermark();
    }
}
