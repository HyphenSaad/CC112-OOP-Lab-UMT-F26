import chirp.number.Numbers;
import chirp.text.Check;

public class WaterBottle {

    private int capacityMl = 1000;
    private int currentMl = 0;

    public int getCapacityMl() { return capacityMl; }

    public void setCapacityMl(int value) {
        if (value > 0) {
            capacityMl = value;
            if (currentMl > capacityMl) currentMl = capacityMl;
        }
    }

    public int getCurrentMl() { return currentMl; }

    public void fill(int ml) {
        if (ml > 0) {
            currentMl = Numbers.clamp(currentMl + ml, 0, capacityMl);
        }
    }

    public int drink(int ml) {
        if (ml <= 0) return 0;
        int actual = Math.min(ml, currentMl);
        currentMl -= actual;
        return actual;
    }

    public boolean isFull() { return currentMl == capacityMl; }

    public boolean isEmpty() { return currentMl == 0; }

    public int remaining() { return capacityMl - currentMl; }

    public boolean isValid() {
        return capacityMl > 0
                && Check.inRange(currentMl, 0, capacityMl);
    }
}
