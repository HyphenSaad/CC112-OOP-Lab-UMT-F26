import chirp.session.Session;
import static chirp.Chirp.*;

public class RectangleDemo {

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
        // Your Rectangle class must expose: setLength, setWidth,
        // getLength, area, perimeter, isSquare, isValid.
        // ============================================================
        // Step 1: Ask for length and width. Both must be > 0. Use
        //         readDouble with min = 0.01.
                
        
        // Step 2: Create a Rectangle and set both attributes.
        
        
        // Step 3: Print a blank line, then three lines with decimal(…, 2)
        //         formatting:
        //           "Area:      " + decimal(r.area(), 2)
        //           "Perimeter: " + decimal(r.perimeter(), 2)
        //           "Is square: " + r.isSquare()
        
        
        // Step 4: Verify the safety net. Call r.setLength(0) — it must
        //         be rejected silently, so getLength() is unchanged.
        //         If unchanged, warn the user.
        
        
        // ============================================================
        // END — the block below prints your signature and closes the demo.
        // ============================================================

        println();
        watermark();
    }
}
