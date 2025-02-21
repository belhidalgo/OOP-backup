package nl.rug.oop.rpg.characters.NPCs;

import nl.rug.oop.rpg.Interactable;
import nl.rug.oop.rpg.characters.Player;

import java.io.Serial;
import java.io.Serializable;

/**
 * The Wizard NPC subclass.
 */
public class Wizard extends NPC implements Interactable, Serializable {

    @Serial
    private static final long serialVersionUID = 800;

    /**
     * New Wizard.
     * @param description the description of the wizard.
     * @param damage the strength the wizard can inflict.
     * @param health the life of the wizard.
     * @param money the money it has.
     * @param key determines if the character has the key in its possession
     */
    public Wizard(String description, int damage, int health, int money, boolean key) {
        super(description, damage, health, money, key);
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        System.out.println("The wizard decided to be generous and increased your powers.");
        player.setStrength(player.getStrength() + getStrength());
        System.out.println("Strength: " + player.getStrength());
        System.out.println("Health level: " + player.getHealth());
    }
}
