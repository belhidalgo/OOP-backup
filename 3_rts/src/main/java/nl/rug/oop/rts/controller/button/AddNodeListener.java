package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.*;

/**
 * Action listener for the Add Node button.
 */
@AllArgsConstructor
public class AddNodeListener implements ActionListener {
    private Graph graph;

    /**
     * Execution of the Add Node button.
     * @param e the event to be processed.
     */
    public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog(null, "Enter city name: ");
        if (input != null) {
            Node city = new Node(graph.getNodeId() + 1, 5, 5, input);
            graph.setNodeId(graph.getNodeId() + 1);
            graph.addNode(city);
        }
    }
}
