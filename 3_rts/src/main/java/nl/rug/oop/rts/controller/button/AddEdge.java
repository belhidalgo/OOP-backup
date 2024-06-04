package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;

import javax.swing.*;

/**
 * AddEdge Button (creates an edge between the selected node
 * and the one selected afterwards).
 */

public class AddEdge extends Button {

    /**
     * Create a JButton that creates a route.
     * @param name - name of the route.
     * @param graph - graph we want to edit.
     * @return the new JButton.
     */
    @Override
    public JButton executeButton(String name, Graph graph) {
        JButton button = new JButton(name);
        if (graph.getCurrent() == null) {
            button.setEnabled(false);
        }
        /*button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graph.getCurrent() != null) {
                }
            }
        });*/
        return button;
    }
}
