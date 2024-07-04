package nl.rug.oop.rts.armies;

import java.util.Arrays;
import java.util.List;
import lombok.*;

/**
 * Sets possible values of each faction.
 */
@Getter
@AllArgsConstructor
public enum Faction {
    MEN(Arrays.asList("Gondor Soldier", "Tower Guard", "Ithilian Ranger")),
    ELVES(Arrays.asList("Lorien Warrior", "Mirkwood Archer", "Rivendell Lancer")),
    DWARVES(Arrays.asList("Guardian", "Phalanx", "Axe Thrower")),
    MORDOR(Arrays.asList("Orc Warrior", "Orc Pikeman", "Haradrim archer")),
    ISENGARD(Arrays.asList("Uruk-hai", "Uruk Crossbowman", "Warg Rider"));

    @Setter
    private List<String> unitNames;
}
