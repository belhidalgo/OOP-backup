package nl.rug.oop.rts.armies;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.*;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Node;

/**
 * Class of the armies.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Army {
    private Faction faction;
    private int numUnits;
    private List<Unit> units;
    private int team;
    private Node node;
    private Edge edge;

    /**
     * New army.
     * @param faction - the faction the army belongs to.
     * @param numUnits - the number of units the army has.
     * @param units - a list of the units the army has.
     * @param team - the team the army belongs to.
     */
    public Army(Faction faction, int numUnits, List<Unit> units, int team) {
        this.faction = faction;
        this.numUnits = numUnits;
        this.units = units;
        this.team = team;
    }

    /**
     * Create a new army.
     * @param faction - the faction the army belongs to.
     */
    public Army(Faction faction) {
        Random random = new Random();
        this.numUnits = random.nextInt(10, 50);
        this.faction = faction;
        switch (faction) {
            case MEN, DWARVES, ELVES -> {
                this.team = 1;
            }
            case MORDOR, ISENGARD -> {
                this.team = 2;
            }
        }
        this.node = null;
        this.edge = null;
        this.units = new ArrayList<>();
        for (int i = 0; i < numUnits; i++) {
            Unit unit = new Unit(faction.getUnitNames().get(random.nextInt(0, 2)), faction);
            units.add(unit);
        }
    }
}
