package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import lombok.*;
import nl.rug.oop.rts.observer.MapObserver;
import nl.rug.oop.rts.util.Value;
import nl.rug.oop.rts.view.optionsMenu.OptionMenu;

/**
 * Main frame class.
 */
@Getter
@Setter
public class MainFrame extends JFrame implements MapObserver {
    private Graph graph;
    private Panel panel;
    private OptionMenu options;
    private Menu menu;

    /**
     * Creates a new frame for a given graph.
     * @param graph - the graph we want to draw.
     */
    public MainFrame(Graph graph) {
        setSize(Value.FRAMEWIDTH.getValue(), Value.FRAMEHEIGHT.getValue());
        setName("Map of Middle-Earth");
        setLocationRelativeTo(null);

        this.panel = new Panel(graph);
        add(panel);

        this.options = new OptionMenu(graph);
        add(options);

        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, options, panel);
        sl.setDividerLocation(Value.DIVIDERLOCATION.getValue());

        add(sl);

        this.menu = new Menu(graph);
        setJMenuBar(menu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void update() {
        repaint();
    }
}
