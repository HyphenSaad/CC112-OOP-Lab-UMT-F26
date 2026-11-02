// Problem 18 — WaterBottle
// Fill in the missing methods below. See README.md for requirements.

import chirp.number.Numbers;
import chirp.text.Check;

public class WaterBottle {

    private int capacityMl = 1000;
    private int currentMl = 0;

    public int getCapacityMl() { return capacityMl; }

    public void setCapacityMl(int value) {
        // TODO: if value > 0: capacityMl = value; if currentMl > value, currentMl = value
    }

    public int getCurrentMl() { return currentMl; }

    public void fill(int ml) {
        // TODO: if ml > 0, currentMl = Numbers.clamp(currentMl + ml, 0, capacityMl)
    }

    public int drink(int ml) {
        // TODO: if ml <= 0 return 0
        // else: int actual = Math.min(ml, currentMl); currentMl -= actual; return actual
        return 0;
    }

    public boolean isFull() { return false; /* TODO: currentMl == capacityMl */ }

    public boolean isEmpty() { return false; /* TODO: currentMl == 0 */ }

    public int remaining() { return 0; /* TODO: capacityMl - currentMl */ }

    public boolean isValid() { return false; /* TODO */ }
}
