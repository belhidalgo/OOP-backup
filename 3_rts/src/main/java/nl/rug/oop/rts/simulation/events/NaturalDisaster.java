package nl.rug.oop.rts.simulation.events;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import java.util.Random;

public class NaturalDisaster extends Event {

    public NaturalDisaster(Graph graph) {
        super(graph);
        setPossibleEvents(PossibleEvents.DISASTER);
    }

    @Override
    public void occur(Army army) {
        Random rand = new Random();
        int removed = rand.nextInt(army.getNumUnits());
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
