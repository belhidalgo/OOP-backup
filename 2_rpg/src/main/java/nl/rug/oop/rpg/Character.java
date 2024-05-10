package nl.rug.oop.rpg;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class Character {
    protected int damage;
    protected int health;
    protected int money;


    public Character(int damage, int health, int money) {
        this.damage = damage;
        this.health = health;
        this.money = money;
    }
}
