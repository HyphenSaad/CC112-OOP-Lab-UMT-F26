import chirp.text.Check;

public class ShoppingCartItem {

    private String name = "";
    private double price = 1.0;
    private int quantity = 1;
    private double discountPercent = 0.0;

    public String getName() { return name; }

    public void setName(String value) {
        if (Check.notBlank(value)) this.name = value;
    }

    public double getPrice() { return price; }

    public void setPrice(double value) {
        if (Check.inRange(value, 0.0001, Double.MAX_VALUE)) this.price = value;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int value) {
        if (Check.inRange(value, 1, Integer.MAX_VALUE)) this.quantity = value;
    }

    public double getDiscount() { return discountPercent; }

    public void applyDiscount(double percent) {
        if (Check.inRange(percent, 0, 100)) this.discountPercent = percent;
    }

    public double lineTotal() {
        return price * quantity * (1 - discountPercent / 100);
    }

    public boolean isBulkOrder() {
        return quantity >= 5;
    }

    public boolean isValid() {
        return Check.notBlank(name)
                && Check.inRange(price, 0.0001, Double.MAX_VALUE)
                && Check.inRange(quantity, 1, Integer.MAX_VALUE)
                && Check.inRange(discountPercent, 0, 100);
    }
}
