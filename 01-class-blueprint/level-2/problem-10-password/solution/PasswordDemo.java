import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class PasswordDemo {

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

        String pwd = readString("Password");

        Password password = new Password();
        password.setValue(pwd);

        Console.println();
        Console.println("Length:    " + password.length());
        Console.println("Has digit: " + password.hasDigit());
        Console.println("Is strong: " + password.isStrong());
        Console.println("Masked:    " + password.masked());

        Console.println();
        Console.watermark();
    }
}
