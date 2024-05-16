package nl.rug.oop.rpg.characters;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * superclass Character.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public abstract class Character implements Serializable {

    @Serial
    private static final long serialVersionUID = 1100;
    /**
    * The damage it can inflict on others.
    */
    protected int strength;
    /**
     * Life left.
     */
    protected int health;
    /**
     * Money it has.
     */
    protected int money;
}
