package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.*;
import nl.rug.oop.rts.simulation.events.Event;
import nl.rug.oop.rts.simulation.events.PossibleEvents;

import javax.swing.*;

/**
 * Action Listener for the Remove Event button.
 */

@AllArgsConstructor
public class RemoveEventListener implements ActionListener {
    private Graph graph;

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isNode;
        PossibleEvents[] possibleEvents;
        if (graph.getCurrent()!= null) {
            isNode = true;
            possibleEvents = new PossibleEvents[graph.getCurrent().getEvents().size()];
            for (int i = 0; i < graph.getCurrent().getEvents().size(); i++) {
                possibleEvents[i] = graph.getCurrent().getEvents().get(i).getPossibleEvents();
            }
            Object selection = select(possibleEvents);
            if (selection != null) {
                for (Event event : graph.getCurrent().getEvents()) {
                    if (event.getPossibleEvents() == (PossibleEvents) selection) {
                        graph.removeEventNode(event, graph.getCurrent());
                        break;
                    }
                }
            }
        } else {
            possibleEvents = new PossibleEvents[graph.getCurrentEdge().getEvents().size()];
            for (int i = 0; i < graph.getCurrentEdge().getEvents().size(); i++) {
                possibleEvents[i] = graph.getCurrentEdge().getEvents().get(i).getPossibleEvents();
            }
            Object selection = select(possibleEvents);
            if (selection != null) {
                for (Event event : graph.getCurrentEdge().getEvents()) {
                    if (event.getPossibleEvents() == (PossibleEvents) selection) {
                        graph.removeEventEdge(event, graph.getCurrentEdge());
                        break;
                    }
                }
            }
        }
    }

    private Object select(PossibleEvents[] possibleEvents) {
        return JOptionPane.showInputDialog(null, "Choose a faction", "Input",
                JOptionPane.INFORMATION_MESSAGE, null, possibleEvents, null);
    }
}
