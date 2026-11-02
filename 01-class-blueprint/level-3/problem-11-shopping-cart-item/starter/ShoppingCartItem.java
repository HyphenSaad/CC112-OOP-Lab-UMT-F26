// Problem 11 — ShoppingCartItem
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class ShoppingCartItem {

    private String name = "";
    private double price = 1.0;
    private int quantity = 1;
    private double discountPercent = 0.0;

    public String getName() { return name; }
    public void setName(String value) { /* TODO: Check.notBlank */ }

    public double getPrice() { return price; }
    public void setPrice(double value) { /* TODO: Check.inRange(value, 0.0001, Double.MAX_VALUE) */ }

    public int getQuantity() { return quantity; }
    public void setQuantity(int value) { /* TODO: Check.inRange(value, 1, Integer.MAX_VALUE) */ }

    public double getDiscount() { return discountPercent; }
    public void applyDiscount(double percent) { /* TODO: if Check.inRange(percent, 0, 100) assign */ }

    public double lineTotal() { return 0; /* TODO */ }

    public boolean isBulkOrder() { return false; /* TODO: quantity >= 5 */ }

    public boolean isValid() { return false; /* TODO */ }
}
