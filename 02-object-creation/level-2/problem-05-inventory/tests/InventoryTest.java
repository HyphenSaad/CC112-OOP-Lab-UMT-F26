import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void copyConstructorCopiesName() {
        Inventory original = new Inventory("Warehouse A");
        assertEquals("Warehouse A", new Inventory(original).getName());
    }

    @Test
    void copyConstructorHasSameItemCount() {
        Inventory original = new Inventory("Warehouse A");
        original.addItem("Pen", 100);
        original.addItem("Notebook", 50);
        Inventory copy = new Inventory(original);
        assertEquals(2, copy.itemCount());
        assertEquals(100, copy.getQuantity(0));
        assertEquals(50, copy.getQuantity(1));
    }

    @Test
    void arrayDeepCopy() {
        Inventory original = new Inventory("Warehouse A");
        original.addItem("Pen", 100);
        Inventory copy = new Inventory(original);
        original.updateQuantity(0, 200);
        assertEquals(200, original.getQuantity(0));
        assertEquals(100, copy.getQuantity(0), "array must be deep-copied");
    }

    @Test
    void listDeepCopy() {
        Inventory original = new Inventory("Warehouse A");
        original.addItem("Pen", 100);
        Inventory copy = new Inventory(original);
        original.addItem("Notebook", 50);
        assertEquals(2, original.itemCount());
        assertEquals(1, copy.itemCount(), "list must be deep-copied");
    }

    @Test
    void copyOfNullHasDefaults() {
        Inventory copy = new Inventory((Inventory) null);
        assertNull(copy.getName());
        assertEquals(0, copy.itemCount());
        assertFalse(copy.isValid());
    }

    @Test
    void getItemsReturnsUnmodifiableList() {
        Inventory original = new Inventory("Warehouse A");
        original.addItem("Pen", 100);
        assertThrows(UnsupportedOperationException.class,
                () -> original.getItems().add("Hack"));
    }
}
