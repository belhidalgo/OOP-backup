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

    @Setter(AccessLevel.NONE)
    private Node current;
    private Edge currentEdge;
    private int nodeId;
    private boolean addEdge;

    /**
     * New Graph.
     */
    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.current = null;
        this.currentEdge = null;
        this.addEdge = false;
    }

    /**
     * Set the selected node to current.
     * @param current - the node we want to select.
     */
    public void setCurrent(Node current) {
        System.out.println("Set node to " + current);
        this.current = current;
        notifyObservers();
    }

    /**
     * Set the selected edge to currentEdge.
     * @param currentEdge - the edge we want to select.
     */
    public void setCurrentEdge(Edge currentEdge) {
        this.currentEdge = currentEdge;
        notifyObservers();
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
        edge.getNode1().getEdges().remove(edge);
        edge.getNode2().getEdges().remove(edge);
        edges.remove(edge);
        setCurrentEdge(null);
        notifyObservers();
    }

    /**
     * Remove a node from the graph.
     * @param node - the node we want to remove.
     */
    public void removeNode(Node node) {
        nodes.remove(node);
        List<Edge> edgesToRemove = new ArrayList<>(node.getEdges());
        for (Edge edge : edgesToRemove) {
            removeEdge(edge);
        }
        setCurrent(null);
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
            observer.update();
        }
    }

    /**
     * Sets current as the node in point point, and null if it doesn't exist.
     * @param point - the point where we want to get the node.
     */
    public void selectNode(Point point) {
        Node node = getNodeAtPoint(point);
        if (current != null && node == current) {
            System.out.println("Selected node: " + point);
            setCurrent(null);
        } else {
            setCurrent(node);
        }
    }

    /**
     * Sets currentEdge as the edge in point point, and null if it doesn't exist.
     * @param point -
     */
    public void selectEdge(Point point) {
        double x = point.getX();
        double y = point.getY();

        if (currentEdge != null) {
            setCurrentEdge(null);
        } else {
            for (Edge edge : edges) {
                double m = ((double)(edge.getNode2().getY() - edge.getNode1().getY())
                        / (double)(edge.getNode2().getX() - edge.getNode1().getX()));
                double c = (edge.getNode1().getY() + 35) - m*(edge.getNode1().getX() + 35);
                if ((y < m * x + c + 5) && (y > m * x + c - 5)) {
                    currentEdge = edge;
                }
            }
        }
        notifyObservers();
    }

    /**
     * Get the node in point point.
     * @param point - the point we want to get the node from.
     * @return the node in the given point.
     */
    public Node getNodeAtPoint(Point point) {
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
