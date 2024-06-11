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
        Node node3 = new Node(3, 130, 120, "Lalaland");
        Node node4 = new Node(4, 270, 300, "Yuppee");
        Edge edge = new Edge(1, "path", node1, node2);
        Edge edge2 = new Edge(2, "path2",node2, node3);
        Edge edge3 = new Edge(3, "path3", node1, node3);
        Edge edge4 = new Edge(4, "path4", node2, node4);
        Graph graph = new Graph();
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addEdge(edge);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        MainFrame frame = new MainFrame(graph);
        graph.addObserver(frame);
        graph.addObserver(frame.getPanel());
        graph.addObserver(frame.getOptions());
        graph.addObserver(frame.getMenu());
        frame.setVisible(true);
    }

}
