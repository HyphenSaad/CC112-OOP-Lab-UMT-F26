import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {

    @Test
    void copyConstructorCopiesName() {
        Playlist original = new Playlist("Workout Mix");
        assertEquals("Workout Mix", new Playlist(original).getName());
    }

    @Test
    void copyConstructorHasSameSongs() {
        Playlist original = new Playlist("Workout Mix");
        original.addSong("Eye of the Tiger");
        original.addSong("Stronger");
        Playlist copy = new Playlist(original);
        assertEquals(2, copy.songCount());
        assertTrue(copy.getSongs().contains("Eye of the Tiger"));
        assertTrue(copy.getSongs().contains("Stronger"));
    }

    @Test
    void copyConstructorDeepCopiesList() {
        Playlist original = new Playlist("Workout Mix");
        original.addSong("Eye of the Tiger");
        Playlist copy = new Playlist(original);
        original.addSong("Stronger");
        assertEquals(2, original.songCount());
        assertEquals(1, copy.songCount(), "copy must not see songs added to original");
    }

    @Test
    void addSongToCopyDoesNotAffectOriginal() {
        Playlist original = new Playlist("Workout Mix");
        original.addSong("Eye of the Tiger");
        Playlist copy = new Playlist(original);
        copy.addSong("Believer");
        assertEquals(1, original.songCount());
        assertEquals(2, copy.songCount());
    }

    @Test
    void getSongsReturnsImmutableCopy() {
        Playlist original = new Playlist("Workout Mix");
        original.addSong("Eye of the Tiger");
        assertThrows(UnsupportedOperationException.class,
                () -> original.getSongs().add("Hack"));
    }

    @Test
    void copyOfNullHasDefaults() {
        Playlist copy = new Playlist((Playlist) null);
        assertNull(copy.getName());
        assertEquals(0, copy.songCount());
        assertFalse(copy.isValid());
    }
}
