package nl.rug.oop.rts.simulation.events;

import lombok.*;
import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.model.Graph;

public class SuperPower extends Event {

    public SuperPower(Graph graph) {
        super(graph);
        setPossibleEvents(PossibleEvents.SUPERPOWER);
    }

    @Override
    public void occur(Army army) {
        for (int i = 0; i < army.getNumUnits(); i++) {
            army.getUnits().get(i).setDamage(army.getUnits().get(i).getDamage() + 2);
        }
    }
}
