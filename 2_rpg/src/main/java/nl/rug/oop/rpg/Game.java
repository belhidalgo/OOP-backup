package nl.rug.oop.rpg;

import java.util.Scanner;

public class Game {

    public void play(Player player) {
        Scanner scanner = new Scanner(System.in);
        while (player.getHealth() > 0) {
            System.out.println("What do you want to do?");
            System.out.println("    (0) Look around");
            System.out.println("    (1) Look for a way out");
            System.out.println("    (2) Look for company");
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    System.out.print("You see: ");
                    player.lookAround();
                    break;
                case 1:
                    System.out.println("You look around for doors. You see:");
                    player.getRoom().listDoors();
                    System.out.println("Which door do you take? (-1 : stay here)");
                    int doorOption = scanner.nextInt();
                    player.getRoom().chooseDoor(doorOption, player);
                    break;
                case 2:
                    System.out.println("You look if there’s someone here.");
                    if (player.getRoom().getNPCs().isEmpty()) {
                        System.out.println("You have no company.");
                        break;
                    }
                    System.out.print(" You see:");
                    player.getRoom().listNPCs();
                    System.out.println("Interact? (-1 : do nothing)");
                    int npc = scanner.nextInt();
                    player.getRoom().chooseNPC(npc, player);

            }
        }
    }
}
