// Problem 4 — Circle
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class Circle {

    private double radius = 1.0;

    public double getRadius() { return radius; }

    public void setRadius(double value) {
        // TODO: use Check.inRange(value, 0.0001, Double.MAX_VALUE)
    }

    public double area() {
        // TODO: return Math.PI * radius * radius
        return 0;
    }

    public double circumference() {
        // TODO: return 2 * Math.PI * radius
        return 0;
    }

    public double diameter() {
        // TODO: return 2 * radius
        return 0;
    }

    public boolean isValid() {
        // TODO: radius > 0
        return false;
    }
}
