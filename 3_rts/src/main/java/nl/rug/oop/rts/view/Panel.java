package nl.rug.oop.rts.view;

import javax.swing.*;
import lombok.*;
import nl.rug.oop.rts.controller.MouseControl;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.observer.MapObserver;
import nl.rug.oop.rts.util.TextureLoader;
import java.awt.*;

/**
 * Panel class.
 */
@Getter
@Setter
public class Panel extends JPanel implements MapObserver {
    private Graph graph;
    private Image back;

    /**
     * Create a new Panel.
     * @param graph - the graph we want the Panel to have access to.
     */
    public Panel(Graph graph) {
        back = TextureLoader.getInstance().getTexture("mapLotr", 800, 600);
        this.graph = graph;
        MouseControl mouseControl = new MouseControl(graph);
        addMouseListener(mouseControl);
        addMouseMotionListener(mouseControl);
    }

    /**
     * Updates view based on changes made to model.
     */
    @Override
    public void update(Graph graph) {
        this.graph = graph;
        repaint();
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
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            float[] dashes = {10.0f};
            g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    10.0f, dashes, 0.0f));
            g2.drawLine(edge.getNode1().getX() + 35 , edge.getNode1().getY() + 35,
                    edge.getNode2().getX() + 35, edge.getNode2().getY() + 35);
        }

        //Draw nodes
        for (Node node : graph.getNodes()) {
            Image image;
            if (node.isSelected()) {
                image = TextureLoader.getInstance().getTexture("node3", 75, 75);
            } else {
                image = TextureLoader.getInstance().getTexture("node4", 70, 70);
            }
            g.drawImage(image, node.getX(), node.getY(), null);
            Font font = new Font("Serif", Font.BOLD | Font.ITALIC, 14);
            g.setFont(font);
            /*JLabel label = new JLabel(node.getName());
            label.setOpaque(true);*/
            int width = g.getFontMetrics().stringWidth(node.getName());
            int startX = node.getX() - width/2;
            g.drawString(node.getName(), startX + 35 , node.getY() + 35);
        }
    }

}
