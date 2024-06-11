package nl.rug.oop.rts.armies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.*;

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

        this.units = new ArrayList<>();
        for (int i = 0; i < numUnits; i++) {
            Unit unit = new Unit(faction.getUnitNames().get(random.nextInt(0, 2)), faction);
            units.add(unit);
        }
    }
}
