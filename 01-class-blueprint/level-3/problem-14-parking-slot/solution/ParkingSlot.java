import chirp.text.Check;

public class ParkingSlot {

    private int slotNumber = 1;
    private boolean isOccupied = false;
    private String vehiclePlate = null;

    public int getSlotNumber() { return slotNumber; }

    public void setSlotNumber(int value) {
        if (Check.inRange(value, 1, 999)) this.slotNumber = value;
    }

    public String getVehiclePlate() { return vehiclePlate; }

    public boolean park(String plate) {
        if (isOccupied) return false;
        if (!Check.notBlank(plate)) return false;
        isOccupied = true;
        vehiclePlate = plate;
        return true;
    }

    public boolean unpark() {
        if (!isOccupied) return false;
        isOccupied = false;
        vehiclePlate = null;
        return true;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public boolean isValid() {
        if (!Check.inRange(slotNumber, 1, 999)) return false;
        if (isOccupied) return Check.notBlank(vehiclePlate);
        return vehiclePlate == null;
    }
}
