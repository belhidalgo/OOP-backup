package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.Attackable;
import nl.rug.oop.rpg.Interactable;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.characters.Character;
import nl.rug.oop.rpg.characters.Player;

import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

import lombok.*;

/**
 * An evil door (subclass of door) that attacks the player.
 */
@Getter
@Setter
public class Evil extends Door implements Interactable, Attackable, Serializable {
    private int damage;
    private int health;
    @Serial
    private static final long serialVersionUID = 300;

    /**
     * We create a new Evil Door.
     * @param description is the description of the door.
     * @param roomBehind is the room behind the door.
     * @param damage is the damage the room can inflict.
     * @param health is the life the door has left.
     */
    public Evil(String description, Room roomBehind, int damage, int health) {
        super(description, roomBehind);
        this.damage = damage;
        this.health = health;
    }

    @Override
    public void attack(Character character) {
        character.setHealth(character.getHealth() - damage);
    }

    /**
     * Print whether the player has defeated the door.
     * @param player is the player who tries to cross the door.
     */
    public void status(Player player) {
        if (player.getHealth() > 0) {
            System.out.println("Congratulations! The Evil Door wasn't able to defeat you!");
            player.setRoom(getRoomBehind());
        } else {
            System.out.println("You should be ashamed, you have been killed by a door!");
        }
    }

    @Override
    public void interact(Player player) {
        System.out.println("Oh no! The door you decided to cross is evil! Do you still want to cross it? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equals("n")) {
            return;
        }
        if (player.getProtection() > 0) {
            System.out.println("Do you want to use your protection? (y/n)");
            choice = scanner.next();
            if (choice.equals("y")) {
                player.setProtection(player.getProtection() - 1);
                status(player);
                return;
            }
        }
        while (player.getHealth() > 0 && health > 0) {
            setHealth(health - player.getDamage());
            if (health <= 0) {
                status(player);
                return;
            }
            attack(player);
        }
        status(player);
    }
}
