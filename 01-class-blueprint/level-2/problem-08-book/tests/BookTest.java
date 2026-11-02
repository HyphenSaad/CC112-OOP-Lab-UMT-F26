import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setTitle("Clean Code");
        book.setAuthor("Robert Martin");
        book.setPages(464);
    }

    @Test
    void validBookReportsIsValid() {
        assertTrue(book.isValid());
    }

    @Test
    void attributesStoredCorrectly() {
        assertEquals("Clean Code", book.getTitle());
        assertEquals("Robert Martin", book.getAuthor());
        assertEquals(464, book.getPages());
    }

    @Test
    void isLongAbove300Pages() {
        assertTrue(book.isLong());
        book.setPages(300);
        assertFalse(book.isLong());
    }

    @Test
    void readingMinutesCalculatesCorrectly() {
        assertEquals(464, book.readingMinutes(60));
        assertEquals(232, book.readingMinutes(30));
    }

    @Test
    void invalidSecondsPerPageReturnsZero() {
        assertEquals(0, book.readingMinutes(0));
        assertEquals(0, book.readingMinutes(-10));
    }

    @Test
    void blankTitleRejected() {
        book.setTitle("   ");
        assertEquals("Clean Code", book.getTitle());
    }

    @Test
    void zeroPagesRejected() {
        book.setPages(0);
        assertEquals(464, book.getPages());
    }
}
