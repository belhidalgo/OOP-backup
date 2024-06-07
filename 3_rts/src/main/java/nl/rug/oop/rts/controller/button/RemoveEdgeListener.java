package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.*;

/**
 * Action listener for the Remove Edge button.
 */
@AllArgsConstructor
public class RemoveEdgeListener implements ActionListener {
    private Graph graph;

    /**
     * Execution of the Remove Edge button.
     * @param e the event to be processed.
     */
    public void actionPerformed(ActionEvent e) {
        if (graph.getCurrentEdge() != null) {
            //button.setEnabled(true);
            graph.removeEdge(graph.getCurrentEdge());
        }
    }
}
