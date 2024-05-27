package nl.rug.oop.rts.util;

import nl.rug.oop.rts.util.model.Graph;
import nl.rug.oop.rts.util.view.MainFrame;
import lombok.*;

/**
 * Initialization class - to initialize a game.
 */
@NoArgsConstructor
public class Initialization {
    public void initialize() {
        Graph graph = new Graph();
        MainFrame frame = new MainFrame(graph);
        frame.setVisible(true);
    }

}
