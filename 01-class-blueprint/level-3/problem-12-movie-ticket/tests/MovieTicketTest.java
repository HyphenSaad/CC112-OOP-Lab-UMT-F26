import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MovieTicketTest {

    private MovieTicket ticket;

    @BeforeEach
    void setUp() {
        ticket = new MovieTicket();
        ticket.setMovieName("Inception");
        ticket.setSeatNumber(25);
        ticket.setPrice(1200);
    }

    @Test
    void validTicketReportsIsValid() {
        assertTrue(ticket.isValid());
    }

    @Test
    void startsAvailable() {
        assertEquals("Available", ticket.getStatus());
    }

    @Test
    void bookSetsBooked() {
        assertTrue(ticket.book());
        assertEquals("Booked", ticket.getStatus());
    }

    @Test
    void bookWhenBookedReturnsFalse() {
        ticket.book();
        assertFalse(ticket.book());
        assertEquals("Booked", ticket.getStatus());
    }

    @Test
    void cancelSetsAvailable() {
        ticket.book();
        assertTrue(ticket.cancel());
        assertEquals("Available", ticket.getStatus());
    }

    @Test
    void cancelWhenAvailableReturnsFalse() {
        assertFalse(ticket.cancel());
    }

    @Test
    void getStatusReflectsState() {
        assertEquals("Available", ticket.getStatus());
        ticket.book();
        assertEquals("Booked", ticket.getStatus());
        ticket.cancel();
        assertEquals("Available", ticket.getStatus());
    }

    @Test
    void invalidSeatNumberRejected() {
        ticket.setSeatNumber(0);
        assertEquals(25, ticket.getSeatNumber());
        ticket.setSeatNumber(150);
        assertEquals(25, ticket.getSeatNumber());
    }
}
