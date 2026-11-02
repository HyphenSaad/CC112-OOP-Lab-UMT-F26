import chirp.text.Check;

public class Player {

    private String name = "";
    private int health = 100;
    private int maxHealth = 100;

    public String getName() { return name; }

    public void setName(String value) {
        if (Check.isName(value)) this.name = value;
    }

    public int getHealth() { return health; }

    public int getMaxHealth() { return maxHealth; }

    public void setMaxHealth(int value) {
        if (Check.inRange(value, 1, Integer.MAX_VALUE)) {
            this.maxHealth = value;
            if (health > maxHealth) health = maxHealth;
        }
    }

    public void takeDamage(int amount) {
        if (amount > 0) health = Math.max(0, health - amount);
    }

    public void heal(int amount) {
        if (amount > 0) health = Math.min(maxHealth, health + amount);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean isValid() {
        return Check.isName(name)
                && Check.inRange(maxHealth, 1, Integer.MAX_VALUE)
                && Check.inRange(health, 0, maxHealth);
    }
}
