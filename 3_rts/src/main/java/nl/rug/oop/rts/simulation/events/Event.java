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
/*
TODO
29. Make sure that the user is somehow aware of what event has happened (and to which army).
You can do this by drawing some fancy stuff or using a simple popup message.
 */