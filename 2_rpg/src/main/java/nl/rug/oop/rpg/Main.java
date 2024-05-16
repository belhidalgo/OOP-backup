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
        Initialization initialization = new Initialization();
        Player player1 = initialization.setup();
        Game game = new Game();
        game.play(player1);
    }
}
// Add your code here :)