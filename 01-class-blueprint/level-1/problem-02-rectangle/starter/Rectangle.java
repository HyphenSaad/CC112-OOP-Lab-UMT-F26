// Problem 2 — Rectangle
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class Rectangle {

    private double length = 1.0;
    private double width = 1.0;

    public double getLength() { return length; }

    public void setLength(double value) {
        // TODO: use Check.inRange(value, 0.0001, Double.MAX_VALUE)
    }

    public double getWidth() { return width; }

    public void setWidth(double value) {
        // TODO: use Check.inRange(value, 0.0001, Double.MAX_VALUE)
    }

    public double area() {
        // TODO: return length * width
        return 0;
    }

    public double perimeter() {
        // TODO: return 2 * (length + width)
        return 0;
    }

    public boolean isSquare() {
        // TODO: return true when length == width
        return false;
    }

    public boolean isValid() {
        // TODO: both values > 0
        return false;
    }
}
