package nl.rug.oop.rpg;

import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class NPC extends Character implements Inspectable, Interactable {
    protected String description;

    public NPC(String description, int damage, int health, int money) {
        super(damage, health, money);
        this.description = description;
    }
}