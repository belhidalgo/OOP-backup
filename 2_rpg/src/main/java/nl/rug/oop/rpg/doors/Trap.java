package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.Interactable;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.characters.Player;

import java.io.Serial;
import java.io.Serializable;

/**
 * A trap door (subclass of door) that attacks the player.
 */
public class Trap extends Door implements Interactable, Serializable {
    private int damage;
    @Serial
    private static final long serialVersionUID = 1;

    /**
     * We create a new Evil Door.
     * @param description is the description of the door.
     * @param room1 is one of the rooms the door is connected to.
     * @param room2 is the other room connected to by the door.
     * @param damage is the damage the room can inflict.
     */
    public Trap(String description, Room room1, Room room2, int damage) {
        super(description, room1, room2);
        this.damage = damage;
    }

    @Override
    public void interact(Player player) {
        System.out.println("Oh no! The door you decided to cross is a trap!");
        player.setHealth(player.getHealth() - damage);
        if (player.getHealth() > 0) {
            System.out.println("Congratulations! The Evil Door wasn't able to kill you!");
            if (player.getRoom().equals(this.getRoom1())) {
                player.setRoom(this.getRoom2());
            } else if (player.getRoom().equals(this.getRoom2())) {
                player.setRoom(this.getRoom1());
            }
        } else {
            System.out.println("You should be ashamed, you have been killed by a door!");
        }
    }
}

