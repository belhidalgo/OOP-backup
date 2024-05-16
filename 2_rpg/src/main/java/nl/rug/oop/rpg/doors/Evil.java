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

import static nl.rug.oop.rpg.Game.scan;

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
     * @param room1 is one of the rooms the door is connected to.
     * @param room2 is the other room connected to by the door.
     * @param damage is the damage the room can inflict.
     * @param health is the life the door has left.
     */
    public Evil(String description, Room room1, Room room2, int damage, int health) {
        super(description, room1, room2);
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
    private void status(Player player) {
        if (player.getHealth() > 0) {
            System.out.println("Congratulations! The Evil Door wasn't able to defeat you!");
            player.setRoomBehind(this);
        } else {
            System.out.println("You should be ashamed, you have been killed by a door!");
        }
    }

    /**
     * Combat options.
     * @param player - the player who fights against the door.
     * @param scanner - scanner to read the user's input.
     * @return - true if the player fights until the end, anf false otherwise.
     */
    private boolean combat(Player player, Scanner scanner) {
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("    (0) Fight!!!");
            System.out.println("    (1) Try to scooch away...");
            int option = scan(scanner, 0, 1);
            if (option == 0) {
                if (getHealth() > 0 && player.getHealth() > 0) {
                    setHealth(health - player.getStrength());
                    if (this.getHealth() <= 0) {
                        return true;
                    }
                    System.out.println("You've wounded the door!  Door's health: " + getHealth());
                    attack(player);
                    System.out.println("The door fought back! (Health: " + player.getHealth() + ")");
                }
            } else if (option == 1) {
                System.out.println("Pheww... that was a close call. However, you remain in the same room.");
                return false;
            }
        }
    }

    @Override
    public void interact(Player player) {
        System.out.println("Oh no! The door you decided to cross is evil! Do you still want to cross it? (y/n)");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String choice = scanner.next();
            if (choice.equals("n")) {
                return;
            } else if (choice.equals("y") && player.getProtection() > 0) {
                while (true) {
                    System.out.println("Do you want to use your protection? (y/n)");
                    choice = scanner.next();
                    if (choice.equals("y")) {
                        player.setProtection(player.getProtection() - 1);
                        status(player);
                        return;
                    } else if (choice.equals("n")) {
                        break;
                    } else {
                        System.out.println("Invalid option. Try again.");
                    }
                }
            } else if (choice.equals("y")) {
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
        if (combat(player, scanner)) {
            status(player);
        }
    }
}
