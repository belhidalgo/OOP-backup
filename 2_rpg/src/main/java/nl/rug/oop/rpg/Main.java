package nl.rug.oop.rpg;

import nl.rug.oop.rpg.characters.Player;
import nl.rug.oop.rpg.characters.NPCs.*;
import nl.rug.oop.rpg.doors.Door;
import nl.rug.oop.rpg.doors.Evil;
import nl.rug.oop.rpg.doors.Trap;

/**
 * The main class.
 */
public class Main {
    /**
     * The main function.
     * @param args the arguments passed on to the main function.
     */
    public static void main(String[] args) {

        Room room2 = new Room("A magically sparkling room with gold lights.");
        Room room3 = new Room("A pitch black space.");
        Door door = new Evil("A black door with a crack", room2, 1, 3);
        Door door2 = new Trap("A mysterious red door", room3, 2);
        Room room1 = new Room("A rather dusty room full of computers.");
        room1.addDoor(door);
        room1.addDoor(door2);
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
        Player player1 = new Player("Player1", room1, 3, 10, 0, 0);
        Game game = new Game();
        game.play(player1);
    }
}
// Add your code here :)