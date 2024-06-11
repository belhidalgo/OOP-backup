package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.*;
import nl.rug.oop.rts.simulation.Simulation;

/**
 * Action Listener for the Simulation Step button.
 */

@AllArgsConstructor
public class TimeStepListener implements ActionListener {
    private Graph graph;

    @Override
    public void actionPerformed(ActionEvent e) {
        Simulation simulation = new Simulation(graph);
        simulation.step();
        //Break
        //simulation.step2();
    }
}
