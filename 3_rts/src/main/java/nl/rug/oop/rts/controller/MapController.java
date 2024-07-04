package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import lombok.*;
import nl.rug.oop.rts.model.Node;

import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Controller - get the user's input and use the model to handle that input.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MapController {
    private Graph graph;

    /**
     * Sets currentEdge as the edge in point point, and null if it doesn't exist.
     * @param point - the point where we want to check if there is an edge.
     * @param current - current edge that is selected.
     */
    public void selectEdge(Point point, Edge current) {
        double x = point.getX();
        double y = point.getY();

        for (Edge edge : graph.getEdges()) {
            double m = ((double)(edge.getNode2().getY() - edge.getNode1().getY())
                    / (double)(edge.getNode2().getX() - edge.getNode1().getX()));
            double c = (edge.getNode1().getY() + 45) - m*(edge.getNode1().getX() + 45);
            if (abs(m*x - y + c) / sqrt(Math.pow(m, 2) + 1) < 5
                    && Math.min(edge.getNode1().getX(), edge.getNode2().getX()) + 40 <= x
                    && x <= Math.max(edge.getNode1().getX(),edge.getNode2().getX()) + 50
                    && Math.min(edge.getNode1().getY(), edge.getNode2().getY()) + 40 <= y
                    && y <= Math.max(edge.getNode1().getY(),edge.getNode2().getY()) + 50) {
                if (current == null || current != edge) {
                    graph.setCurrentEdge(edge);
                }
            }
        }
        graph.notifyObservers();
    }

    /**
     * Sets current as the node in point point, and null if it doesn't exist.
     * @param point - the point where we want to get the node.
     */
    public void selectNode(Point point) {
        Node node = getNodeAtPoint(point);
        if (graph.getCurrent() != null && node == graph.getCurrent()) {
            graph.setCurrent(null);
        } else {
            graph.setCurrent(node);
        }
    }

    /**
     * Get the node in point point.
     * @param point - the point we want to get the node from.
     * @return the node in the given point.
     */
    public Node getNodeAtPoint(Point point) {
        double x = point.getX();
        double y = point.getY();

        for (Node node : graph.getNodes()) {
            if ((x >= node.getX() && x <= (node.getX() + 90)) &&
                    (y >= node.getY() && y <= (node.getY() + 90))) {
                return node;
            }
        }
        return null;
    }
}
