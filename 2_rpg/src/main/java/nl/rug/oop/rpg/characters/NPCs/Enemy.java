package nl.rug.oop.rpg.characters.NPCs;

import nl.rug.oop.rpg.Interactable;
import nl.rug.oop.rpg.Attackable;
import nl.rug.oop.rpg.characters.Character;
import nl.rug.oop.rpg.characters.Player;

import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

import static nl.rug.oop.rpg.Game.scan;

/**
 * Enemy class.
 * Subclass of NPC.
 */
public class Enemy extends NPC implements Attackable, Interactable, Serializable {

    @Serial
    private static final long serialVersionUID = 500;

    /**
     * Create a new enemy.
     * @param description the description of the enemy.
     * @param damage the strength the enemy can inflict.
     * @param health the life of the enemy.
     * @param money the money it has.
     */
    public Enemy(String description, int damage, int health, int money) {
        super(description, damage, health, money);
    }

    @Override
    public void attack(Character player) {
        player.setHealth(player.getHealth() - getStrength());
        if (player.getHealth() <= 0) {
            System.out.println("Game over!");
        }
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * Method to interact with a player.
     * @param player is the player with whom an Enemy interacts.
     */
    @Override
    public void interact(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You angered an enemy and now it has started attacking you!");
        attack(player);
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("    (0) Fight back!!!");
            System.out.println("    (1) Try to scooch away...");
            int option = scan(scanner, 0, 1);
            if (option == 0) {
                if (getHealth() > 0 && player.getHealth() > 0) {
                    player.attack(this);
                    if (this.getHealth() <= 0) {
                        System.out.println("Success! You killed the enemy!");
                        player.setMoney(player.getMoney() + getMoney());
                        System.out.println("You took the enemy's possessions." +
                                " You've gained "+getMoney()+" pennies.");
                        break;
                    }
                    System.out.println("You've wounded the enemy!  Enemy's health: " + getHealth());
                    attack(player);
                    System.out.println("The enemy fought back! (Health: " + player.getHealth() + ")");
                }
            } else if (option == 1) {
                System.out.println("Pheww... that was a close call");
                break;
            }
        }
    }
}
