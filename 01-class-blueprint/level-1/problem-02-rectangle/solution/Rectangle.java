import chirp.text.Check;

public class Rectangle {

    private double length = 1.0;
    private double width = 1.0;

    public double getLength() { return length; }

    public void setLength(double value) {
        if (Check.inRange(value, 0.0001, Double.MAX_VALUE)) {
            this.length = value;
        }
    }

    public double getWidth() { return width; }

    public void setWidth(double value) {
        if (Check.inRange(value, 0.0001, Double.MAX_VALUE)) {
            this.width = value;
        }
    }

    public double area() { return length * width; }

    public double perimeter() { return 2 * (length + width); }

    public boolean isSquare() { return length == width; }

    public boolean isValid() {
        return Check.inRange(length, 0.0001, Double.MAX_VALUE)
                && Check.inRange(width, 0.0001, Double.MAX_VALUE);
    }
}
