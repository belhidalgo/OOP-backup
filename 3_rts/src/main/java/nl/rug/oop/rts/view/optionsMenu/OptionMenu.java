package nl.rug.oop.rts.view.optionsMenu;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.observer.MapObserver;

import javax.swing.*;
import java.awt.*;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OptionMenu extends JPanel implements MapObserver {
    private Graph graph;
    private NodeMenu nodeMenu = null;
    private EdgeMenu edgeMenu = null;

    public OptionMenu(Graph graph) {
        this.graph = graph;
    }

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
