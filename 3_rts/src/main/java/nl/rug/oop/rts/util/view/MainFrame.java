package nl.rug.oop.rts.util.view;

import nl.rug.oop.rts.util.model.Graph;

import javax.swing.*;
import lombok.*;

/**
 * Main frame class.
 */
@Getter
@Setter
public class MainFrame extends JFrame {
    private Graph graph;

    /**
     * Creates a new frame for a given graph.
     * @param graph - the graph we want to draw.
     */
    public MainFrame(Graph graph) {
        setSize(800, 600);
        setName("Map of Middle-Earth");
        setLocationRelativeTo(null);

        Panel panel = new Panel(graph);
        add(panel);

        //Create menu bar
        JMenuBar menuBar = new JMenuBar();

        //Create the buttons for the menu bar
        JButton addNode = new JButton("Add Location");
        JButton deleteNode = new JButton("Delete Location");
        JButton addEdge = new JButton("Add Route");
        JButton deleteEdge = new JButton("Delete Route");

        //add options to menu bar
        menuBar.add(addEdge);
        menuBar.add(addNode);
        menuBar.add(deleteEdge);
        menuBar.add(deleteNode);

        setJMenuBar(menuBar);

    }
}
