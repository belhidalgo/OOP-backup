package nl.rug.oop.rts.view.optionsMenu;

import lombok.*;
import nl.rug.oop.rts.controller.button.AddEventListener;
import nl.rug.oop.rts.controller.button.RemoveEventListener;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.util.Value;

import javax.swing.*;
import java.awt.*;

/**
 * EdgeMenu class (to display the different options within an edge).
 */
@Setter
@Getter
public class EdgeMenu extends OptionMenu {
    private JTextField nameField;
    private JTextField node1Field;
    private JTextField node2Field;
    private JButton removeEvent;

    /**
     * New EdgeMenu.
     * @param graph - the graph for which we want to create the menu.
     */
    public EdgeMenu(Graph graph) {
        super(graph);
        nameField = createTextField(graph.getCurrentEdge().getName());
        node1Field = createTextField(graph.getCurrentEdge().getNode1().getName());
        node2Field = createTextField(graph.getCurrentEdge().getNode2().getName());
        node1Field.setEditable(false);
        node2Field.setEditable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(Value.ROWSLAYOUT.getValue(), Value.COLSLAYOUT.getValue(), 10, 10));
        panel.add(nameField);
        panel.add(node1Field);
        panel.add(node2Field);

        JButton addEvent = new JButton("Add Event");
        addEvent.addActionListener(new AddEventListener(graph));
        panel.add(addEvent);

        removeEvent = new JButton("Remove Event");
        removeEvent.addActionListener(new RemoveEventListener(graph));
        if (graph.getCurrent() != null && graph.getCurrentEdge().getEvents().isEmpty()) {
            removeEvent.setEnabled(false);
        }
        panel.add(removeEvent);
        add(panel);
    }

    /**
     * Update the Edge Menu.
     * @param g the <code>Graphics</code> object to protect.
     */
    public void paintComponent(Graphics g) {
        Edge current = getGraph().getCurrentEdge();
        nameField.setText(current.getName());
        node1Field.setText(current.getNode1().getName());
        node2Field.setText(current.getNode2().getName());
        removeEvent.setEnabled(!getGraph().getCurrentEdge().getEvents().isEmpty());
    }
}
