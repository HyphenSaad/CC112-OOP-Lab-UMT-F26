import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player p;

    @BeforeEach
    void setUp() {
        p = new Player();
        p.setName("Saad");
        p.setMaxHealth(100);
    }

    @Test
    void validPlayerReportsIsValid() {
        p.setMaxHealth(100);
        assertTrue(p.isValid());
    }

    @Test
    void startsWithFullHealth() {
        p.setMaxHealth(80);
        p.heal(1000);
        assertEquals(80, p.getHealth());
    }

    @Test
    void takeDamageReducesHealth() {
        p.setMaxHealth(100);
        p.heal(100);
        p.takeDamage(30);
        assertEquals(70, p.getHealth());
    }

    @Test
    void damageCannotGoBelowZero() {
        p.setMaxHealth(100);
        p.heal(100);
        p.takeDamage(500);
        assertEquals(0, p.getHealth());
        assertFalse(p.isAlive());
    }

    @Test
    void healCannotGoAboveMax() {
        p.setMaxHealth(100);
        p.heal(100);
        p.takeDamage(50);
        p.heal(1000);
        assertEquals(100, p.getHealth());
    }

    @Test
    void healFromZeroRevivesPlayer() {
        p.setMaxHealth(100);
        p.takeDamage(500);
        assertFalse(p.isAlive());
        p.heal(10);
        assertEquals(10, p.getHealth());
        assertTrue(p.isAlive());
    }

    @Test
    void setMaxHealthBelowCurrentCapsHealth() {
        p.setMaxHealth(100);
        p.heal(100);
        p.setMaxHealth(50);
        assertEquals(50, p.getHealth());
        assertEquals(50, p.getMaxHealth());
    }

    @Test
    void negativeDamageRejected() {
        p.setMaxHealth(100);
        p.heal(100);
        p.takeDamage(-10);
        assertEquals(100, p.getHealth());
    }
}
