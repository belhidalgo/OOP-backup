package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;

import javax.swing.*;

/**
 * RemoveEdge Button (removes the selected edge).
 */
public class RemoveEdge extends Button {

    @Override
    public JButton executeButton(String name, Graph graph) {
        JButton button = new JButton(name);
        if (graph.getCurrentEdge() == null) {
            button.setEnabled(false);
        }
        return button;
    }
}
