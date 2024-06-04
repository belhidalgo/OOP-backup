package nl.rug.oop.rts.view;

import javax.swing.*;

import nl.rug.oop.rts.controller.button.*;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.observer.MapObserver;

/**
 * Menu class - to create the menu bar.
 */
public class Menu extends JMenuBar implements MapObserver {
    private Graph graph;
    private JButton addEdge;
    private JButton removeNode;
    private JButton removeEdge;

    /**
     * Create a new menu based on a graph.
     * @param graph - the graph we want to edit with the menu.
     */
    public Menu(Graph graph) {
        this.graph = graph;
        JButton newButton = new AddNode().executeButton("Add Location", graph);
        add(newButton);

        removeNode = new RemoveNode().executeButton("Delete Location", graph);
        add(removeNode);

        addEdge = new AddEdge().executeButton("Add Route", graph);
        add(addEdge);

        removeEdge = new RemoveEdge().executeButton("Delete Route", graph);
        add(removeEdge);
    }

    @Override
    public void update() {
        if (graph.getCurrent() == null) {
            addEdge.setEnabled(false);
            removeNode.setEnabled(false);
        } else {
            addEdge.setEnabled(true);
            removeNode.setEnabled(true);
        }
        if (graph.getCurrentEdge() == null) {
            removeEdge.setEnabled(false);
        } else {
            removeEdge.setEnabled(true);
        }
        this.updateUI();
    }

}
