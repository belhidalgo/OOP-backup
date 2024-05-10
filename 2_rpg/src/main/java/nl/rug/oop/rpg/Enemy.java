package nl.rug.oop.rpg;

import java.util.Scanner;

public class Enemy extends NPC implements Attackable, Interactable {

    public Enemy(String description, int damage, int health, int money) {
        super(description, damage, health, money);
    }

    @Override
    public void attack(Character player) {
        player.setHealth(player.getHealth() - getDamage());
        if (player.getHealth() <= 0) {
            System.out.println("Game over!");
        }
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You angered an enemy and now it has started attacking you!");
        attack(player);
        System.out.println("What do you want to do?");
        System.out.println("    (0) Fight back!!!");
        System.out.println("    (1) Try to scooch away...");
        int option = scanner.nextInt();
        if (option == 0) {
            while (getHealth() > 0 && player.getHealth() > 0) {
                player.attack(this);
                if (this.getHealth() <= 0) {
                    System.out.println("Success! You killed the enemy!");
                    break;
                }
                attack(player);
            }
        } else if (option == 1) {
            System.out.println("Pheww... that was a close call");
        }
    }
}
