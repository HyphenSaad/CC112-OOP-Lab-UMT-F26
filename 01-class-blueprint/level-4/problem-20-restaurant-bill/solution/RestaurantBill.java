import chirp.text.Check;

public class RestaurantBill {

    private double subtotal = 0.0;
    private double taxPercent = 0.0;
    private double servicePercent = 0.0;

    public double getSubtotal() { return subtotal; }

    public void setSubtotal(double value) {
        if (Check.inRange(value, 0, Double.MAX_VALUE)) this.subtotal = value;
    }

    public double getTaxPercent() { return taxPercent; }

    public void setTaxPercent(double value) {
        if (Check.inRange(value, 0, 30)) this.taxPercent = value;
    }

    public double getServicePercent() { return servicePercent; }

    public void setServicePercent(double value) {
        if (Check.inRange(value, 0, 20)) this.servicePercent = value;
    }

    // Both tax and service are computed on the subtotal independently.
    // Do not compound — the tax does not include the service charge.
    public double tax() { return subtotal * taxPercent / 100; }

    public double service() { return subtotal * servicePercent / 100; }

    public double total() { return subtotal + tax() + service(); }

    public boolean isValid() {
        return subtotal >= 0
                && Check.inRange(taxPercent, 0, 30)
                && Check.inRange(servicePercent, 0, 20);
    }
}
