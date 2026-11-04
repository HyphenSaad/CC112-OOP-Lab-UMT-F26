import chirp.text.Check;

public class Discount {

    private double amount;
    private String type;

    public Discount(double amount, String type) {
        String canonical = canonicalType(type);
        if (canonical == null) return;

        if (canonical.equals("PERCENT")) {
            if (Check.inRange(amount, 0, 100)) {
                this.amount = amount;
                this.type = canonical;
            }
        } else if (canonical.equals("FIXED")) {
            if (Check.inRange(amount, 0, Double.MAX_VALUE)) {
                this.amount = amount;
                this.type = canonical;
            }
        }
    }

    private static String canonicalType(String raw) {
        if (raw == null) return null;
        return switch (raw.trim().toUpperCase()) {
            case "PERCENT", "PCT", "%" -> "PERCENT";
            case "FIXED", "AMOUNT", "RS" -> "FIXED";
            default -> null;
        };
    }

    public double getAmount() { return amount; }
    public String getType() { return type; }

    public double apply(double subtotal) {
        if (!isValid()) return subtotal;
        if ("PERCENT".equals(type)) return subtotal * (1 - amount / 100);
        return Math.max(0, subtotal - amount);
    }

    public boolean isValid() {
        return type != null;
    }
}
