package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graph.getCurrentEdge() != null) {
                    button.setEnabled(true);
                    graph.removeEdge(graph.getCurrentEdge());
                }
            }
        });
        return button;
    }
}
