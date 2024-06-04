package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import lombok.*;
import nl.rug.oop.rts.observer.MapObserver;

import static com.formdev.flatlaf.FlatLaf.updateUI;

/**
 * Main frame class.
 */
@Getter
@Setter
public class MainFrame extends JFrame implements MapObserver {
    private Graph graph;
    private Panel panel;
    private Menu menu;

    /**
     * Creates a new frame for a given graph.
     * @param graph - the graph we want to draw.
     */
    public MainFrame(Graph graph) {
        setSize(800, 600);
        setName("Map of Middle-Earth");
        setLocationRelativeTo(null);

        this.panel = new Panel(graph);
        add(panel);

        this.menu = new Menu(graph);
        setJMenuBar(menu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void update() {
        repaint();
        updateUI();
    }
}
