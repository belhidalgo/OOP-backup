package nl.rug.oop.rts.simulation.events;

import nl.rug.oop.rts.armies.Army;
import lombok.*;
import nl.rug.oop.rts.model.Graph;

/**
 * Abstract class for Events.
 */
@AllArgsConstructor
@Setter
@Getter
public abstract class Event {
    private Graph graph;
    private PossibleEvents possibleEvents;

    public Event(Graph graph) {
        this.graph = graph;
    }

    public abstract void occur(Army army);
}