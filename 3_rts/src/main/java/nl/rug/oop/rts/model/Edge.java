package nl.rug.oop.rts.model;

import lombok.*;

/**
 * Class that represents an edge.
 * Every edge has a unique (integer) id and a name.
 * An edge connects node1 and node2.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Edge {
    private int id;
    private String name;
    private Node node1;
    private Node node2;
    private boolean selected = false;

    /**
     * Create a new edge.
     * @param id - id of the edge.
     * @param path - name of the edge.
     * @param node1 - first node it connects.
     * @param node2 - second node it connects.
     */
    public Edge(int id, String path, Node node1, Node node2) {
        this.id = id;
        this.name = path;
        this.node1 = node1;
        this.node2 = node2;
    }
}
