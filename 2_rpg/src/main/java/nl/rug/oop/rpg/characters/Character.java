package nl.rug.oop.rpg.characters;

import lombok.*;

/**
 * superclass Character.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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
}
