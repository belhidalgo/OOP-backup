package nl.rug.oop.rpg.characters.NPCs;

import nl.rug.oop.rpg.Interactable;
import nl.rug.oop.rpg.characters.Player;

import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

import method 

/**
 * The Trader NPC subclass.
 */

public class Trader extends NPC implements Interactable, Serializable {

    @Serial
    private static final long serialVersionUID = 700;

    /**
     * New Trader.
     * @param description the description of the trader.
     * @param strength the damage the trader can inflict.
     * @param health the life of the trader.
     * @param money the money it has.
     */
    public Trader(String description, int strength, int health, int money) {
        super(description, strength, health, money);
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * Player chose the pretty nice jeans.
     * @param player is the player who chose the option.
     */
    public void optionJeans(Player player) {
        if (player.getMoney() >= 50) {
            player.setProtection(player.getProtection() + 1);
            player.setMoney(player.getMoney() - 50);
            System.out.println("Protection level: " + player.getProtection());
        } else {
            System.out.println("You're too broke for this.");
        }
    }

    /**
     * Player chose the rusty sword.
     * @param player is the player who made the choice.
     */
    public void optionSword(Player player) {
        if (player.getMoney() >= 150) {
            player.setStrength(player.getStrength() + 2);
            player.setMoney(player.getMoney() - 150);
            System.out.println("+2 Strength");
            System.out.println("Strength level: " + player.getStrength());
        } else {
            System.out.println("You're too broke for this.");
        }
    }

    /**
     * Player chose the purple fluid.
     * @param player is the player who made the choice.
     */
    public void optionFluid(Player player) {
        if (player.getMoney() >= 250) {
            player.setHealth(player.getHealth() + 3);
            player.setMoney(player.getMoney() - 250);
            System.out.println("Health level: " + player.getHealth());
        } else {
            System.out.println("You're too broke for this.");
        }
    }

    @Override
    public void interact(Player player) {
        System.out.println("What can I offer you? (-1 : do nothing)");
        System.out.println("    (0) A hug ;) - free");
        System.out.println("    (1) Some pretty nice jeans - 50 pennies");
        System.out.println("    (2) Rusty sword - 150 pennies");
        System.out.println("    (3) Funky purple fluid in a flask - 250 pennies");
        Scanner scanner = new Scanner(System.in);
        int choice = scan(scanner, );
        switch (choice) {
            case -1:
                break;
            case 0:
                System.out.println("<3 <3 <3 <3 <3 <3");
                break;
            case 1:
                optionJeans(player);
                break;
            case 2:
                optionSword(player);
                break;
            case 3:
                optionFluid(player);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}

