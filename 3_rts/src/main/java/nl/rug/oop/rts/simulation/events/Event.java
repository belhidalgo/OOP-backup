package nl.rug.oop.rts.simulation.events;

import nl.rug.oop.rts.armies.Army;
import lombok.*;
import nl.rug.oop.rts.model.Graph;

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

Of course, just having some battling armies is not the most interesting thing. As such, we want armies to encounter random events.

25. Add something to model a general event. An event affects the army that encounters said event. Some examples of events could be:

	- Reinforcements event: adds units to the army.
	- Natural disaster event: removes a number of units from the army.
	- Hidden weaponry event: improves the weapons of an army, giving their units increased damage.

	However, you are of course encouraged to come up with your own events as well. **You should Implement at least three different kinds of events.**

26. Nodes and edges should both be able to have events.
27. Allows the user to add/remove events to/from nodes & edges. Once again, you can use a `JOptionPane` to allow the user to select one of the existing events.
28. Whenever an army arrives at a node/edge for the first time during a simulation step, it should encounter one of the events present at the said location at random:

	- There should be a chance not to encounter any event at all (e.g. 50%).
	- If the army encounters an event, it should be a random available event from the node/edge the army is on.

29. Make sure that the user is somehow aware of what event has happened (and to which army). You can do this by drawing some fancy stuff or using a simple popup message.

 */