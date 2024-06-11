package nl.rug.oop.rts.controller.button;

import lombok.*;
import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.armies.Faction;
import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for removeArmy button.
 */
@AllArgsConstructor
public class RemoveArmyListener implements ActionListener {
    private Graph graph;

    /**
     * Execution of the Remove Edge button.
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /*JOptionPane options = new JOptionPane("Choose a faction");
        options.setOptions(Faction.values());*/
        Faction[] factions = new Faction[graph.getCurrent().getArmies().size()];
        for (int i = 0; i < graph.getCurrent().getArmies().size(); i++) {
            factions[i] = graph.getCurrent().getArmies().get(i).getFaction();
        }
        Object selection = JOptionPane.showInputDialog(null, "Choose a faction", "Input",
                JOptionPane.INFORMATION_MESSAGE, null, factions, null);
        for (Army army : graph.getCurrent().getArmies()) {
            if (army.getFaction() == (Faction) selection) {
                graph.removeArmyNode(army, graph.getCurrent());
                break;
            }
        }
    }
}
