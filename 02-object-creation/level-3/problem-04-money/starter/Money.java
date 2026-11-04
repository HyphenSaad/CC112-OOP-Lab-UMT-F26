// Problem 4 — Money (Rupees, or Rupees + Paise)
// Fill in both constructors. See README.md for requirements.

import chirp.text.Check;

public class Money {

    private long totalPaise;

    // Rupees only.
    public Money(long rupees) {
        // TODO: if rupees >= 0, this.totalPaise = rupees * 100
    }

    // Rupees + paise.
    public Money(long rupees, long paise) {
        // TODO: if rupees >= 0 && paise >= 0 && paise < 100,
        //       this.totalPaise = rupees * 100 + paise
    }

    public long getRupees() { return totalPaise / 100; }
    public long getPaise() { return totalPaise % 100; }
    public long totalPaise() { return totalPaise; }

    public Money plus(Money other) {
        // TODO: if other == null, return this copy
        // else return a new Money with totalPaise(this.totalPaise + other.totalPaise)
        return null;
    }

    public boolean isValid() {
        return totalPaise >= 0;
    }
}
