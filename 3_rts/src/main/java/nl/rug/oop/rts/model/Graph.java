package nl.rug.oop.rts.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import nl.rug.oop.rts.observer.MapObservable;
import nl.rug.oop.rts.observer.MapObserver;

/**
 * Class that keeps track of all the nodes and edges.
 */

@Getter
@Setter
@AllArgsConstructor
public class Graph implements MapObservable {
    private List<Node> nodes;
    private List<Edge> edges;
    private List<MapObserver> observers;
    private Node current;

    /**
     * New Graph.
     */
    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.current = null;
    }

    /**
     * Add a node to the graph.
     * @param node - the node we want to add.
     */
    public void addNode(Node node) {
        nodes.add(node);
        notifyObservers();
    }

    /**
     * Add an edge to the graph.
     * @param edge - the edge we want to add.
     */
    public void addEdge(Edge edge) {
        edges.add(edge);
        edge.getNode1().getEdges().add(edge);
        edge.getNode2().getEdges().add(edge);
        notifyObservers();
    }

    /**
     * Remove an edge from the graph.
     * @param edge - the edge we want to remove.
     */
    public void removeEdge(Edge edge) {
        edges.remove(edge);
        edge.getNode1().getEdges().remove(edge);
        edge.getNode2().getEdges().remove(edge);
        notifyObservers();
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
        notifyObservers();
    }

    @Override
    public void addObserver(MapObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MapObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (MapObserver observer : observers) {
            observer.update(this);
        }
    }

    /**
     * Gives the node in point point, and null if it doesn't exist.
     * @param point the point where we want to get the node.
     */
    public void getSelectedNode(Point point) {
        double x = point.getX();
        double y = point.getY();

        if (current != null) {
            current.setSelected(false);
            current = null;
        } else {
            for (Node node : nodes) {
                if ((x >= node.getX() && x <= (node.getX() + 70)) &&
                        (y >= node.getY() && y <= (node.getY() + 70))) {
                    node.setSelected(true);
                    current = node;
                }
            }
        }
        notifyObservers();
    }
}
