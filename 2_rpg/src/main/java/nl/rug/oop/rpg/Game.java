package nl.rug.oop.rpg;

import java.io.*;
import java.util.Scanner;
import lombok.*;
import nl.rug.oop.rpg.characters.Player;

/**
 * The Game class.
 */

@NoArgsConstructor

public class Game implements Serializable {

    @Serial
    private static final long serialVersionUID = 1000;

    /**
     * Print the menu with the different options for the player.
     */
    public void printMenu() {
        System.out.println("What do you want to do?");
        System.out.println("    (0) Look around");
        System.out.println("    (1) Look for a way out");
        System.out.println("    (2) Look for company");
        System.out.println("    (3) See my energy levels");
        System.out.println("    (4) QuickSave");
        System.out.println("    (5) QuickLoad");
        System.out.println("    (6) Save");
        System.out.println("    (7) Load");
    }

    /**
     * Play a game.
     * @param player is the player that plays the game.
     */
    public void play(Player player) {
        File saveDirectory = new File("savedgames");
        saveDirectory.mkdir();
        File saveGame = new File("savedGames/quicksave.ser");
        Files files = new Files(saveGame);
        Scanner scanner = new Scanner(System.in);
        while (player.getHealth() > 0) {
            printMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    System.out.print("You see: ");
                    player.lookAround();
                    break;
                case 1:
                    player.wayOut(scanner);
                    break;
                case 2:
                    player.lookForCompany(scanner);
                    break;
                case 3:
                    player.printStatus();
                    break;
                case 4:
                    files.quickSaveTo();
                    break;
                case 5:
                    files.quickLoadFromFile();
                    break;
                case 6:
                    files.askFileName(scanner, saveDirectory);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
