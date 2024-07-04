package nl.rug.oop.rts.util;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.view.MainFrame;
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
        graph.addObserver(frame);
        graph.addObserver(frame.getPanel());
        graph.addObserver(frame.getOptions());
        graph.addObserver(frame.getMenu());
        frame.setVisible(true);
    }

}
