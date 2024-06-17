package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.*;
import nl.rug.oop.rts.util.Value;

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
        graph.setAddEdge(false);
        if (input != null && !input.isEmpty()) {
            Node city = new Node(graph.getNodeId() + 1, Value.STARTNEWNODE.getValue(),
                    Value.STARTNEWNODE.getValue(), input);
            graph.setNodeId(graph.getNodeId() + 1);
            graph.addNode(city);
        } else if (input != null) {
            JOptionPane.showMessageDialog(null, "Please enter a valid city name.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
