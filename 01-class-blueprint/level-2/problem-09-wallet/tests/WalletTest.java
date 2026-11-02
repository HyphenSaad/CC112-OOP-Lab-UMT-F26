import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    private Wallet wallet;

    @BeforeEach
    void setUp() {
        wallet = new Wallet();
    }

    @Test
    void freshWalletIsValid() {
        assertTrue(wallet.isValid());
    }

    @Test
    void initialBalanceIsZero() {
        assertEquals(0, wallet.getBalance(), 0.001);
        assertTrue(wallet.isEmpty());
    }

    @Test
    void addMoneyIncreasesBalance() {
        wallet.addMoney(500);
        assertEquals(500, wallet.getBalance(), 0.001);
        assertFalse(wallet.isEmpty());
    }

    @Test
    void spendMoneyReducesBalanceAndReturnsTrue() {
        wallet.addMoney(500);
        assertTrue(wallet.spendMoney(200));
        assertEquals(300, wallet.getBalance(), 0.001);
    }

    @Test
    void overspendReturnsFalse() {
        wallet.addMoney(100);
        assertFalse(wallet.spendMoney(500));
        assertEquals(100, wallet.getBalance(), 0.001);
    }

    @Test
    void canAffordChecksCorrectly() {
        wallet.addMoney(500);
        assertTrue(wallet.canAfford(300));
        assertTrue(wallet.canAfford(500));
        assertFalse(wallet.canAfford(501));
    }

    @Test
    void negativeAddRejected() {
        wallet.addMoney(-50);
        assertEquals(0, wallet.getBalance(), 0.001);
    }

    @Test
    void zeroSpendReturnsFalse() {
        wallet.addMoney(100);
        assertFalse(wallet.spendMoney(0));
        assertEquals(100, wallet.getBalance(), 0.001);
    }

    @Test
    void spendUntilEmpty() {
        wallet.addMoney(100);
        assertTrue(wallet.spendMoney(100));
        assertEquals(0, wallet.getBalance(), 0.001);
        assertTrue(wallet.isEmpty());
    }
}
