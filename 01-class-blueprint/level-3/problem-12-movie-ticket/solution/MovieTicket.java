import chirp.text.Check;

public class MovieTicket {

    private String movieName = "";
    private int seatNumber = 1;
    private double price = 1.0;
    private boolean isBooked = false;

    public String getMovieName() { return movieName; }

    public void setMovieName(String value) {
        if (Check.notBlank(value)) this.movieName = value;
    }

    public int getSeatNumber() { return seatNumber; }

    public void setSeatNumber(int value) {
        if (Check.inRange(value, 1, 100)) this.seatNumber = value;
    }

    public double getPrice() { return price; }

    public void setPrice(double value) {
        if (Check.inRange(value, 0.0001, Double.MAX_VALUE)) this.price = value;
    }

    public boolean book() {
        if (isBooked) return false;
        isBooked = true;
        return true;
    }

    public boolean cancel() {
        if (!isBooked) return false;
        isBooked = false;
        return true;
    }

    public String getStatus() {
        return isBooked ? "Booked" : "Available";
    }

    public boolean isValid() {
        return Check.notBlank(movieName)
                && Check.inRange(seatNumber, 1, 100)
                && Check.inRange(price, 0.0001, Double.MAX_VALUE);
    }
}
