import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryItemTest {

    private InventoryItem item;

    @BeforeEach
    void setUp() {
        item = new InventoryItem();
        item.setId("ST-101");
        item.setName("Pen");
        item.setUnitPrice(25);
        item.setReorderLevel(5);
    }

    @Test
    void validItemReportsIsValid() {
        assertTrue(item.isValid());
    }

    @Test
    void startsAtZeroQuantity() {
        assertEquals(0, item.getQuantity());
    }

    @Test
    void reorderIncreasesQuantity() {
        item.reorder(20);
        assertEquals(20, item.getQuantity());
        item.reorder(5);
        assertEquals(25, item.getQuantity());
    }

    @Test
    void reorderZeroOrNegativeRejected() {
        item.reorder(10);
        item.reorder(0);
        assertEquals(10, item.getQuantity());
        item.reorder(-5);
        assertEquals(10, item.getQuantity());
    }

    @Test
    void isLowStockWhenQuantityAtOrBelowLevel() {
        assertTrue(item.isLowStock());
        item.reorder(5);
        assertTrue(item.isLowStock());
        item.reorder(1);
        assertFalse(item.isLowStock());
    }

    @Test
    void totalValueCalculatesCorrectly() {
        item.reorder(20);
        assertEquals(500, item.totalValue(), 0.001);
    }

    @Test
    void zeroUnitPriceRejected() {
        item.setUnitPrice(0);
        assertEquals(25, item.getUnitPrice(), 0.001);
    }

    @Test
    void blankIdRejected() {
        item.setId("   ");
        assertEquals("ST-101", item.getId());
    }

    @Test
    void blankNameRejected() {
        item.setName("");
        assertEquals("Pen", item.getName());
    }
}
