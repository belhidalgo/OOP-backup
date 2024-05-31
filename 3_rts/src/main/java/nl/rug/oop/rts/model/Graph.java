package nl.rug.oop.rts.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

/**
 * Class that keeps track of all the nodes and edges.
 */

@Getter
@Setter
@AllArgsConstructor
public class Graph {
    private List<Node> nodes;
    private List<Edge> edges;
    private Node current;

    /**
     * New Graph.
     * @param current - the node we are currently at.
     */
    public Graph(Node current) {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.current = current;
    }

    /**
     * Add a node to the graph.
     * @param node - the node we want to add.
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * Add an edge to the graph.
     * @param edge - the edge we want to add.
     */
    public void addEdge(Edge edge) {
        edges.add(edge);
        edge.getNode1().getEdges().add(edge);
        edge.getNode2().getEdges().add(edge);
    }

    /**
     * Remove an edge from the graph.
     * @param edge - the edge we want to remove.
     */
    public void removeEdge(Edge edge) {
        edges.remove(edge);
        edge.getNode1().getEdges().remove(edge);
        edge.getNode2().getEdges().remove(edge);
    }

    /**
     * Remove a node from the graph.
     * @param node - the node we want to remove.
     */
    public void removeNode(Node node) {
        nodes.remove(node);
        for (Edge edge : node.getEdges()) {
            removeEdge(edge);
        }
    }

    /**
     * Gives the node in point point, and null if it doesn't exist.
     * @param point the point where we want to get the node.
     * @return the node in the given point.
     */
    public Node getSelectedNode(Point point) {
        double x = point.getX();
        double y = point.getY();

        for (Node node : nodes) {
            if ((x >= node.getX() && x <= (node.getX() + 70)) &&
                    (y >= node.getY() && y <= (node.getY() + 70))) {
                return node;
            }
        }
        return null;
    }
}
