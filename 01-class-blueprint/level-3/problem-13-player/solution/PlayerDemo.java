import chirp.console.Console;
import chirp.session.Session;
import static chirp.Chirp.*;

public class PlayerDemo {

    public static void main(String[] args) {
        start();

        Session.me()
                .name("John Baba")
                .rollNumber("2024-CS-001")
                .degree("BSCS")
                .batch("2024")
                .section("A");

        if (!Session.me().isComplete()) {
            Console.warn("Session incomplete. Missing: " + Session.me().missingFields());
        }

        String name = readNonEmpty("Player name");
        int maxHp = readInt("Max health", 1, 10000);

        Player p = new Player();
        p.setName(name);
        p.setMaxHealth(maxHp);
        p.heal(maxHp);

        Console.println();
        Console.println(p.getName() + " (" + p.getHealth() + " / "
                + p.getMaxHealth() + "), alive: " + p.isAlive());

        int damage = readInt("Damage", 1, p.getMaxHealth());
        p.takeDamage(damage);
        Console.println(p.getName() + " (" + p.getHealth() + " / "
                + p.getMaxHealth() + "), alive: " + p.isAlive());

        p.takeDamage(p.getMaxHealth() + 100);
        Console.println(p.getName() + " (" + p.getHealth() + " / "
                + p.getMaxHealth() + "), alive: " + p.isAlive());

        p.heal(500);
        Console.println(p.getName() + " (" + p.getHealth() + " / "
                + p.getMaxHealth() + "), alive: " + p.isAlive());

        Console.println();
        Console.watermark();
    }
}
