package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.*;

/**
 * Action listener for the Remove Node button.
 */
@AllArgsConstructor
public class RemoveNodeListener implements ActionListener {
    private Graph graph;
    //JButton button;

    /**
     * Execution of the Remove Node button.
     * @param e the event to be processed.
     */
    public void actionPerformed(ActionEvent e) {
        if (graph.getCurrent() != null) {
            graph.setAddEdge(false);
            //button.setEnabled(true);
            graph.removeNode(graph.getCurrent());
        }
    }
}
