package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AddNode Button (creates a node and asks the user to
 * input the name of the given node).
 */

public class AddNode extends Button {

    @Override
    public JButton executeButton(String name, Graph graph) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter city name: ");
                if (input != null) {
                    Node city = new Node(graph.getNodeId() + 1, 5, 5, input);
                    graph.setNodeId(graph.getNodeId() + 1);
                    graph.addNode(city);
                }
            }
        });
        return button;
    }
}
