package nl.rug.oop.rpg;

/**
 * The Wizard NPC subclass.
 */
public class Wizard extends NPC implements Interactable {

    /**
     * New Wizard.
     * @param description the description of the wizard.
     * @param damage the damage the wizard can inflict.
     * @param health the life of the wizard.
     * @param money the money it has.
     */
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
