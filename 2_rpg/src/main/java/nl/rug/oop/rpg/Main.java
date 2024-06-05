package nl.rug.oop.rpg;

import nl.rug.oop.rpg.characters.Player;

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