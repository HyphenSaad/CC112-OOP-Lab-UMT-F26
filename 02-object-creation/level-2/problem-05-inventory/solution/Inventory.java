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

    public Inventory(Inventory other) {
        if (other != null) {
            this.name = other.name;
            this.quantities = other.quantities.clone();       // array deep copy
            this.items = new ArrayList<>(other.items);        // list deep copy
        } else {
            this.quantities = new int[0];
            this.items = new ArrayList<>();
        }
    }

    public String getName() { return name; }

    public List<String> getItems() { return List.copyOf(items); }

    public int getQuantity(int index) {
        if (index < 0 || index >= quantities.length) return -1;
        return quantities[index];
    }

    public boolean addItem(String itemName, int quantity) {
        if (!Check.notBlank(itemName)) return false;
        if (quantity < 0) return false;
        if (items.contains(itemName)) return false;

        items.add(itemName);
        int[] grown = new int[quantities.length + 1];
        System.arraycopy(quantities, 0, grown, 0, quantities.length);
        grown[quantities.length] = quantity;
        quantities = grown;
        return true;
    }

    public boolean updateQuantity(int index, int newQty) {
        if (index < 0 || index >= quantities.length) return false;
        if (newQty < 0) return false;
        quantities[index] = newQty;
        return true;
    }

    public int itemCount() { return items.size(); }

    public boolean isValid() {
        return Check.notBlank(name) && quantities != null && items != null;
    }
}
