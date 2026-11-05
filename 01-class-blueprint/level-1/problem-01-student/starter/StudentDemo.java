import chirp.session.Session;
import static chirp.Chirp.*;

public class StudentDemo {

    public static void main(String[] args) {
        start();

        // Sign your work — replace with your real information.
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
        // Your Student class must expose: setName, setAge, setGpa,
        // getAge, introduce, isValid (see README.md for details).
        // ============================================================
        // Step 1: Ask the user for a non-blank name.
        //         Use readNonEmpty — it re-prompts until the input has
        //         at least one non-whitespace character.
        

        // Step 2: Ask for age (between 1 and 120) and GPA (between 0.0
        //         and 4.0). Use readInt and readDouble with min/max.
        

        // Step 3: Create a new Student and set all three attributes
        //         using the setters.
        

        // Step 4: Print a blank line, then x.introduce(), then
        //         "Valid: " + x.isValid() on the next line.
        // where, x will be name of your student object
        

        // Step 5: Verify the safety net. Call x.setAge(200) — the setter
        //         must silently reject it, so x.getAge() should still
        //         return the previous value. If unchanged, warn the user.
        

        // ============================================================
        // END — the block below prints your signature and closes the demo.
        // ============================================================

        println();
        watermark();
    }
}
