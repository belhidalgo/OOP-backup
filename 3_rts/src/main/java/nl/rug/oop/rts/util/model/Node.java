package nl.rug.oop.rts.util.model;

import lombok.*;

import java.util.List;

/**
 * Class that represents a node.
 * Every node has a unique (integer) id and a name.
 * Every node has a list of edges.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Node {
    private int id;
    private String name;
    private List<Edge> edges;
}
