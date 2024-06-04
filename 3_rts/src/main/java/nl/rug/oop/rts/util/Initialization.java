package nl.rug.oop.rts.util;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.view.MainFrame;
import lombok.*;


/**
 * Initialization class - to avoid initializing in
 * the main class.
 */
@NoArgsConstructor
public class Initialization {
    /**
     * Initializes a map - to avoid initializing in
     * the main class.
     */
    public void initialize() {
        Node node1 = new Node(1, 70, 70, "Mordor");
        Node node2 = new Node(2, 500, 60, "Isengard");
        Edge edge = new Edge(1, "path", node1, node2);
        Graph graph = new Graph();
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(edge);
        MainFrame frame = new MainFrame(graph);
        graph.addObserver(frame);
        graph.addObserver(frame.getPanel());
        graph.addObserver(frame.getMenu());
        frame.setVisible(true);
    }

}
