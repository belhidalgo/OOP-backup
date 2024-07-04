package nl.rug.oop.rts.view.optionsMenu;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.observer.MapObserver;
import nl.rug.oop.rts.util.Value;

import javax.swing.*;
import java.awt.*;

import lombok.*;

/**
 * OptionMenu class (to display the different options).
 */

@Setter
@Getter
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
        g.getFont().deriveFont((float) Value.FONTSIZE2.getValue());
        if (graph.getCurrent() == null && graph.getCurrentEdge() == null) {
            g.drawString("Nothing selected", Value.PANELSTRINGX.getValue(), Value.PANELSTRINGY.getValue());

        } else if (graph.getCurrentEdge() != null) {
            edgeMenu.paintComponent(g);

        } else  {
            nodeMenu.paintComponent(g);
        }
    }

    /**
     * Create a new JTextField with the action depending on whether a node or an edge is selected.
     * @param text - the text we want to appear in the text field.
     * @return - the new text field.
     */
    protected JTextField createTextField(String text) {
        JTextField textField = new JTextField(text, Value.COLUMNSTEXTFIELD.getValue());
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.BLACK);
        textField.addActionListener(e -> {
            if (graph.getCurrent() != null) {
                graph.getCurrent().setName(textField.getText());
            } else {
                graph.getCurrentEdge().setName(textField.getText());
            }
            graph.notifyObservers();
        });
        return textField;
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
