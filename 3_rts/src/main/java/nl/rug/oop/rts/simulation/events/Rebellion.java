package nl.rug.oop.rts.simulation.events;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.armies.Faction;
import nl.rug.oop.rts.model.Graph;

/**
 * Rebellion event (the army switches teams).
 */
public class Rebellion extends Event {

    public Rebellion(Graph graph) {
        super(graph, PossibleEvents.REBELLION);
    }

    @Override
    public void occur(Army army) {
        switch (army.getTeam()) {
            case 1 -> {
                army.setTeam(2);
                army.setFaction(Faction.MORDOR);
            }
            case 2 -> {
                army.setTeam(1);
                army.setFaction(Faction.MEN);
            }
        }
    }
}
