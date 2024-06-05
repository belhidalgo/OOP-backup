package nl.rug.oop.rpg.characters;

import lombok.*;
import nl.rug.oop.rpg.Attackable;
import nl.rug.oop.rpg.Room;
import nl.rug.oop.rpg.doors.Door;

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
    private boolean win;
    @Serial
    private static final long serialVersionUID = 100;

    /**
     * New Player.
     * @param name the name of the player.
     * @param room the room where the player is.
     * @param damage the strength the player can inflict.
     * @param health the life of the player.
     * @param money the money it has.
     * @param protection the protection level of the player.
     * @param key determines if the character has the key in its possession
     */
    public Player(String name, Room room, int damage, int health, int money, int protection, boolean key) {
        super(damage, health, money, key);
        this.name = name;
        this.room = room;
        this.protection = protection;
        this.win = false;
    }

    public void lookAround() {
        System.out.println("You see: ");
        room.inspect();
    }

    @Override
    public void attack(Character target) {
        target.setHealth(target.getHealth() - getStrength());
    }

    /**
     * Set the room the player is in to the room behind the chosen door.
     * @param door is the door we want to cross.
     */
    public void setRoomBehind(Door door) {
        if (getRoom().equals(door.getRoom1())) {
            setRoom(door.getRoom2());
        } else if (getRoom().equals(door.getRoom2())) {
            setRoom(door.getRoom1());
        }
        System.out.println("You go through the door");
    }

    /**
     * Print the current strength, health and money.
     */
    public void printStatus() {
        System.out.println("Strength level: " + getStrength());
        System.out.println("Health level: " + getHealth());
        System.out.println("Wealth: " + getMoney());
    }

    /**
     * Player has decided to look for company.
     * @param scanner scans the option of the user.
     */
    public void lookForCompany(Scanner scanner) {
        while (true) {
            System.out.print("You look if there’s someone here.");
            if (room.getNPCs().isEmpty()) {
                System.out.println("\nYou have no company.");
                return;
            }
            System.out.println(" You see:");
            room.listNPCs();
            System.out.println("Interact? (-1 : do nothing)");
            if (scanner.hasNextInt()) {
                int npc = scanner.nextInt();
                if (npc >= -1 && npc < room.getNPCs().size()) {
                    room.chooseNPC(npc, this);
                    if (npc == -1 || getHealth() <= 0) {
                        break;
                    }
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            } else {
                scanner.next();
                System.out.println("Invalid character. Please enter a number.");
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
            if (scanner.hasNextInt()) {
                int doorOption = scanner.nextInt();
                if (doorOption >= -1 && doorOption < room.getDoors().size()) {
                    room.chooseDoor(doorOption, this);
                    break;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            } else {
                scanner.next();
                System.out.println("Invalid character. Please enter a number.");
            }
        }
    }
}
