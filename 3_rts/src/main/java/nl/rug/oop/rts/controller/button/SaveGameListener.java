package nl.rug.oop.rts.controller.button;

import lombok.*;
import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Action listener for Save Game button.
 */
@AllArgsConstructor
public class SaveGameListener implements ActionListener {
    private Graph graph;

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JSON file", "json"));
        int choice = chooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String path = file.getAbsolutePath();
            if (!file.getAbsolutePath().endsWith(".json")) {
                file = new File(file.getAbsolutePath() + ".json");
            }

        }
    }
}
