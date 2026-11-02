import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WaterBottleTest {

    private WaterBottle bottle;

    @BeforeEach
    void setUp() {
        bottle = new WaterBottle();
        bottle.setCapacityMl(1000);
    }

    @Test
    void freshBottleIsValid() {
        assertTrue(bottle.isValid());
    }

    @Test
    void startsEmpty() {
        assertEquals(0, bottle.getCurrentMl());
        assertTrue(bottle.isEmpty());
    }

    @Test
    void fillIncreasesCurrent() {
        bottle.fill(600);
        assertEquals(600, bottle.getCurrentMl());
    }

    @Test
    void fillCapsAtCapacity() {
        bottle.fill(1500);
        assertEquals(1000, bottle.getCurrentMl());
        assertTrue(bottle.isFull());
    }

    @Test
    void drinkReducesCurrent() {
        bottle.fill(600);
        bottle.drink(200);
        assertEquals(400, bottle.getCurrentMl());
    }

    @Test
    void drinkReturnsActualAmountDrunk() {
        bottle.fill(300);
        int got = bottle.drink(500);
        assertEquals(300, got);
        assertEquals(0, bottle.getCurrentMl());
    }

    @Test
    void drinkWhenEmptyReturnsZero() {
        assertEquals(0, bottle.drink(500));
    }

    @Test
    void invalidFillRejected() {
        bottle.fill(0);
        assertEquals(0, bottle.getCurrentMl());
        bottle.fill(-50);
        assertEquals(0, bottle.getCurrentMl());
    }

    @Test
    void setCapacityBelowCurrentCapsCurrent() {
        bottle.fill(800);
        bottle.setCapacityMl(500);
        assertEquals(500, bottle.getCurrentMl());
        assertEquals(500, bottle.getCapacityMl());
    }
}
