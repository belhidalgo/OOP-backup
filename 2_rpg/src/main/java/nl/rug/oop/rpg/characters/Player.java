package nl.rug.oop.rpg.characters;

import lombok.*;
import nl.rug.oop.rpg.Attackable;
import nl.rug.oop.rpg.Room;

import java.io.Serial;
import java.io.Serializable;

/**
 * The player class.
 */

@Getter
@Setter

public class Player extends Character implements Attackable, Serializable {
    /**
     * Implement a player.
     */
    private String name;
    private Room room;
    private int protection;
    @Serial
    private static final long serialVersionUID = 100;

    /**
     * New Player.
     * @param name the name of the player.
     * @param room the room where the player is.
     * @param damage the damage the player can inflict.
     * @param health the life of the player.
     * @param money the money it has.
     * @param protection the protection level of the player.
     */
    public Player(String name, Room room, int damage, int health, int money, int protection) {
        super(damage, health, money);
        this.name = name;
        this.room = room;
        this.protection = protection;
    }

    public void lookAround() {
        room.inspect();
    }

    @Override
    public void attack(Character target) {
        target.setHealth(target.getHealth() - damage);
    }

    /**
     * Print the current damage, health and money.
     */
    public void printStatus() {
        System.out.println("Damage level: " + damage);
        System.out.println("Health level: " + health);
        System.out.println("Wealth: " + money);
    }
}
