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
@NoArgsConstructor
public class MouseControl extends MouseAdapter {
    private Graph graph;

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
        graph.getSelectedNode(point);
        /*if (graph.getCurrent() != null) {
            removeNode.setEnabled(true);
        } else {
            removeNode.setEnabled(false);
        }*/
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (graph.getCurrent() != null) {
            Node moving = graph.getCurrent();
            moving.setX(e.getX() - 35);
            moving.setY(e.getY() - 35);
            graph.notifyObservers();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (graph.getCurrent() != null) {
            graph.getCurrent().setSelected(false);
            graph.setCurrent(null);
            graph.notifyObservers();
        }
    }

}
