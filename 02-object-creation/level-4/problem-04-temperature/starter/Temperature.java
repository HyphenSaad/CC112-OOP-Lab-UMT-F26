// Problem 4 — Temperature (The this(...) Gotcha)
// The delegating constructor needs a STATIC helper — instance methods
// cannot be called before this(...).

import chirp.text.Check;

public class Temperature {

    private double celsius;

    // Canonical — keeps the absolute-zero validation.
    public Temperature(double celsius) {
        if (Check.inRange(celsius, -273.15, Double.MAX_VALUE)) this.celsius = celsius;
    }

    // TODO: delegate via this(convertToCelsius(value, unit));
    public Temperature(double value, String unit) {
    }

    // TODO: fill in the static conversion helper.
    // Return -999 for unknown unit or null — that value is below absolute zero,
    // so the canonical constructor will reject it automatically.
    private static double convertToCelsius(double value, String unit) {
        return 0;
    }

    public double getCelsius() { return celsius; }
    public double getFahrenheit() { return celsius * 9 / 5 + 32; }
    public double getKelvin() { return celsius + 273.15; }

    public boolean isValid() {
        return Check.inRange(celsius, -273.15, Double.MAX_VALUE);
    }
}
