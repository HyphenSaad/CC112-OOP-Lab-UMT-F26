import chirp.text.Check;

public class Circle {

    private double radius;

    public Circle(double radius) {
        if (Check.inRange(radius, 0.0001, Double.MAX_VALUE)) this.radius = radius;
    }

    public double getRadius() { return radius; }

    public double area() { return Math.PI * radius * radius; }

    public double circumference() { return 2 * Math.PI * radius; }

    public double diameter() { return 2 * radius; }

    public boolean isValid() {
        return Check.inRange(radius, 0.0001, Double.MAX_VALUE);
    }
}
