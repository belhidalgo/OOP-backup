package nl.rug.oop.rts.controller.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.*;
import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.armies.Faction;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.simulation.events.*;

import javax.swing.*;

/**
 * Action listener for Add Event button.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AddEventListener implements ActionListener {
    Graph graph;

    /**
     * Performs action of adding an event.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object selection = JOptionPane.showInputDialog(null, "Choose an Event", "Input",
                JOptionPane.INFORMATION_MESSAGE, null, PossibleEvents.values(), null);
        Event event;

        try {
            switch (PossibleEvents.values()[Integer.parseInt(selection.toString())]) {
                case DISASTER -> {
                    event = new NaturalDisaster(graph);
                }
                case REBELLION -> {
                    event = new Rebellion(graph);
                }
                case SUPERPOWER -> {
                    event = new SuperPower(graph);
                }
                case REINFORCEMENT -> {
                    event = new Reinforcement(graph);
                }
            }
        } catch (ClassCastException ex) {
            throw new RuntimeException(ex);
        }
        if (graph.getCurrent() != null) {
            graph.addEventNode(event, graph.getCurrent());
        } else if (graph.getCurrentEdge() != null){
            graph.addEventEdge(event, graph.getCurrentEdge());
        }

    }
}
