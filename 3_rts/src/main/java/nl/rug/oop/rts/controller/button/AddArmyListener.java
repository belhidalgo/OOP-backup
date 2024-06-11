package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.armies.Faction;
import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.*;

/**
 * Action listener for the Add Army button.
 */
@AllArgsConstructor
public class AddArmyListener implements ActionListener {
    private Graph graph;

    /**
     * Execution of the Add Army button.
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /*JOptionPane options = new JOptionPane("Choose a faction");
        options.setOptions(Faction.values());*/

        Object selection = JOptionPane.showInputDialog(null, "Choose a faction", "Input",
                JOptionPane.INFORMATION_MESSAGE, null, Faction.values(), null);
        Army army = new Army((Faction) selection);
        graph.addArmyNode(army, graph.getCurrent());
    }
}
