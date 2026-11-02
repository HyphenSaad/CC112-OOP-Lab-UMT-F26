// Problem 13 — Player
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class Player {

    private String name = "";
    private int health = 100;
    private int maxHealth = 100;

    public String getName() { return name; }
    public void setName(String value) { /* TODO: Check.isName */ }

    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }

    public void setMaxHealth(int value) {
        // TODO: if Check.inRange(value, 1, Integer.MAX_VALUE):
        //   this.maxHealth = value
        //   if health > maxHealth, health = maxHealth
    }

    public void takeDamage(int amount) {
        // TODO: if amount > 0, health = Math.max(0, health - amount)
    }

    public void heal(int amount) {
        // TODO: if amount > 0, health = Math.min(maxHealth, health + amount)
    }

    public boolean isAlive() { return false; /* TODO: health > 0 */ }

    public boolean isValid() { return false; /* TODO */ }
}
