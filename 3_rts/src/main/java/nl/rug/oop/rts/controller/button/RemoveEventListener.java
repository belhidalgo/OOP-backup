package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.armies.Faction;
import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.*;
import nl.rug.oop.rts.simulation.events.Event;
import nl.rug.oop.rts.simulation.events.PossibleEvents;

import javax.swing.*;

@AllArgsConstructor
public class RemoveEventListener implements ActionListener {
    private Graph graph;

    @Override
    public void actionPerformed(ActionEvent e) {
        Boolean isNode;
        PossibleEvents[] possibleEvents;
        if (graph.getCurrent()!= null) {
            isNode = true;
            possibleEvents = new PossibleEvents[graph.getCurrent().getEvents().size()];
        } else {
            isNode = false;
            possibleEvents = new PossibleEvents[graph.getCurrentEdge().getEvents().size()];
        }
        if (isNode) {
            for (int i = 0; i < graph.getCurrent().getEvents().size(); i++) {
                possibleEvents[i] = graph.getCurrent().getEvents().get(i).getPossibleEvents();
            }
        } else {
            for (int i = 0; i < graph.getCurrentEdge().getEvents().size(); i++) {
                possibleEvents[i] = graph.getCurrentEdge().getEvents().get(i).getPossibleEvents();
            }
        }
        Object selection = JOptionPane.showInputDialog(null, "Choose a faction", "Input",
                JOptionPane.INFORMATION_MESSAGE, null, possibleEvents, null);
        if (isNode) {
            for (Event event : graph.getCurrent().getEvents()) {
                if (event.getPossibleEvents() == (PossibleEvents) selection) {
                    graph.removeEventNode(event, graph.getCurrent());
                    break;
                }
            }
        } else {
            for (Event event : graph.getCurrent().getEvents()) {
                if (event.getPossibleEvents() == (PossibleEvents) selection) {
                    graph.removeEventEdge(event, graph.getCurrentEdge());
                    break;
                }
            }
        }
    }
}
