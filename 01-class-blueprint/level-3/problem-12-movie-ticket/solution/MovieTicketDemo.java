import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class MovieTicketDemo {

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

        String movie = readNonEmpty("Movie");
        int seat = readInt("Seat number", 1, 100);
        double price = readDouble("Price", 0.01, Double.MAX_VALUE);

        MovieTicket t = new MovieTicket();
        t.setMovieName(movie);
        t.setSeatNumber(seat);
        t.setPrice(price);

        Console.println();
        Console.println("Status: " + t.getStatus());

        Console.println("Book: " + t.book());
        Console.println("Status: " + t.getStatus());
        Console.println("Book again: " + t.book());

        Console.println("Cancel: " + t.cancel());
        Console.println("Status: " + t.getStatus());
        Console.println("Cancel again: " + t.cancel());

        Console.println();
        Console.watermark();
    }
}
