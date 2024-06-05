package nl.rug.oop.rpg.characters.NPCs;

import lombok.*;
import nl.rug.oop.rpg.Inspectable;
import nl.rug.oop.rpg.Interactable;
import nl.rug.oop.rpg.characters.Character;

import java.io.Serial;
import java.io.Serializable;

/**
 * The superclass NPC, subclass of Character.
 */
@Getter
@Setter
public abstract class NPC extends Character implements Inspectable, Interactable, Serializable {
    /**
     * Implement the NPC.
     */
    protected String description;
    @Serial
    private static final long serialVersionUID = 900;

    /**
     * New NPC.
     * @param description the description of the NPC.
     * @param strength the strength the NPC can inflict.
     * @param health the life of the NPC.
     * @param money the money of the NPC.
     * @param key determines if the character has the key in its possession
     */
    public NPC(String description, int strength, int health, int money, boolean key) {
        super(strength, health, money, key);
        this.description = description;
    }
}