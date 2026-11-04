// Problem 5 — Discount (Multi-Field Validation)
// Fill in the constructor and apply(). See README.md for requirements.

import chirp.text.Check;

public class Discount {

    private double amount;
    private String type;   // "PERCENT" or "FIXED"

    public Discount(double amount, String type) {
        // TODO: canonicalize the type first:
        //   PERCENT / PCT / % -> "PERCENT"
        //   FIXED / AMOUNT / RS -> "FIXED"
        //   anything else -> leave type null

        // TODO: validate amount based on type:
        //   if "PERCENT": if Check.inRange(amount, 0, 100) -> this.amount = amount
        //   if "FIXED": if Check.inRange(amount, 0, Double.MAX_VALUE) -> this.amount = amount

        // TODO: assign this.type only after the amount check passed
    }

    public double getAmount() { return amount; }
    public String getType() { return type; }

    public double apply(double subtotal) {
        // TODO: if invalid, return subtotal unchanged
        // if "PERCENT": return subtotal * (1 - amount / 100)
        // if "FIXED": return Math.max(0, subtotal - amount)
        return subtotal;
    }

    public boolean isValid() {
        return type != null;
    }
}
