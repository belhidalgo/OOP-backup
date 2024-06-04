package nl.rug.oop.rts.view.optionsMenu;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.observer.MapObserver;

import javax.swing.*;
import java.awt.*;

import lombok.*;

/**
 * OptionMenu class (to display the different options).
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OptionMenu extends JPanel implements MapObserver {
    private Graph graph;
    private NodeMenu nodeMenu = null;
    private EdgeMenu edgeMenu = null;

    /**
     * New OptionMenu.
     * @param graph - the graph for which we want to create the option menu.
     */
    public OptionMenu(Graph graph) {
        this.graph = graph;
    }

    /**
     * Draw the Menu.
     * @param g the <code>Graphics</code> object to protect.
     */
    public void paintComponent(Graphics g) {
        setBackground(Color.DARK_GRAY);
        g.getFont().deriveFont(20.0f);
        if (graph.getCurrent() == null && graph.getCurrentEdge() == null) {
            g.drawString("Nothing selected", 10, 20);

        } else if (graph.getCurrentEdge() != null) {
            edgeMenu.paintComponent(g);
        } else  {
            nodeMenu.paintComponent(g);
        }
    }

    /**
     * Update the menu.
     */
    @Override
    public void update() {
        if (nodeMenu != null) {
            remove(nodeMenu);
        }
        if (edgeMenu != null) {
            remove(edgeMenu);
        }
        if (graph.getCurrent() != null) {
            nodeMenu = new NodeMenu(graph);
            add(nodeMenu);
        }
        if (graph.getCurrentEdge() != null) {
            edgeMenu = new EdgeMenu(graph);
            add(edgeMenu);
        }
    }
}
