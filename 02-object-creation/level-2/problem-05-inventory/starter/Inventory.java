// Problem 5 — Inventory (Deep Copy of Array + List)
// Fill in the copy constructor. See README.md for requirements.

import chirp.text.Check;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private String name;
    private int[] quantities;
    private List<String> items;

    public Inventory(String name) {
        if (Check.notBlank(name)) this.name = name;
        this.quantities = new int[0];
        this.items = new ArrayList<>();
    }

    // TODO: copy constructor — copy name, then deep-copy BOTH the array and the list
    // this.quantities = other.quantities.clone();
    // this.items = new ArrayList<>(other.items);
    // If other is null, set defaults: quantities = new int[0], items = new ArrayList<>()
    public Inventory(Inventory other) {
    }

    public String getName() { return name; }

    public List<String> getItems() { return List.copyOf(items); }

    public int getQuantity(int index) {
        if (index < 0 || index >= quantities.length) return -1;
        return quantities[index];
    }

    public boolean addItem(String itemName, int quantity) {
        // TODO: reject blank name, negative quantity, or duplicate item
        // else append to items and to quantities, return true
        return false;
    }

    public boolean updateQuantity(int index, int newQty) {
        // TODO: reject index out of range or newQty < 0
        // else set quantities[index] = newQty, return true
        return false;
    }

    public int itemCount() { return items.size(); }

    public boolean isValid() {
        return Check.notBlank(name) && quantities != null && items != null;
    }
}
