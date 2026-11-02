import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class EmailValidatorDemo {

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

        String email = readString("Email");

        EmailValidator v = new EmailValidator();
        v.setEmail(email);

        Console.println();
        Console.println("Valid email? " + v.isValidEmail());
        Console.println("Normalized:  " + v.normalize());
        Console.println("Domain:      " + (v.domain().isEmpty() ? "(none)" : v.domain()));

        v.setEmail("not-an-email");
        Console.println();
        Console.println("Set to \"not-an-email\"");
        Console.println("Valid email? " + v.isValidEmail());
        Console.println("Domain:      " + (v.domain().isEmpty() ? "(none)" : v.domain()));

        Console.println();
        Console.watermark();
    }
}
