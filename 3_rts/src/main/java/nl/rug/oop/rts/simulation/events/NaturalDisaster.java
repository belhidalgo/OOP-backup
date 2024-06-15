package nl.rug.oop.rts.simulation.events;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.util.Value;

import java.util.Random;

/**
 * Natural Disaster event (one of the units of the army is removed).
 */

public class NaturalDisaster extends Event {

    public NaturalDisaster(Graph graph) {
        super(graph, PossibleEvents.DISASTER);
    }

    @Override
    public void occur(Army army) {
        Random rand = new Random();
        int removed = rand.nextInt(Value.START.getValue(), army.getNumUnits());
        army.getUnits().remove(removed);
        army.setNumUnits(army.getUnits().size());
        if (army.getUnits().isEmpty()) {
            if (getGraph().getCurrent() != null) {
                getGraph().removeArmyNode(army, getGraph().getCurrent());
            } else if (getGraph().getCurrentEdge() != null) {
                getGraph().removeArmyEdge(army, getGraph().getCurrentEdge());
            }
        }
    }
}
