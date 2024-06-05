package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.Interactable;

import java.io.Serializable;
import java.util.Scanner;

import lombok.*;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.characters.Player;

/**
 * Class for the final door to win the game and exit the rooms.
 */
@Getter
@Setter
public class Exit extends Door implements Interactable, Serializable {

    /**
     * The Exit door (subclass of Door).
     * @param description - description of the door.
     * @param room1 - One of the rooms it connects.
     * @param room2 - The other room it leads to.
     */
    public Exit(String description, Room room1, Room room2) {
        super(description, room1, room2);
    }

    @Override
    public void interact(Player player) {
        Scanner scanner = new Scanner(System.in);
        if (player.isKey()) {
            while (true) {
                System.out.println("Do you wish to go through the door? (y/n)");
                String choice = scanner.next();
                if (choice.equals("y")) {
                    player.setWin(true);
                    System.out.println("Congratulations! You won the game!!!");
                    return;
                } else if (choice.equals("n")) {
                    System.out.println("You stay in the room.");
                    return;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Oh no! This door is locked.");
        }
    }

}
