// Problem 19 — GymMember
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class GymMember {

    private String name = "";
    private int monthsActive = 0;
    private double monthlyFee = 1.0;
    private double totalPaid = 0.0;

    public String getName() { return name; }
    public void setName(String value) { /* TODO: Check.isName */ }

    public int getMonthsActive() { return monthsActive; }

    public double getMonthlyFee() { return monthlyFee; }
    public void setMonthlyFee(double value) { /* TODO: Check.inRange(value, 0.0001, Double.MAX_VALUE) */ }

    public double getTotalPaid() { return totalPaid; }

    public void renew(int months) {
        // TODO: if months > 0, monthsActive += months and totalPaid += months * monthlyFee
    }

    public void cancel() {
        // TODO: set monthsActive = 0 (totalPaid unchanged)
    }

    public boolean isActive() { return false; /* TODO: monthsActive > 0 */ }

    public double monthlyCost() {
        // TODO: if !isActive() return 0; else totalPaid / monthsActive
        return 0;
    }

    public boolean isValid() { return false; /* TODO */ }
}
