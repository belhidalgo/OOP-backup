package nl.rug.oop.rpg;

import lombok.Getter;
import lombok.Setter;
/**
 * The player class.
 */
@Getter
@Setter
public class Player extends Character implements Attackable {
    private String name;
    private Room room;
    private int protection;

    public Player (String name, Room room, int damage, int health, int money, int protection) {
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
}
