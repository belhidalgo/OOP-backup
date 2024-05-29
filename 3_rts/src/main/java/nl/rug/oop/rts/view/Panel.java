package nl.rug.oop.rts.view;

import javax.swing.*;
import lombok.*;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.util.TextureLoader;
import java.awt.*;

/**
 * Panel class.
 */
@Getter
@Setter
public class Panel extends JPanel {
    private Graph graph;
    private Image back;

    /**
     * Create a new Panel.
     * @param graph - the graph we want the Panel to have access to.
     */
    public Panel(Graph graph) {
        back = TextureLoader.getInstance().getTexture("mapLotr", 800, 600);
        this.graph = graph;
    }

    /**
     * Override the paintComponent method to paint the background.
     * @param g the <code>Graphics</code> object to protect.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(back, 0, 0, null);

        //Draw edges
        for (Edge edge : graph.getEdges()) {
            g.drawLine(edge.getNode1().getX(), edge.getNode1().getY(), edge.getNode2().getX(), edge.getNode2().getY());
        }

        //Draw nodes
        for (Node node : graph.getNodes()) {
            Image image = TextureLoader.getInstance().getTexture("node4", 100, 100);
            g.drawImage(image, node.getX(), node.getY(), null);
        }
    }

}
