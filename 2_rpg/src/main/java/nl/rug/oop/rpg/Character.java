package nl.rug.oop.rpg;

import lombok.Setter;
import lombok.Getter;

/**
 * superclass Character.
 */

@Getter
@Setter

public class Character {
    /**
    * The damage it can inflict.
    */
    protected int damage;
    /**
     * Life left.
     */
    protected int health;
    /**
     * Money it has.
     */
    protected int money;
    /**
     * A new character.
     * @param damage the damage the character can inflict.
     * @param health the life left of the character.
     * @param money the money left.
     */
    public Character(int damage, int health, int money) {
        this.damage = damage;
        this.health = health;
        this.money = money;
    }
}
