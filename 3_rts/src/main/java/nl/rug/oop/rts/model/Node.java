package nl.rug.oop.rts.model;

import lombok.*;
import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.simulation.events.Event;

import java.util.ArrayList;
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
@ToString
public class Node {
    private int id;
    private int x;
    private int y;
    private String name;
    private List<Edge> edges;
    private List<Army> armies;
    private List<Event> events;

    /**
     * New node.
     * @param id - the id of the node.
     * @param x - the x-coordinate of the node.
     * @param y - the y-coordinate of the node.
     * @param name - the name of the node.
     */
    public Node(int id, int x, int y, String name) {
        edges = new ArrayList<>();
        armies = new ArrayList<>();
        events = new ArrayList<>();
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
    }
}
