import chirp.text.Check;

public class GymMember {

    private String name = "";
    private int monthsActive = 0;
    private double monthlyFee = 1.0;
    private double totalPaid = 0.0;

    public String getName() { return name; }

    public void setName(String value) {
        if (Check.isName(value)) this.name = value;
    }

    public int getMonthsActive() { return monthsActive; }

    public double getMonthlyFee() { return monthlyFee; }

    public void setMonthlyFee(double value) {
        if (Check.inRange(value, 0.0001, Double.MAX_VALUE)) this.monthlyFee = value;
    }

    public double getTotalPaid() { return totalPaid; }

    public void renew(int months) {
        if (months > 0) {
            monthsActive += months;
            totalPaid += months * monthlyFee;
        }
    }

    public void cancel() {
        monthsActive = 0;
    }

    public boolean isActive() {
        return monthsActive > 0;
    }

    public double monthlyCost() {
        if (!isActive()) return 0;
        return totalPaid / monthsActive;
    }

    public boolean isValid() {
        return Check.isName(name)
                && monthsActive >= 0
                && Check.inRange(monthlyFee, 0.0001, Double.MAX_VALUE)
                && totalPaid >= 0;
    }
}
