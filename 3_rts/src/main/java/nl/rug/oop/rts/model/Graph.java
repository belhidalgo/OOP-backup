package nl.rug.oop.rts.model;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
import nl.rug.oop.rts.armies.Army;
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

    /**
     * Add an army to the current node of the graph.
     * @param army - the army we want to add.
     * @param node - adds the army to the list of this node.
     */
    public void addArmyNode(Army army, Node node) {
        node.getArmies().add(army);
        army.setNode(node);
        notifyObservers();
    }

    /**
     * Remove an army from the current node of the graph.
     * @param army - the army we want to remove.
     * @param node - removes the army from this node.
     */
    public void removeArmyNode(Army army, Node node) {
        node.getArmies().remove(army);
        notifyObservers();
    }

    /**
     * Adds an army to the list of armies of the edge.
     * @param army - the army we want to add.
     * @param edge - army is added to this edge.
     */
    public void addArmyEdge(Army army, Edge edge) {
        edge.getArmies().add(army);
        army.setEdge(edge);
        notifyObservers();
    }

    /**
     * Removes an army from the edge.
     * @param army - the army we want to remove.
     * @param edge - army is removed from this edge.
     */
    public void removeArmyEdge(Army army, Edge edge) {
        edge.getArmies().remove(army);
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
            observer.update();
        }
    }
}
