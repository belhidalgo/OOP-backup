package nl.rug.oop.rts.view.optionsMenu;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        this.nameField = new JTextField(graph.getCurrent().getName(), 15);
        nameField.setBackground(Color.LIGHT_GRAY);
        nameField.setForeground(Color.BLACK);
        //nameField.setBounds(10, 20, 70, 20);
        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graph.getCurrent().setName(nameField.getText());
                graph.notifyObservers();
            }
        });
        add(nameField);
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
