package nl.rug.oop.rts.util;

import nl.rug.oop.rts.util.model.Graph;
import nl.rug.oop.rts.util.view.MainFrame;
import lombok.*;

/**
 * Initialization class - to avoid initializing in
 * the main class.
 */
@NoArgsConstructor
public class Initialization {
    /**
     * Initializes a map - to avoid initializing in
     * the main class.
     */
    public void initialize() {
        Graph graph = new Graph();
        MainFrame frame = new MainFrame(graph);
        frame.setVisible(true);
    }

}
