package nl.rug.oop.rpg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.Scanner;

import static nl.rug.oop.rpg.Game.scan;

/**
 * The Files class.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Files implements Serializable {

    private File file;
    @Serial
    private static final long serialVersionUID = 1234;

    /**
     * Quicksave a file.
     */
    public void quickSaveTo() {
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
     */
    public void quickLoadFromFile() {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectStream = new ObjectInputStream(inputStream);
            objectStream.readObject();
            objectStream.close();
            inputStream.close();
        } catch (IOException e) {
            System.err.println("IO Exception");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException");
            throw new RuntimeException(e);
        }
    }

    /**
     * Check if the filename is valid.
     * @param fileName - the filename we want to check.
     * @param directory - the directory we want to put the file into.
     * @return true if it is a valid fileName, and false otherwise.
     */
    public boolean checkFileName(String fileName, File directory) {
        if (fileName == null) {
            System.out.println("File name can't be null.");
            return false;
        }
        if (fileName.length() > 30) {
            System.out.println("File name is too long - It can't exceed 30 characters");
            return false;
        }
        for (File file : directory.listFiles()) {
            if (file.getName().equals(fileName+".ser")) {
                System.out.println("File name already exists");
                return false;
            }
        }
        if (fileName.matches("^[a-zA-Z0-9_-]*$")) {
            return true;
        } else {
            System.out.println("File name contains invalid characters" +
                    " - only letters, numbers, '_' and '-' are allowed (in this order).");
            return false;
        }
    }

    /**
     * Saves current game status with the given name by the user.
     * @param fileName - the name of the file given by the user.
     * @param directory - the directory in which the file should be saved.
     */
    public void save(String fileName, File directory) {
        File saveGame = new File("savedGames/"+fileName+".ser");
        Files savedGames = new Files(saveGame);
        savedGames.quickSaveTo();
    }

    /**
     * It scans the file chosen and loads it.
     * @param scanner - scans the input of the user.
     * @param directory - the directory we want the user to choose a file from.
     */
    public void scanLoadFile(Scanner scanner, File directory) {
        if (directory.listFiles() == null ) {
            System.out.println("No files found");
            return;
        }
        System.out.println("Which file? (-1 : none)");
        int i = 0;
        for (File file : directory.listFiles()) {
            System.out.println(     "("+i+") "+file.getName());
            i++;
        }
        int choice = scan(scanner, -1, i - 1);
        if (choice == -1) {
            System.out.println("No files chosen");
            return;
        }
        i = 0;
        for (File file : directory.listFiles()) {
            if (i == choice) {
                Files loadFiles = new Files(file);
                loadFiles.quickLoadFromFile();
                break;
            }
            i++;
        }
        if (choice == i) {
            System.out.println("Game loaded successfully.");
        } else {
            System.out.println("Loading was unsuccessful.");
        }
    }
}


