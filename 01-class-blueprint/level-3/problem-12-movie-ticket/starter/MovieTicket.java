// Problem 12 — MovieTicket
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class MovieTicket {

    private String movieName = "";
    private int seatNumber = 1;
    private double price = 1.0;
    private boolean isBooked = false;

    public String getMovieName() { return movieName; }
    public void setMovieName(String value) { /* TODO: Check.notBlank */ }

    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int value) { /* TODO: Check.inRange(value, 1, 100) */ }

    public double getPrice() { return price; }
    public void setPrice(double value) { /* TODO: Check.inRange(value, 0.0001, Double.MAX_VALUE) */ }

    public boolean book() {
        // TODO: if already booked return false; else set true and return true
        return false;
    }

    public boolean cancel() {
        // TODO: if not booked return false; else set false and return true
        return false;
    }

    public String getStatus() {
        // TODO: "Booked" or "Available"
        return "";
    }

    public boolean isValid() { return false; /* TODO */ }
}
