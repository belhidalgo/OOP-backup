package nl.rug.oop.rts.armies;

import lombok.*;

/**
 * Unit class - members of an army.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Unit {
    private int damage;
    private int health;
    private String name;

    /**
     * Creates unit based on its faction.
     * @param name - assigns name to the unit.
     * @param faction - categorizes units into different factions.
     */
    public Unit(String name, Faction faction) {
        this.name = name;
        switch(faction) {
            case MEN -> {
                this.damage = 2;
                this.health = 8;
            }
            case ELVES -> {
                this.damage = 3;
                this.health = 10;
            }
            case MORDOR -> {
                this.damage = 5;
                this.health = 10;
            }
            case DWARVES -> {
                this.damage = 2;
                this.health = 7;
            }
            case ISENGARD -> {
                this.damage = 3;
                this.health = 6;
            }
        }
    }
}
