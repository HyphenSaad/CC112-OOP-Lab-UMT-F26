import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantBillTest {

    private RestaurantBill bill;

    @BeforeEach
    void setUp() {
        bill = new RestaurantBill();
        bill.setSubtotal(2000);
        bill.setTaxPercent(5);
        bill.setServicePercent(10);
    }

    @Test
    void validBillReportsIsValid() {
        assertTrue(bill.isValid());
    }

    @Test
    void taxCalculatesOnSubtotal() {
        assertEquals(100, bill.tax(), 0.001);
    }

    @Test
    void serviceCalculatesOnSubtotal() {
        assertEquals(200, bill.service(), 0.001);
    }

    @Test
    void totalAddsAllThree() {
        assertEquals(2300, bill.total(), 0.001);
    }

    @Test
    void taxAboveThirtyRejected() {
        bill.setTaxPercent(50);
        assertEquals(5, bill.getTaxPercent(), 0.001);
    }

    @Test
    void serviceAboveTwentyRejected() {
        bill.setServicePercent(25);
        assertEquals(10, bill.getServicePercent(), 0.001);
    }

    @Test
    void negativeSubtotalRejected() {
        bill.setSubtotal(-100);
        assertEquals(2000, bill.getSubtotal(), 0.001);
    }

    @Test
    void zeroSubtotalYieldsZeroTotal() {
        bill.setSubtotal(0);
        assertEquals(0, bill.total(), 0.001);
    }
}
