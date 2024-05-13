package nl.rug.oop.rpg.characters.NPCs;

import nl.rug.oop.rpg.Interactable;
import nl.rug.oop.rpg.characters.Player;

import java.util.Random;
import java.util.Scanner;

/**
 * Healer class (Subclass of NPC).
 */
public class Healer extends NPC implements Interactable {
    /**
     * New healer.
     * @param description the description of the healer.
     * @param damage the damage the healer can inflict.
     * @param health the life of the healer.
     * @param money the money it has.
     */
    public Healer(String description, int damage, int health, int money) {
        super(description, damage, health, money);
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * Player chose to offer the Healer a kind gesture.
     * @param player is the player who chose the option 0.
     */
    public void option0(Player player) {
        Random random = new Random();
        if (random.nextBoolean()) {
            System.out.println("The healer feels generous today and appreciates your kind gesture");
            player.setHealth(player.getHealth() + damage);
            System.out.println("Health level: "+ player.getHealth());
        } else {
            System.out.println("Tough luck, the healer doesn't appreciate your kind gesture");
        }
    }

    /**
     * Player chose to offer the healer money.
     * @param player is the player who chose the option 1.
     */
    public void option1(Player player) {
        System.out.println("How much money are you offering (money available: " + player.getMoney() + " coins)");
        Scanner scanner = new Scanner(System.in);
        int offer = scanner.nextInt();
        if (offer < (player.getMoney() * 0.2)) {
            System.out.println("Incredibly disappointing offer... don't assume I'm cheap.");
        } else if (offer <= 0 || offer > player.getMoney()) {
            System.out.println("Invalid choice. Try again another time!");
        } else {
            System.out.println("Tempting offer...");
            player.setHealth(player.getHealth() + damage);
            player.setMoney(player.getMoney() - offer);
            player.printStatus();
        }
    }

    /**
     * Player chose to offer the healer some of its damage level.
     * @param player is the player who chose the option 2.
     */
    public void option2(Player player) {
        System.out.println("How much of your damage level are you offering?");
        Scanner scanner = new Scanner(System.in);
        int damLevel = scanner.nextInt();
        if (damLevel < (player.getDamage() * 0.2)) {
            System.out.println("Incredibly disappointing offer...");
        } else if (damLevel <= 0 || damLevel > player.getDamage()) {
            System.out.println("Invalid choice. Try again another time!");
        } else {
            System.out.println("Tempting offer...");
            player.setHealth(player.getHealth() + damage);
            player.setDamage(player.getDamage() - damLevel);
            player.printStatus();
        }
    }

    @Override
    public void interact(Player player) {
        System.out.println("You found a healer! What offer can you make for them?");
        System.out.println("    (0) A kind gesture");
        System.out.println("    (1) Offer money");
        System.out.println("    (2) Offer some of you damage level");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                option0(player);
                break;
            case 1:
                option1(player);
                break;
            case 2:
                option2(player);
                break;
        }
    }
}


