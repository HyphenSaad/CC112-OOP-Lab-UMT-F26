// Problem 2 — Rectangle
// Fill in the constructor and methods below. See README.md for requirements.

import chirp.text.Check;

public class Rectangle {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        // TODO: if Check.inRange(length, 0.0001, Double.MAX_VALUE) -> this.length = length
        // TODO: if Check.inRange(width, 0.0001, Double.MAX_VALUE) -> this.width = width
    }

    public double getLength() { return length; }
    public double getWidth() { return width; }

    public double area() { return 0; /* TODO */ }
    public double perimeter() { return 0; /* TODO */ }
    public boolean isSquare() { return false; /* TODO */ }
    public boolean isValid() { return false; /* TODO */ }
}
