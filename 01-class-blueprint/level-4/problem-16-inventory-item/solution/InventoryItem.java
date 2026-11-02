import chirp.text.Check;

public class InventoryItem {

    private String id = "";
    private String name = "";
    private int quantity = 0;
    private double unitPrice = 1.0;
    private int reorderLevel = 0;

    public String getId() { return id; }

    public void setId(String value) {
        if (Check.notBlank(value)) this.id = value;
    }

    public String getName() { return name; }

    public void setName(String value) {
        if (Check.notBlank(value)) this.name = value;
    }

    public int getQuantity() { return quantity; }

    public double getUnitPrice() { return unitPrice; }

    public void setUnitPrice(double value) {
        if (Check.inRange(value, 0.0001, Double.MAX_VALUE)) this.unitPrice = value;
    }

    public int getReorderLevel() { return reorderLevel; }

    public void setReorderLevel(int value) {
        if (Check.inRange(value, 0, Integer.MAX_VALUE)) this.reorderLevel = value;
    }

    public void reorder(int amount) {
        if (amount > 0) quantity += amount;
    }

    public boolean isLowStock() {
        return quantity <= reorderLevel;
    }

    public double totalValue() {
        return quantity * unitPrice;
    }

    public boolean isValid() {
        return Check.notBlank(id)
                && Check.notBlank(name)
                && quantity >= 0
                && Check.inRange(unitPrice, 0.0001, Double.MAX_VALUE)
                && reorderLevel >= 0;
    }
}
