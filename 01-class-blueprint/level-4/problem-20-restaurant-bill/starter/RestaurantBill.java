// Problem 20 — RestaurantBill
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class RestaurantBill {

    private double subtotal = 0.0;
    private double taxPercent = 0.0;
    private double servicePercent = 0.0;

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double value) { /* TODO: Check.inRange(value, 0, Double.MAX_VALUE) */ }

    public double getTaxPercent() { return taxPercent; }
    public void setTaxPercent(double value) { /* TODO: Check.inRange(value, 0, 30) */ }

    public double getServicePercent() { return servicePercent; }
    public void setServicePercent(double value) { /* TODO: Check.inRange(value, 0, 20) */ }

    public double tax() { return 0; /* TODO: subtotal * taxPercent / 100 */ }

    public double service() { return 0; /* TODO: subtotal * servicePercent / 100 */ }

    public double total() { return 0; /* TODO: subtotal + tax() + service() */ }

    public boolean isValid() { return false; /* TODO */ }
}
