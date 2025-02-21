package nl.rug.oop.rts.view;

import javax.swing.*;
import lombok.*;
import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.armies.Faction;
import nl.rug.oop.rts.controller.MouseControl;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.observer.MapObserver;
import nl.rug.oop.rts.util.TextureLoader;
import nl.rug.oop.rts.util.Value;

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
        back = TextureLoader.getInstance().getTexture("mapLotr", Value.PANELWIDTH.getValue(),
                Value.FRAMEHEIGHT.getValue(), true);
        this.graph = graph;
        MouseControl mouseControl = new MouseControl(graph);
        addMouseListener(mouseControl);
        addMouseMotionListener(mouseControl);

    }

    /**
     * Updates view based on changes made to model.
     */
    @Override
    public void update() {
        repaint();
    }

    /**
     * Override the paintComponent method to paint the background.
     * @param g the <code>Graphics</code> object to protect.
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawImage(back, Value.START.getValue(), Value.START.getValue(), null);
        drawEdges(g);
        drawNodes(g);
        drawArmyNode(g);
        drawArmyEdge(g);
    }

    private void drawEdges(Graphics g) {
        for (Edge edge : graph.getEdges()) {
            Graphics2D g2 = (Graphics2D) g;
            if (edge == graph.getCurrentEdge()) {
                g2.setColor(Color.BLUE);
            } else {
                g2.setColor(Color.BLACK);
            }
            float[] dashes = {Value.DASHES.getValue()};
            g2.setStroke(new BasicStroke(Value.STROKEWIDTH.getValue(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    Value.DASHES.getValue(), dashes, Value.START.getValue()));
            g2.drawLine(edge.getNode1().getX() + 45, edge.getNode1().getY() + 45,
                    edge.getNode2().getX() + 45, edge.getNode2().getY() + 45);
        }
    }

    private void drawNodes(Graphics g) {
        for (Node node : graph.getNodes()) {
            Image image;
            g.setColor(Color.BLACK);
            if (node == graph.getCurrent()) {
                image = TextureLoader.getInstance().getTexture("node3", Value.SELECTEDNODESIZE.getValue(),
                        Value.SELECTEDNODESIZE.getValue());
                g.drawImage(image, node.getX() - 3, node.getY() - 3, null);
            } else {
                image = TextureLoader.getInstance().getTexture("node4", Value.NODESIZE.getValue(),
                        Value.NODESIZE.getValue());
                g.drawImage(image, node.getX(), node.getY(), null);
            }
            //g.drawImage(image, node.getX(), node.getY(), null);
            Font font = new Font("Serif", Font.BOLD | Font.ITALIC, Value.FONTSIZE.getValue());
            g.setFont(font);
            int width = g.getFontMetrics().stringWidth(node.getName());
            int startX = node.getX() - width/2;
            g.drawString(node.getName(), startX + 45 , node.getY() + 45);
        }
    }

    private void drawArmyNode(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(Value.STROKEWIDTH.getValue(),
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        for (Node node : graph.getNodes()) {
            int pos1 = 0;
            int pos2 = 0;
            for (Army army : node.getArmies()) {
                Image image = chooseFaction(army.getFaction());
                if (army.getTeam() == 1) {
                    g2.setColor(Color.RED);
                    g2.drawRect(node.getX() - 46 + pos1, node.getY() - 14,
                            Value.ARMYSIZE.getValue() + 2, Value.ARMYSIZE.getValue() + 2);
                    g.drawImage(image, node.getX() - 45 + pos1, node.getY() - 13, null);
                    pos1 = pos1 + 12;
                } else {
                    g2.setColor(Color.BLUE);
                    g2.drawRect(node.getX() - 46 + pos2, node.getY() + 86,
                            Value.ARMYSIZE.getValue() + 2, Value.ARMYSIZE.getValue() + 2);
                    g.drawImage(image, node.getX() - 45 + pos2, node.getY() + 87, null);
                    pos2 = pos2 + 12;
                }
            }

        }
    }

    private void drawArmyEdge(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(Value.STROKEWIDTH.getValue(),
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        for (Edge edge : graph.getEdges()) {
            int x = (edge.getNode1().getX() + edge.getNode2().getX() + 90) / 2 - 20;
            int y = (edge.getNode1().getY() + edge.getNode2().getY() + 90) / 2 - 20;
            for (Army army : edge.getArmies()) {
                Image image = chooseFaction(army.getFaction());
                switch (army.getTeam()) {
                    case 1 -> {
                        g2.setColor(Color.RED);
                        g2.drawRect(x - 1, y - 1, Value.ARMYSIZE.getValue() + 2, Value.ARMYSIZE.getValue() + 2);
                        g.drawImage(image, x, y, null);
                    }
                    case 2 -> {
                        g2.setColor(Color.BLUE);
                        g2.drawRect(x + 39, y + 39, Value.ARMYSIZE.getValue() + 2, Value.ARMYSIZE.getValue() + 2);
                        g.drawImage(image, x + 40, y + 40, null);
                    }
                }
            }
        }
    }

    private Image chooseFaction(Faction faction) {
        Image image = null;
        switch (faction) {
            case MEN -> {
                image = TextureLoader.getInstance().getTexture("factionMen", Value.ARMYSIZE.getValue(),
                        Value.ARMYSIZE.getValue());
            }
            case DWARVES -> {
                image = TextureLoader.getInstance().getTexture("factionDwarves", Value.ARMYSIZE.getValue(),
                        Value.ARMYSIZE.getValue());
            }
            case ELVES -> {
                image = TextureLoader.getInstance().getTexture("factionElves", Value.ARMYSIZE.getValue(),
                        Value.ARMYSIZE.getValue());
            }
            case ISENGARD -> {
                image = TextureLoader.getInstance().getTexture("factionIsengard", Value.ARMYSIZE.getValue(),
                        Value.ARMYSIZE.getValue());
            }
            case MORDOR -> {
                image = TextureLoader.getInstance().getTexture("factionMordor", Value.ARMYSIZE.getValue(),
                        Value.ARMYSIZE.getValue());
            }
        }
        return image;
    }
}
