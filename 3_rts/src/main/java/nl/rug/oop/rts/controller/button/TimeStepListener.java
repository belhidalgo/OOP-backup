package nl.rug.oop.rts.controller.button;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lombok.*;
import nl.rug.oop.rts.simulation.Simulation;

import javax.swing.*;

/**
 * Action Listener for the Simulation Step button.
 */
@AllArgsConstructor
public class TimeStepListener implements ActionListener {
    private Graph graph;

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(()-> {
            Simulation simulation = new Simulation(graph);
            for (int i = 0; i < 2; i++) {
                simulation.battle();
                sleepThread();
                simulation.step();
                sleepThread();
            }
            simulation.battle();
        }).start();
    }

    private void sleepThread() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
