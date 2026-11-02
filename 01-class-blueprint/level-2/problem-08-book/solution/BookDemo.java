import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class BookDemo {

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

        String title = readNonEmpty("Title");
        String author = readNonEmpty("Author");
        int pages = readInt("Pages", 1, Integer.MAX_VALUE);

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPages(pages);

        Console.println();
        Console.println("Book: " + book.getTitle() + " by " + book.getAuthor());
        Console.println("Pages: " + book.getPages());
        Console.println("Is long? " + book.isLong());
        Console.println("Reading time (60s/page): " + book.readingMinutes(60) + " minutes");
        Console.println("Reading time (30s/page): " + book.readingMinutes(30) + " minutes");
        Console.println("Reading time (0s/page):  " + book.readingMinutes(0)
                + " minutes (invalid input, defaults to 0)");

        Console.println();
        Console.watermark();
    }
}
