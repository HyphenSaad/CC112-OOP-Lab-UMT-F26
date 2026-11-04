import chirp.text.Check;

public class Money {

    private long totalPaise;

    public Money(long rupees) {
        if (rupees >= 0) this.totalPaise = rupees * 100;
    }

    public Money(long rupees, long paise) {
        if (rupees >= 0 && paise >= 0 && paise < 100) {
            this.totalPaise = rupees * 100 + paise;
        }
    }

    public long getRupees() { return totalPaise / 100; }
    public long getPaise() { return totalPaise % 100; }
    public long totalPaise() { return totalPaise; }

    public Money plus(Money other) {
        if (other == null) return new Money(this.totalPaise / 100, this.totalPaise % 100);
        long sum = this.totalPaise + other.totalPaise;
        return new Money(sum / 100, sum % 100);
    }

    public boolean isValid() {
        return totalPaise >= 0;
    }
}
