// Problem 1 — Rectangle (Square vs Rectangle)
// Fill in both constructors. See README.md for requirements.

import chirp.text.Check;

public class Rectangle {

    private double length;
    private double width;

    // Constructor for a square — both sides equal.
    public Rectangle(double side) {
        // TODO: if Check.inRange(side, 0.0001, Double.MAX_VALUE) {
        //     this.length = side;
        //     this.width = side;
        // }
    }

    // Constructor for a general rectangle.
    public Rectangle(double length, double width) {
        // TODO: same validation for length
        // TODO: same validation for width
    }

    public double getLength() { return length; }
    public double getWidth() { return width; }

    public double area() { return length * width; }
    public double perimeter() { return 2 * (length + width); }
    public boolean isSquare() { return length == width; }

    public boolean isValid() {
        return Check.inRange(length, 0.0001, Double.MAX_VALUE)
                && Check.inRange(width, 0.0001, Double.MAX_VALUE);
    }
}
