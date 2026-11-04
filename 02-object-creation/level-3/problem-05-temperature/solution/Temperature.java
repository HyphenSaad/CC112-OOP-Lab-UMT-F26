import chirp.text.Check;

public class Temperature {

    private double celsius;

    public Temperature(double celsius) {
        if (Check.inRange(celsius, -273.15, Double.MAX_VALUE)) this.celsius = celsius;
    }

    public Temperature(double value, String unit) {
        if (unit == null) return;

        double converted;
        switch (unit.trim()) {
            case "C", "Celsius" -> converted = value;
            case "F", "Fahrenheit" -> converted = (value - 32) * 5.0 / 9.0;
            case "K", "Kelvin" -> converted = value - 273.15;
            default -> { return; }
        }
        if (Check.inRange(converted, -273.15, Double.MAX_VALUE)) this.celsius = converted;
    }

    public double getCelsius() { return celsius; }
    public double getFahrenheit() { return celsius * 9 / 5 + 32; }
    public double getKelvin() { return celsius + 273.15; }

    public boolean isValid() {
        return Check.inRange(celsius, -273.15, Double.MAX_VALUE);
    }
}
