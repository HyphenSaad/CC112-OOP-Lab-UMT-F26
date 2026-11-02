// Problem 7 — Temperature
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class Temperature {

    private double celsius = 0.0;

    public double getCelsius() { return celsius; }

    public void setCelsius(double value) {
        // TODO: use Check.inRange(value, -273.15, Double.MAX_VALUE)
    }

    public double toFahrenheit() {
        // TODO: return celsius * 9.0 / 5.0 + 32.0
        return 0;
    }

    public double toKelvin() {
        // TODO: return celsius + 273.15
        return 0;
    }

    public boolean isFreezing() {
        // TODO: return celsius <= 0
        return false;
    }

    public boolean isBoiling() {
        // TODO: return celsius >= 100
        return false;
    }

    public boolean isValid() {
        // TODO: celsius >= -273.15
        return false;
    }
}
