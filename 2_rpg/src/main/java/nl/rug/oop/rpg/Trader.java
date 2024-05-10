package nl.rug.oop.rpg;

import java.util.Scanner;

public class Trader extends NPC implements Interactable{
    public Trader(String description, int damage, int health, int money) {
        super(description, damage, health, money);
    }

    @Override
    public void inspect() {
        System.out.println(description);
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
                if (player.getMoney() >= 50) {
                    player.setProtection(player.getProtection() + 1);
                    player.setMoney(player.getMoney() - 50);
                    System.out.println("Protection level: " + player.getProtection());
                } else {
                    System.out.println("You're too broke for this.");
                }
                break;
            case 2:
                if (player.getMoney() >= 150) {
                    player.setDamage(player.getDamage() + 2);
                    player.setMoney(player.getMoney() - 150);
                    System.out.println("Damage level: " + player.getDamage());
                } else {
                    System.out.println("You're too broke for this.");
                }
                break;
            case 3:
                if (player.getMoney() >= 250) {
                    player.setHealth(player.getHealth() + 3);
                    player.setMoney(player.getMoney() - 250);
                    System.out.println("Health level: " + player.getHealth());
                } else {
                    System.out.println("You're too broke for this.");
                }
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}

