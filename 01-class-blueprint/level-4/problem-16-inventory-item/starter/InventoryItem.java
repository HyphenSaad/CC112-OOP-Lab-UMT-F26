// Problem 16 — InventoryItem
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class InventoryItem {

    private String id = "";
    private String name = "";
    private int quantity = 0;
    private double unitPrice = 1.0;
    private int reorderLevel = 0;

    public String getId() { return id; }
    public void setId(String value) { /* TODO: Check.notBlank */ }

    public String getName() { return name; }
    public void setName(String value) { /* TODO: Check.notBlank */ }

    public int getQuantity() { return quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double value) { /* TODO: Check.inRange(value, 0.0001, Double.MAX_VALUE) */ }

    public int getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(int value) { /* TODO: Check.inRange(value, 0, Integer.MAX_VALUE) */ }

    public void reorder(int amount) { /* TODO: if amount > 0, quantity += amount */ }

    public boolean isLowStock() { return false; /* TODO: quantity <= reorderLevel */ }

    public double totalValue() { return 0; /* TODO: quantity * unitPrice */ }

    public boolean isValid() { return false; /* TODO */ }
}
