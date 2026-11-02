import chirp.text.Check;

public class Circle {

    private double radius = 1.0;

    public double getRadius() { return radius; }

    public void setRadius(double value) {
        if (Check.inRange(value, 0.0001, Double.MAX_VALUE)) {
            this.radius = value;
        }
    }

    public double area() { return Math.PI * radius * radius; }

    public double circumference() { return 2 * Math.PI * radius; }

    public double diameter() { return 2 * radius; }

    public boolean isValid() {
        return Check.inRange(radius, 0.0001, Double.MAX_VALUE);
    }
}
