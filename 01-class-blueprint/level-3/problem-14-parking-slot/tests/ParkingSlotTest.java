import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParkingSlotTest {

    private ParkingSlot slot;

    @BeforeEach
    void setUp() {
        slot = new ParkingSlot();
        slot.setSlotNumber(12);
    }

    @Test
    void validSlotReportsIsValid() {
        assertTrue(slot.isValid());
    }

    @Test
    void startsAvailableWithNullPlate() {
        assertTrue(slot.isAvailable());
        assertNull(slot.getVehiclePlate());
    }

    @Test
    void parkSetsOccupiedAndStoresPlate() {
        assertTrue(slot.park("LEB-1234"));
        assertFalse(slot.isAvailable());
        assertEquals("LEB-1234", slot.getVehiclePlate());
    }

    @Test
    void parkWhenOccupiedReturnsFalse() {
        slot.park("LEB-1234");
        assertFalse(slot.park("XYZ-9999"));
        assertEquals("LEB-1234", slot.getVehiclePlate());
    }

    @Test
    void parkWithBlankPlateReturnsFalse() {
        assertFalse(slot.park("   "));
        assertTrue(slot.isAvailable());
    }

    @Test
    void unparkClearsState() {
        slot.park("LEB-1234");
        assertTrue(slot.unpark());
        assertTrue(slot.isAvailable());
        assertNull(slot.getVehiclePlate());
    }

    @Test
    void unparkWhenAvailableReturnsFalse() {
        assertFalse(slot.unpark());
    }

    @Test
    void invalidSlotNumberRejected() {
        slot.setSlotNumber(0);
        assertEquals(12, slot.getSlotNumber());
        slot.setSlotNumber(1000);
        assertEquals(12, slot.getSlotNumber());
    }

    @Test
    void isAvailableReflectsState() {
        assertTrue(slot.isAvailable());
        slot.park("LEB-1234");
        assertFalse(slot.isAvailable());
        slot.unpark();
        assertTrue(slot.isAvailable());
    }
}
