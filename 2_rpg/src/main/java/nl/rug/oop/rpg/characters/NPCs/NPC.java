package nl.rug.oop.rpg.characters.NPCs;

import lombok.*;
import nl.rug.oop.rpg.Inspectable;
import nl.rug.oop.rpg.Interactable;
import nl.rug.oop.rpg.characters.Character;

/**
 * The superclass NPC, subclass of Character.
 */
@Getter
@Setter
public abstract class NPC extends Character implements Inspectable, Interactable {
    /**
     * Implement the NPC.
     */
    protected String description;

    /**
     * New NPC.
     * @param description the description of the NPC.
     * @param damage the damage the NPC can inflict.
     * @param health the life of the NPC.
     * @param money the money of the NPC.
     */
    public NPC(String description, int damage, int health, int money) {
        super(damage, health, money);
        this.description = description;
    }
}