// Problem 4 — Playlist (Deep Copy)
// Fill in the copy constructor. See README.md for requirements.

import chirp.text.Check;
import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String name;
    private final List<String> songs = new ArrayList<>();

    public Playlist(String name) {
        if (Check.notBlank(name)) this.name = name;
    }

    // TODO: copy constructor — copy name AND deep-copy the song list
    // Wrong: this.songs = other.songs;
    // Right: this.songs.addAll(other.songs);
    public Playlist(Playlist other) {
    }

    public String getName() { return name; }

    public List<String> getSongs() { return List.copyOf(songs); }

    public boolean addSong(String title) {
        // TODO: if title is not blank and not already in songs -> add, return true
        // else return false
        return false;
    }

    public boolean removeSong(String title) {
        return songs.remove(title);
    }

    public int songCount() { return songs.size(); }

    public boolean isValid() {
        return Check.notBlank(name);
    }
}
