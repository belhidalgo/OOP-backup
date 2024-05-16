package nl.rug.oop.rpg;

import nl.rug.oop.rpg.characters.NPCs.*;
import nl.rug.oop.rpg.characters.Player;
import nl.rug.oop.rpg.doors.Door;
import nl.rug.oop.rpg.doors.Evil;
import nl.rug.oop.rpg.doors.Trap;

/**
 * Initializes the game.
 */
public class Initialization {
    /**
     * Function that initialises the rooms, doors and characters.
     * @return - returns the main player.
     */
    public Player setup() {
        Room room1 = new Room("A rather dusty room full of computers.");
        Room room2 = new Room("A magically sparkling room with gold lights.");
        Room room3 = new Room("A pitch black space.");

        Door door = new Evil("A black door with a crack", room1, room2, 1, 3);
        Door door2 = new Trap("A mysterious red door", room3, room1, 2);
        Door door3 = new Door("A rusty wooden door", room2, room1);

        room1.addDoor(door);
        room2.addDoor(door);
        room1.addDoor(door2);
        room3.addDoor(door2);
        room2.addDoor(door3);
        room1.addDoor(door3);

        NPC char1 = new Enemy("A dancing cheery strawberry", 2, 10, 50);
        NPC char2 = new Healer("An odd looking dirty kobold", 1, 7, 100);
        NPC char3 = new Wizard("A magical wizard in a large hat", 3, 8, 0);
        NPC char4 = new Trader("A nervous looking man pacing back and forth", 1, 6, 1000);
        NPC char5 = new Enemy("A large fire spitting dragon", 6, 15, 0);

        room1.addNPC(char1);
        room1.addNPC(char2);
        room2.addNPC(char3);
        room3.addNPC(char4);
        room3.addNPC(char5);

        return new Player("Player1", room1, 3, 10, 0, 0);
    }
}
