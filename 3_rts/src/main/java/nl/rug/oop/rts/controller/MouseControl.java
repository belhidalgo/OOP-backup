package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import lombok.*;

/**
 * Class that controls the mouse.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MouseControl extends MouseAdapter {
    private Node node;
    private Graph graph;

    /**
     * New mouse control.
     * @param graph - the graph we want it to work with.
     */
    public MouseControl(Graph graph) {
        this.graph = graph;
    }

    /**
     * The mouse has been clicked.
     * @param e the event to be processed.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        Node node1 = graph.getSelectedNode(point);
        if (node1 != null) {
            node = node1;
            System.out.println("Node " + node.getId() + " selected");
        }
    }

}
