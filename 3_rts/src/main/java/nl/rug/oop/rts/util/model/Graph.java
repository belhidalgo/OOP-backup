package nl.rug.oop.rts.util.model;

import java.util.List;
import lombok.*;

/**
 * Class that keeps track of all the nodes and edges.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Graph {
    private List<Node> nodes;
    private List<Edge> edges;
    private Node current;

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
}
