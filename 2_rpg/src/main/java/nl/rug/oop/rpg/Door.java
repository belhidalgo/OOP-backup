package nl.rug.oop.rpg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Door implements Inspectable, Interactable {
    private String description;
    private Room roomBehind;

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
