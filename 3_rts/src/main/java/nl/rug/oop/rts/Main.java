package nl.rug.oop.rts;

import com.formdev.flatlaf.FlatDarculaLaf;
import nl.rug.oop.rts.util.Initialization;

/**
 * Main class of the application. Add more details here.
 */
public class Main {

    /**
     * Main function. Add more details here.
     *
     * @param args Commandline arguments.
     */
    public static void main(String[] args) {

        FlatDarculaLaf.setup();// Dark mode
        Initialization initialization = new Initialization();
        initialization.initialize();
    }
}