package nl.rug.oop.rpg.doors;

import lombok.*;
import nl.rug.oop.rpg.Inspectable;
import nl.rug.oop.rpg.Interactable;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.characters.Player;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class Door.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Door implements Inspectable, Interactable, Serializable {
    private String description;
    private Room room1;
    private Room room2;
    @Serial
    private static final long serialVersionUID = 200;

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        if (player.getRoom().equals(room1)) {
            player.setRoom(room2);
        } else if (player.getRoom().equals(room2)) {
            player.setRoom(room1);
        }
    }

}
