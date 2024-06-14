package nl.rug.oop.rts.simulation;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.awt.*;

class MoveArmy extends JComponent {
    /*private Army army;

    public MoveArmy(Army army, Edge edge, Node node) {
        this.army = army;
        int diffX = Math.abs(x2 - x1);
        int diffY = Math.abs(y2 - y1);
        Thread animationThread = new Thread(new Runnable() {
            public void run() {
                while (diffX != 0 || diffY != 0) {
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        animationThread.start();
    }

    public void paintComponent(Graphics g) {
        Graphics2D gg = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        int trainW = 100;
        int trainH = 10;
        int trainSpeed = 3;

        int x = lastX + trainSpeed;

        if (x > w + trainW) {
            x = -trainW;
        }

        gg.setColor(Color.BLACK);
        gg.fillRect(x, h/2 + trainH, trainW, trainH);

        lastX = x;
    }*/

}

