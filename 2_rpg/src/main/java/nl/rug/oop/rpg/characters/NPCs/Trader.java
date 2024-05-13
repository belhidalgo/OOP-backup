package nl.rug.oop.rpg.characters.NPCs;

import nl.rug.oop.rpg.Interactable;
import nl.rug.oop.rpg.characters.Player;

import java.util.Scanner;

/**
 * The Trader NPC subclass.
 */

public class Trader extends NPC implements Interactable {
    /**
     * New Trader.
     * @param description the description of the trader.
     * @param damage the damage the trader can inflict.
     * @param health the life of the trader.
     * @param money the money it has.
     */
    public Trader(String description, int damage, int health, int money) {
        super(description, damage, health, money);
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * Player chose the pretty nice jeans.
     * @param player is the player who chose the option.
     */
    public void option1(Player player) {
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
    public void option2(Player player) {
        if (player.getMoney() >= 150) {
            player.setDamage(player.getDamage() + 2);
            player.setMoney(player.getMoney() - 150);
            System.out.println("Damage level: " + player.getDamage());
        } else {
            System.out.println("You're too broke for this.");
        }
    }

    /**
     * Player chose the purple fluid.
     * @param player is the player who made the choice.
     */
    public void option3(Player player) {
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
        int choice = scanner.nextInt();
        switch (choice) {
            case -1:
                break;
            case 0:
                System.out.println("<3 <3 <3 <3 <3 <3");
                break;
            case 1:
                option1(player);
                break;
            case 2:
                option2(player);
                break;
            case 3:
                option3(player);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}

