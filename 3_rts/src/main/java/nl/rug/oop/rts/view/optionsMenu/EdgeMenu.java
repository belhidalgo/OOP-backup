package nl.rug.oop.rts.view.optionsMenu;

import lombok.*;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EdgeMenu class (to display the different options within an edge).
 */
@Setter
@Getter
public class EdgeMenu extends OptionMenu {
    private JTextField nameField;
    private JTextField node1Field;
    private JTextField node2Field;

    /**
     * New EdgeMenu.
     * @param graph - the graph for which we want to create the menu.
     */
    public EdgeMenu(Graph graph) {
        super(graph);
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new GridLayout(1, 3));
        nameField = new JTextField(graph.getCurrentEdge().getName(), 15);
        nameField.setBackground(Color.LIGHT_GRAY);
        nameField.setForeground(Color.BLACK);
        nameField.setEditable(true);
        nameField.setBounds(10, 40, 70, 20);
        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graph.getCurrent().setName(nameField.getText());
                graph.notifyObservers();
            }
        });
        this.add(nameField);

        node1Field = new JTextField(graph.getCurrentEdge().getNode1().getName(), 15);
        node1Field.setBackground(Color.LIGHT_GRAY);
        node1Field.setForeground(Color.BLACK);
        node1Field.setBounds(10, 80, 70, 20);
        node1Field.setEditable(false);
        add(node1Field);

        node2Field = new JTextField(graph.getCurrentEdge().getNode2().getName(), 15);
        node2Field.setBackground(Color.LIGHT_GRAY);
        node2Field.setForeground(Color.BLACK);
        node2Field.setBounds(10, 120, 70, 20);
        node2Field.setEditable(false);
        add(node2Field);
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
    }
}
