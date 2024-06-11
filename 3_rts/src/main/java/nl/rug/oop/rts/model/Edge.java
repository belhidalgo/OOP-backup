package nl.rug.oop.rts.model;

import lombok.*;
import nl.rug.oop.rts.armies.Army;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents an edge.
 * Every edge has a unique (integer) id and a name.
 * An edge connects node1 and node2.
 */

@Getter
@Setter
@NoArgsConstructor
public class Edge {
    private int id;
    private String name;
    private Node node1;
    private Node node2;
    private List<Army> armies;

    /**
     * Create a new edge.
     * @param id - id of the edge.
     * @param name - name of the path.
     * @param node1 - one of the nodes it connects.
     * @param node2 - the other node it connects.
     */
    public Edge(int id, String name, Node node1, Node node2) {
        this.armies = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
    }
}
