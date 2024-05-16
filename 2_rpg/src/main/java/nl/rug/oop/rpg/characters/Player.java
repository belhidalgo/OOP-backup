package nl.rug.oop.rpg.characters;

import lombok.*;
import nl.rug.oop.rpg.Attackable;
import nl.rug.oop.rpg.Room;

import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

/**
 * The player class.
 */

@Getter
@Setter

public class Player extends Character implements Attackable, Serializable {
    /**
     * Implement a player.
     */
    private String name;
    private Room room;
    private int protection;
    @Serial
    private static final long serialVersionUID = 100;

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
        target.setHealth(target.getHealth() - strength);
    }

    /**
     * Print the current damage, health and money.
     */
    public void printStatus() {
        System.out.println("Strength level: " + strength);
        System.out.println("Health level: " + health);
        System.out.println("Wealth: " + money);
    }

    /**
     * Player has decided to look for company.
     * @param scanner scans the option of the user.
     */
    public void lookForCompany(Scanner scanner) {
        while (true) {
            System.out.println("You look if there’s someone here.");
            if (room.getNPCs().isEmpty()) {
                System.out.println("You have no company.");
                return;
            }
            System.out.print(" You see:");
            room.listNPCs();
            System.out.println("Interact? (-1 : do nothing)");
            int npc;
            while (true) {
                if (scanner.hasNextInt()) {
                    npc = scanner.nextInt();
                    if (npc >= -1 && npc < room.getNPCs().size()) {
                        room.chooseNPC(npc, this);
                        break;
                    }
                    System.out.println("Invalid choice. Try again.");
                } else {
                    System.out.println("Invalid character. Please enter a number.");
                }
            }
        }

    }

    /**
     * Player searches for a way out - descriptions of the doors in current room
     * are listed and player chooses which one to go through.
     * @param scanner - scans the chosen option of the user.
     */
    public void wayOut(Scanner scanner) {
        while (true) {
            System.out.println("You look around for doors. You see:");
            room.listDoors();
            System.out.println("Which door do you take? (-1 : stay here)");
            int doorOption;
            while (true) {
                if (scanner.hasNextInt()) {
                    doorOption = scanner.nextInt();
                    if (doorOption >= -1 && doorOption < room.getDoors().size()) {
                        room.chooseDoor(doorOption, this);
                        break;
                    }
                    System.out.println("Invalid choice. Try again.");
                } else {
                    System.out.println("Invalid character. Please enter a number.");
                }
            }
        }
    }
}
