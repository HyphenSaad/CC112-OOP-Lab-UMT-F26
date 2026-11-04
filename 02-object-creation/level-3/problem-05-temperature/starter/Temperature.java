// Problem 5 — Temperature (Celsius by Default, Any Unit Explicitly)
// Fill in both constructors. See README.md for requirements.

import chirp.text.Check;

public class Temperature {

    private double celsius;

    // Value interpreted as Celsius.
    public Temperature(double celsius) {
        // TODO: if Check.inRange(celsius, -273.15, Double.MAX_VALUE) this.celsius = celsius
    }

    // Value interpreted per unit ("C"/"Celsius", "F"/"Fahrenheit", "K"/"Kelvin").
    public Temperature(double value, String unit) {
        // TODO: switch on unit:
        //   "C"/"Celsius"      -> store value (if above absolute zero)
        //   "F"/"Fahrenheit"   -> convert to Celsius, then validate
        //   "K"/"Kelvin"       -> convert to Celsius, then validate
        //   anything else      -> celsius stays 0.0
    }

    public double getCelsius() { return celsius; }
    public double getFahrenheit() { return celsius * 9 / 5 + 32; }
    public double getKelvin() { return celsius + 273.15; }

    public boolean isValid() {
        return Check.inRange(celsius, -273.15, Double.MAX_VALUE);
    }
}
