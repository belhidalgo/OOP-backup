package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.*;

/**
 * Action listener for the Add Edge button.
 */
@AllArgsConstructor
public class AddEdgeListener implements ActionListener {
    private Graph graph;

    /**
     * Execution of the Add Edge button.
     * @param e the event to be processed.
     */
    public void actionPerformed(ActionEvent e) {
        graph.setAddEdge(true);
    }
}
