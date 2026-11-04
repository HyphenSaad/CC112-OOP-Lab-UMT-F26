import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class PlaylistDemo {

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

        String name = readNonEmpty("Playlist name");

        Playlist original = new Playlist(name);
        Console.println();
        Console.println("Created original.");

        String song1 = readNonEmpty("Song 1");
        original.addSong(song1);
        String song2 = readNonEmpty("Song 2");
        original.addSong(song2);
        Console.println("Original songs (" + original.songCount() + "): "
                + String.join(", ", original.getSongs()));

        Playlist copy = new Playlist(original);
        Console.println();
        Console.println("Copy created from original.");
        Console.println("Copy songs (" + copy.songCount() + "): "
                + String.join(", ", copy.getSongs()));

        String song3 = readNonEmpty("Add to original");
        original.addSong(song3);
        Console.println();
        Console.println("Original songs (" + original.songCount() + "): "
                + String.join(", ", original.getSongs()));
        Console.println("Copy songs (" + copy.songCount() + "): "
                + String.join(", ", copy.getSongs()) + "   <-- independent!");

        Console.println();
        Console.watermark();
    }
}
