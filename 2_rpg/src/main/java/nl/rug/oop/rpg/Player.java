package nl.rug.oop.rpg;

import lombok.Getter;
import lombok.Setter;

/**
 * The player class.
 */

@Getter
@Setter

public class Player extends Character implements Attackable {
    /**
     * Implement a player.
     */
    private String name;
    private Room room;
    private int protection;

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
        target.setHealth(target.getHealth() - getDamage());
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
