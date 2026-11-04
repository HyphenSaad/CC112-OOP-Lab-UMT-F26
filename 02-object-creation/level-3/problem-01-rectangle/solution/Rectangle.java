import chirp.text.Check;

public class Rectangle {

    private double length;
    private double width;

    public Rectangle(double side) {
        if (Check.inRange(side, 0.0001, Double.MAX_VALUE)) {
            this.length = side;
            this.width = side;
        }
    }

    public Rectangle(double length, double width) {
        if (Check.inRange(length, 0.0001, Double.MAX_VALUE)) this.length = length;
        if (Check.inRange(width, 0.0001, Double.MAX_VALUE)) this.width = width;
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
