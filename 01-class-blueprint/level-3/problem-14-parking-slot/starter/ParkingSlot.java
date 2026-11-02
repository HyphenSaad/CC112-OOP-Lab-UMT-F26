// Problem 14 — ParkingSlot
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class ParkingSlot {

    private int slotNumber = 1;
    private boolean isOccupied = false;
    private String vehiclePlate = null;

    public int getSlotNumber() { return slotNumber; }
    public void setSlotNumber(int value) { /* TODO: Check.inRange(value, 1, 999) */ }

    public String getVehiclePlate() { return vehiclePlate; }

    public boolean park(String plate) {
        // TODO: if isOccupied return false
        // if !Check.notBlank(plate) return false
        // set isOccupied = true, vehiclePlate = plate, return true
        return false;
    }

    public boolean unpark() {
        // TODO: if !isOccupied return false
        // clear isOccupied and vehiclePlate, return true
        return false;
    }

    public boolean isAvailable() { return false; /* TODO: !isOccupied */ }

    public boolean isValid() { return false; /* TODO */ }
}
