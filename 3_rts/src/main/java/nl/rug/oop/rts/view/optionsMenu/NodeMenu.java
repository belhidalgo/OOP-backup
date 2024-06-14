package nl.rug.oop.rts.view.optionsMenu;

import nl.rug.oop.rts.controller.button.AddArmyListener;
import nl.rug.oop.rts.controller.button.AddEventListener;
import nl.rug.oop.rts.controller.button.RemoveArmyListener;
import nl.rug.oop.rts.controller.button.RemoveEventListener;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.awt.*;

/**
 * NodeMenu class (to display the different options within a node).
 */
public class NodeMenu extends OptionMenu {
    private JTextField nameField;
    private JButton addArmy;
    private JButton removeArmy;

    /**
     * New EdgeMenu.
     * @param graph - the graph for which we want to create the menu.
     */
    public NodeMenu(Graph graph) {
        super(graph);
        this.nameField = createTextField(graph.getCurrent().getName());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.add(nameField);

        addArmy = new JButton("Add Army");
        addArmy.addActionListener(new AddArmyListener(graph));
        panel.add(addArmy);

        removeArmy = new JButton("Remove Army");
        removeArmy.addActionListener(new RemoveArmyListener(graph));
        if (graph.getCurrent() != null && graph.getCurrent().getArmies().isEmpty()) {
            removeArmy.setEnabled(false);
        }
        panel.add(removeArmy);

        JButton addEvent = new JButton("Add Event");
        addEvent.addActionListener(new AddEventListener(graph));
        panel.add(addEvent);

        JButton removeEvent = new JButton("Remove Event");
        removeEvent.addActionListener(new RemoveEventListener(graph));
        panel.add(removeEvent);

        add(panel);
    }

    /**
     * Update the menu.
     * @param g the <code>Graphics</code> object to protect.
     */
    public void paintComponent(Graphics g) {
        Node current = getGraph().getCurrent();
        nameField.setText(current.getName());
        removeArmy.setEnabled(!getGraph().getCurrent().getArmies().isEmpty());
    }
}
