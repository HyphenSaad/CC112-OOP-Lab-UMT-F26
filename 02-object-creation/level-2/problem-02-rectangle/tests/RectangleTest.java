import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void copyConstructorCopiesFields() {
        Rectangle original = new Rectangle(8, 5);
        Rectangle copy = new Rectangle(original);
        assertEquals(8, copy.getLength(), 0.001);
        assertEquals(5, copy.getWidth(), 0.001);
    }

    @Test
    void copyOfValidIsValid() {
        Rectangle original = new Rectangle(8, 5);
        assertTrue(new Rectangle(original).isValid());
    }

    @Test
    void copyOfNullHasDefaults() {
        Rectangle copy = new Rectangle((Rectangle) null);
        assertEquals(0.0, copy.getLength(), 0.001);
        assertEquals(0.0, copy.getWidth(), 0.001);
        assertFalse(copy.isValid());
    }

    @Test
    void copyPreservesAreaAndPerimeter() {
        Rectangle original = new Rectangle(8, 5);
        Rectangle copy = new Rectangle(original);
        assertEquals(original.area(), copy.area(), 0.001);
        assertEquals(original.perimeter(), copy.perimeter(), 0.001);
    }
}
