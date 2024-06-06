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
}
