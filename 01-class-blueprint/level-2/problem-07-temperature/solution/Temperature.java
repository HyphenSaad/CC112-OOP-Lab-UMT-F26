import chirp.text.Check;

public class Temperature {

    private double celsius = 0.0;

    public double getCelsius() { return celsius; }

    public void setCelsius(double value) {
        if (Check.inRange(value, -273.15, Double.MAX_VALUE)) {
            this.celsius = value;
        }
    }

    public double toFahrenheit() {
        return celsius * 9.0 / 5.0 + 32.0;
    }

    public double toKelvin() {
        return celsius + 273.15;
    }

    public boolean isFreezing() {
        return celsius <= 0;
    }

    public boolean isBoiling() {
        return celsius >= 100;
    }

    public boolean isValid() {
        return Check.inRange(celsius, -273.15, Double.MAX_VALUE);
    }
}
