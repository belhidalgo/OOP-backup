package nl.rug.oop.rpg;

public class Wizard extends NPC implements Interactable {

    public Wizard(String description, int damage, int health, int money) {
        super(description, damage, health, money);
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    @Override
    public void interact(Player player) {
        System.out.println("The wizard decided to be generous and increased your powers.");
        player.setDamage(player.getDamage() + damage);
        System.out.println("Damage level: " + player.getDamage());
        System.out.println("Health level: " + player.getHealth());
    }
}
