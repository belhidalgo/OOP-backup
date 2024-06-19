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
    private JButton addNode;
    private JButton addEdge;
    private JButton removeNode;
    private JButton removeEdge;
    private  JButton saveGame;

    /**
     * Create a new menu based on a graph.
     * @param graph - the graph we want to edit with the menu.
     */
    public Menu(Graph graph) {
        this.graph = graph;

        addNode = new JButton("Add Location");
        addNode.addActionListener(new AddNodeListener(graph));
        add(addNode);

        removeNode = new JButton("Delete Location");
        removeNode.addActionListener(new RemoveNodeListener(graph));
        add(removeNode);
        removeNode.setEnabled(false);

        addEdge = new JButton("Add Route");
        addEdge.addActionListener(new AddEdgeListener(graph));
        add(addEdge);
        addEdge.setEnabled(false);

        removeEdge = new JButton("Delete Route");
        removeEdge.addActionListener(new RemoveEdgeListener(graph));
        add(removeEdge);
        removeEdge.setEnabled(false);

        JButton timeStep = new JButton("Simulation Step");
        timeStep.addActionListener(new TimeStepListener(graph));
        add(timeStep);

        saveGame = new JButton("Save Game");
        saveGame.addActionListener(new SaveGameListener(graph));
        add(saveGame);

        JButton loadGame = new JButton("Load Game");
        loadGame.addActionListener(new LoadGameListener(graph));
        add(loadGame);
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
