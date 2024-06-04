package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;

import javax.swing.*;

/**
 * Button class to add to the menu bar (subclass of JButton).
 */
public abstract class Button extends JButton {

    /**
     * Create a JButton.
     * @param name - the name of the Button.
     * @param graph - the graph we want to edit.
     * @return the created JButton.
     */
    public abstract JButton executeButton(String name, Graph graph);
}
