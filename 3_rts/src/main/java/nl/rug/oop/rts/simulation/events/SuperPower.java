package nl.rug.oop.rts.simulation.events;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.model.Graph;

/**
 * SuperPower event (the army has its damage level increased by 2).
 */
public class SuperPower extends Event {

    public SuperPower(Graph graph) {
        super(graph, PossibleEvents.SUPERPOWER);
    }

    @Override
    public void occur(Army army) {
        for (int i = 0; i < army.getNumUnits(); i++) {
            army.getUnits().get(i).setDamage(army.getUnits().get(i).getDamage() + 2);
        }
    }
}
