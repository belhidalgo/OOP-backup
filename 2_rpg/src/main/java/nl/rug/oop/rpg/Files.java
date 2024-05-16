package nl.rug.oop.rpg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.Scanner;

/**
 * The Files class.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Files {

    private File file;

    /**
     * Quicksave a file.
     */
    public void quickSaveTo() {
        try {
            FileOutputStream stream = new FileOutputStream(this.file);
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
            FileInputStream inputStream = new FileInputStream(this.file);
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
     * @param fileName is the filename we want to check.
     * @param directory is the directory we want to put the file into.
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
            if (file.getName().equals(fileName)) {
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
     * Ask the player to provide a fileName.
     * @param scanner scans the name of the file.
     * @param directory is the directory where we want to save our file.
     */
    public void askFileName(Scanner scanner, File directory){
        System.out.println("Filename:");
        String filename = scanner.next();
        while (!checkFileName(filename, directory)) {
            System.out.println("Invalid filename. Try again.");
            System.out.println("Filename:");
            filename = scanner.next();
        }
        //Save filename
        System.out.println("Save successful.");
    }
}


