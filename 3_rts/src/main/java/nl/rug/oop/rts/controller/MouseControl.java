package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Edge;
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
        //System.out.println(graph.getCurrent());
        if (graph.isAddEdge()) {
            Node node1 = graph.getCurrent();
            Node node2 = graph.getNodeAtPoint(point);
            if (node2 != null) {
                Edge edge = new Edge(graph.getEdges().size() + 1, "Way from " + node1.getName() +
                        " to " + node2.getName(), node1, node2);
                edge.setNode2(node2);
                graph.addEdge(edge);
            }
            graph.setAddEdge(false);
            graph.getCurrent().setSelected(false);
            graph.setCurrent(null);
        } else {
            if (graph.getCurrentEdge() != null && graph.getCurrentEdge().isSelected()) {
                graph.getCurrentEdge().setSelected(false);
                graph.setCurrentEdge(null);
            }
            graph.selectNode(point);
            if (graph.getCurrent() == null) {
                graph.selectEdge(point);
            }
        }
        graph.notifyObservers();
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
            if (!graph.isAddEdge()) {
                graph.setCurrent(null);
            }
            graph.notifyObservers();
        }
    }

}
