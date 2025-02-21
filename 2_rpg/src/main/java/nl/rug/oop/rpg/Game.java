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
    private void printMenu() {
        System.out.println("What do you want to do?");
        System.out.println("    (0) Look around");
        System.out.println("    (1) Look for a way out");
        System.out.println("    (2) Look for company");
        System.out.println("    (3) See my energy levels");
        System.out.println("    (4) QuickSave");
        System.out.println("    (5) QuickLoad");
        System.out.println("    (6) Save");
        System.out.println("    (7) Load");
        System.out.println("    (8) Exit");
    }

    /**
     * Player chose to exit the game.
     * @param player - player who made the choice.
     * @param scanner - scans the user input.
     * @return - true if the player wants to exit, false otherwise.
     */
    private boolean exit(Player player, Scanner scanner) {
        System.out.println("Are you sure you want to exit? (y/n)");
        while (true) {
            String choice = scanner.next();
            if (choice.equals("n")) {
                return false;
            } else if (choice.equals("y")) {
                return true;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Ask the player to provide a fileName.
     * @param scanner - scans the name of the file.
     * @param directory - the directory where we want to save our file.
     */
    private void askFileName(Scanner scanner, File directory){
        Files files = new Files();
        System.out.println("Filename:");
        String filename = scanner.next();
        while (!files.checkFileName(filename, directory)) {
            System.out.println("Invalid filename. Try again.");
            System.out.println("Filename:");
            filename = scanner.next();
        }
        files.save(filename, directory);
        System.out.println("Save successful.");
    }

    /**
     * Asks the user for an integer n such that lowebound <= n <= upperbound.
     * @param scanner - scans the input.
     * @param lowerBound - the smallest value the user can input.
     * @param upperBound - the greatest value the user can input.
     * @return - returns the scanned integer within bounds.
     */
    public static int scan(Scanner scanner, int lowerBound, int upperBound) {
        int option;
        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
            if (option >= lowerBound && option <= upperBound) {
                return option;
            }
            System.out.println("Invalid choice. Try again.");
        } else {
            scanner.next();
            System.out.println("Invalid character. Please enter a number.");
        }
        return -2;
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
        while (player.getHealth() > 0 && !player.isWin()) {
            printMenu();
            switch (scan(scanner, 0, 8)) {
                case 0:
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
                    askFileName(scanner, saveDirectory);
                    break;
                case 7:
                    files.scanLoadFile(scanner, saveDirectory);
                    break;
                case 8:
                    if (exit(player, scanner)) {
                        return;
                    }
            }
        }
    }
}
