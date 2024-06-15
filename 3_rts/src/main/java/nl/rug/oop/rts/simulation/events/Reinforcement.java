package nl.rug.oop.rts.simulation.events;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.armies.Unit;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.util.Value;

import java.util.Random;

/**
 * Reinforcement event (the army has 5 new units).
 */
public class Reinforcement extends Event {

    public Reinforcement(Graph graph) {
        super(graph, PossibleEvents.REINFORCEMENT);
    }

    @Override
    public void occur(Army army) {
        Random random = new Random();
        for (int i = army.getUnits().size(); i < army.getUnits().size() + 5; i++) {
            Unit unit = new Unit(army.getFaction().getUnitNames().get(random.nextInt(Value.START.getValue(), 3)),
                    army.getFaction());
            army.getUnits().add(unit);
        }
        army.setNumUnits(army.getNumUnits() + 5);
    }
}
