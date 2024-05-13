package nl.rug.oop.rpg.doors;

import lombok.*;
import nl.rug.oop.rpg.Inspectable;
import nl.rug.oop.rpg.Interactable;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.characters.Player;

/**
 * Class Door.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Door implements Inspectable, Interactable {
    private String description;
    private Room roomBehind;

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        player.setRoom(roomBehind);
    }

}
