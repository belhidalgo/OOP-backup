package nl.rug.oop.rts.controller.button;

import lombok.AllArgsConstructor;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.saving.GraphStateLoad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Action listener for the Load Game button.
 */
@AllArgsConstructor
public class LoadGameListener implements ActionListener {
    private Graph graph;

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JSON file", "json"));
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            if (!selectedFile.getName().endsWith(".json")) {
                selectedFile = new File(selectedFile + ".json");
            }
            GraphStateLoad loadedGraph = new GraphStateLoad(graph);
            loadedGraph.loadSimulationState(selectedFile);
        }
    }
}
