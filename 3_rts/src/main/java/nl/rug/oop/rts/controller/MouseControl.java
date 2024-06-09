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
    private MapController mapController;
    private boolean isDragged = false;

    public MouseControl(Graph graph) {
        this.graph = graph;
        this.mapController = new MapController(graph);
    }

    /**
     * The mouse has been clicked.
     * @param e the event to be processed.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        if (graph.isAddEdge()) {
            Node node1 = graph.getCurrent();
            Node node2 = mapController.getNodeAtPoint(point);
            if (node2 != null) {
                Edge edge = new Edge(graph.getEdges().size() + 1, "Way from " + node1.getName() +
                        " to " + node2.getName(), node1, node2);
                edge.setNode2(node2);
                graph.addEdge(edge);
            }
            graph.setAddEdge(false);
            graph.setCurrent(null);
        } else {
            if (graph.getCurrentEdge() != null) {
                graph.setCurrentEdge(null);
            }
            boolean isSelected = false;
            if (graph.getCurrent() != null) {
                isSelected = true;
            }
            mapController.selectNode(point);
            if (graph.getCurrent() == null && !isSelected) {
                mapController.selectEdge(point);
            }
        }
        graph.notifyObservers();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setDragged(true);
        if (graph.getCurrent() == null) {
            graph.setCurrent(mapController.getNodeAtPoint(e.getPoint()));
        }
        if (graph.getCurrent() != null) {
            if (graph.getCurrentEdge() != null) {
                graph.setCurrentEdge(null);
            }
            Node moving = graph.getCurrent();
            moving.setX(e.getX() - 35);
            moving.setY(e.getY() - 35);
            graph.notifyObservers();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (graph.getCurrent() != null && !graph.isAddEdge() && isDragged) {
            graph.setCurrent(null);
            setDragged(false);
            graph.notifyObservers();
        }
    }
}
