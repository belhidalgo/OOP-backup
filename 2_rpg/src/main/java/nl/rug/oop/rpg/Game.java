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
     * Quicksave a file.
     * @param file is the file we want to save the game into.
     */
    public void quickSaveTo(File file) {
        try {
            FileOutputStream stream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(this);
            objectStream.close();
            stream.close();
        } catch (IOException e) {
            System.err.println("IO Exception");
            throw new RuntimeException(e);
        }
    }

    /**
     * Quickload a file.
     * @param file is the file we want to quickload from.
     */
    public void quickLoadFromFile(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectStream = new ObjectInputStream(inputStream);
        } catch (IOException e) {
            System.err.println("IO Exception");
            throw new RuntimeException(e);
        }
    }

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
    }

    /**
     * Play a game.
     * @param player is the player that plays the game.
     */
    public void play(Player player) {
        File saveDirectory = new File("savedgames");
        saveDirectory.mkdir();
        File saveGame = new File("/savedGames/quicksave.ser");
        Scanner scanner = new Scanner(System.in);
        while (player.getHealth() > 0) {
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    System.out.print("You see: ");
                    player.lookAround();
                    break;
                case 1:
                    System.out.println("You look around for doors. You see:");
                    player.getRoom().listDoors();
                    System.out.println("Which door do you take? (-1 : stay here)");
                    int doorOption = scanner.nextInt();
                    player.getRoom().chooseDoor(doorOption, player);
                    break;
                case 2:
                    player.lookForCompany();
                    break;
                case 3:
                    player.printStatus();
                    break;
                case 4:
                    quickSaveTo(saveGame);
                    break;
                case 5:
                    quickLoadFromFile(saveGame);
                    break;
            }
        }
    }
}
