package nl.rug.oop.rts.util.view;

import javax.swing.*;
import lombok.*;
import nl.rug.oop.rts.util.model.Graph;

import java.awt.*;

/**
 * Panel class.
 */
@Getter
@Setter
public class Panel extends JPanel {
    private Graph graph;

    /**
     * Create a new Panel.
     * @param graph - the graph we want the Panel to have access to.
     */
    public Panel(Graph graph) {
        setBackground(Color.cyan);
        this.graph = graph;
    }
}
