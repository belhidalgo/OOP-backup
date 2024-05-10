package nl.rug.oop.rpg;

import java.util.Random;
import java.util.Scanner;

public class Healer extends NPC implements Interactable {

    public Healer(String description, int damage, int health, int money) {
        super(description, damage, health, money);
    }

    @Override
    public void inspect() {
        System.out.println(description);
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
                Random random = new Random();
                if (random.nextBoolean()) {
                    System.out.println("The healer feels generous today and appreciates your kind gesture");
                    player.setHealth(player.getHealth() + damage);
                    System.out.println("Health level: "+ player.getHealth());
                } else {
                    System.out.println("Tough luck, the healer doesn't appreciate your kind gesture");
                }
            case 1:
                System.out.println("How much money are you offering (money available: "+ player.getMoney()+" coins)");
                int offer = scanner.nextInt();
                if (offer < (player.getMoney() * 0.2)) {
                    System.out.println("Incredibly disappointing offer... don't assume I'm cheap.");
                } else if (offer <= 0 || offer > player.getMoney()) {
                    System.out.println("Invalid choice. Try again another time!");
                } else {
                    System.out.println("Tempting offer...");
                    player.setHealth(player.getHealth() + damage);
                    player.setMoney(player.getMoney() - offer);
                    System.out.println("Damage level: " + player.getDamage());
                    System.out.println("Health level: " + player.getHealth());
                    System.out.println("Wealth: " + player.getMoney());
                }
            case 2:
                System.out.println("How much of your damage level are you offering?");
                int damLevel = scanner.nextInt();
                if (damLevel < (player.getDamage() * 0.2)) {
                    System.out.println("Incredibly disappointing offer...");
                } else if (damLevel <= 0 || damLevel > player.getDamage()) {
                    System.out.println("Invalid choice. Try again another time!");
                } else {
                    System.out.println("Tempting offer...");
                    player.setHealth(player.getHealth() + damage);
                    player.setDamage(player.getDamage() - damLevel);
                    System.out.println("Damage level: " + player.getDamage());
                    System.out.println("Health level: " + player.getHealth());
                    System.out.println("Wealth: " + player.getMoney());
                }
        }
    }
}
