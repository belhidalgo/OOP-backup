package nl.rug.oop.rpg;

import lombok.*;
import nl.rug.oop.rpg.characters.NPCs.NPC;
import nl.rug.oop.rpg.characters.Player;
import nl.rug.oop.rpg.doors.Door;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * The room class.
 */

@Setter
@Getter
@NoArgsConstructor
public class Room implements Inspectable, Serializable {
    private String description;
    private List<Door> doors;
    private List<NPC> NPCs;
    @Serial
    private static final long serialVersionUID = 400;

    /**
     * New room.
     * @param description description of the room.
     */
    public Room(String description) {
        this.description = description;
        doors = new ArrayList<>();
        NPCs = new ArrayList<>();
    }

    public void addDoor(Door door) {
        doors.add(door);
    }

    public void addNPC(NPC npc){
        NPCs.add(npc);
    }

    @Override
    public void inspect() {
        System.out.println(description + " The room has"+ doors.size() + " doors.");
    }

    /**
     * Lists the description of each door in order.
     */
    public void listDoors(){
        for (int i=0; i<doors.size(); i++) {
            System.out.println("    ("+i+") "+ doors.get(i).getDescription());
        }
    }

    /**
     * Lists the description of each NPC in order.
     */
    public void listNPCs() {
        for (int i=0; i<NPCs.size(); i++) {
            System.out.print("    ("+ i +") ");
            NPCs.get(i).inspect();
        }
    }

    /**
     * Cross the door chosen.
     * @param option the option chosen.
     * @param player the player that chooses the option.
     */
    public void chooseDoor(int option, Player player) {
        if (option == -1) {
            System.out.println("You stay here");
        } else {
            doors.get(option).interact(player);
            if (player.getHealth() > 0) {
                System.out.println("You go through the door");
            }
        }
    }

    /**
     * Interact with the chosen NPC.
     * @param option the option chosen.
     * @param player the player that chooses.
     */
    public void chooseNPC(int option, Player player) {
        if (option == -1) {
            System.out.println("You do nothing");
        } else {
            NPCs.get(option).interact(player);
            if (NPCs.get(option).getHealth() <= 0) {
                NPCs.remove(option);
            }
        }
    }
}
