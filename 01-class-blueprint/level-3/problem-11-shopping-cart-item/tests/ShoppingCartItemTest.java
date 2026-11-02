import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartItemTest {

    private ShoppingCartItem item;

    @BeforeEach
    void setUp() {
        item = new ShoppingCartItem();
        item.setName("Notebook");
        item.setPrice(150);
        item.setQuantity(6);
    }

    @Test
    void validItemReportsIsValid() {
        assertTrue(item.isValid());
    }

    @Test
    void lineTotalWithoutDiscount() {
        assertEquals(900, item.lineTotal(), 0.001);
    }

    @Test
    void isBulkOrderAtFiveOrMore() {
        assertTrue(item.isBulkOrder());
        item.setQuantity(4);
        assertFalse(item.isBulkOrder());
    }

    @Test
    void applyDiscountChangesLineTotal() {
        item.applyDiscount(10);
        assertEquals(810, item.lineTotal(), 0.001);
    }

    @Test
    void invalidDiscountRejected() {
        item.applyDiscount(150);
        assertEquals(0, item.getDiscount(), 0.001);
        item.applyDiscount(-5);
        assertEquals(0, item.getDiscount(), 0.001);
    }

    @Test
    void zeroPriceRejected() {
        item.setPrice(0);
        assertEquals(150, item.getPrice(), 0.001);
    }

    @Test
    void zeroQuantityRejected() {
        item.setQuantity(0);
        assertEquals(6, item.getQuantity());
    }

    @Test
    void blankNameRejected() {
        item.setName("   ");
        assertEquals("Notebook", item.getName());
    }
}
