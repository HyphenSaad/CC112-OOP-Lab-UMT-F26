import chirp.text.Check;
import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String name;
    private final List<String> songs = new ArrayList<>();

    public Playlist(String name) {
        if (Check.notBlank(name)) this.name = name;
    }

    public Playlist(Playlist other) {
        if (other != null) {
            this.name = other.name;
            this.songs.addAll(other.songs);   // deep copy — not a reference copy
        }
    }

    public String getName() { return name; }

    public List<String> getSongs() { return List.copyOf(songs); }

    public boolean addSong(String title) {
        if (!Check.notBlank(title)) return false;
        if (songs.contains(title)) return false;
        songs.add(title);
        return true;
    }

    public boolean removeSong(String title) {
        return songs.remove(title);
    }

    public int songCount() { return songs.size(); }

    public boolean isValid() {
        return Check.notBlank(name);
    }
}
