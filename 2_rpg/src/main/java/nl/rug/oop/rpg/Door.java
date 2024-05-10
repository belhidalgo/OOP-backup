package nl.rug.oop.rpg;

import lombok.Getter;
import lombok.Setter;

/**
 * Class Door.
 */
@Getter
@Setter
public class Door implements Inspectable, Interactable {
    private String description;
    private Room roomBehind;

    /**
     * New door.
     * @param description description of the door.
     * @param roomBehind the room behind the door.
     */
    public Door(String description, Room roomBehind) {
        this.description = description;
        this.roomBehind = roomBehind;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        player.setRoom(roomBehind);
    }
}
