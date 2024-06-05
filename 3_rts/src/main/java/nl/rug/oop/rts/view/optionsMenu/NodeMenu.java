package nl.rug.oop.rts.view.optionsMenu;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.awt.*;

/**
 * NodeMenu class (to display the different options within a node).
 */
public class NodeMenu extends OptionMenu {
    private JTextField nameField;

    /**
     * New EdgeMenu.
     * @param graph - the graph for which we want to create the menu.
     */
    public NodeMenu(Graph graph) {
        super(graph);
        this.nameField = createTextField(graph.getCurrent().getName());
        JPanel panel = new JPanel();
        panel.add(nameField);
        add(panel);
    }

    /**
     * Update the menu.
     * @param g the <code>Graphics</code> object to protect.
     */
    public void paintComponent(Graphics g) {
        Node current = getGraph().getCurrent();
        nameField.setText(current.getName());
    }
}
