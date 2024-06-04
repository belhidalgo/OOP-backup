package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * RemoveNode Button (deletes the selected node).
 */
public class RemoveNode extends Button {

    @Override
    public JButton executeButton(String name, Graph graph) {
        JButton button = new JButton(name);
        if (graph.getCurrent() == null) {
            button.setEnabled(false);
        }
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graph.getCurrent() != null && graph.getCurrent().isSelected()) {
                    button.setEnabled(true);
                    graph.removeNode(graph.getCurrent());
                }
            }
        });
        return button;
    }
}
