package nl.rug.oop.rts.simulation;

import nl.rug.oop.rts.armies.Army;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

class MoveArmy extends JComponent {
    private Army army;

    MoveArmy(Army army, int x1, int y1, int x2, int y2) {
        this.army = army;
        int diffX = Math.abs(x1 - x2);
        int diffY = Math.abs(y1 - y2);
        double space = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
        AtomicInteger numJumps = new AtomicInteger((int) space / 5);

        Timer timer = new Timer(10, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (numJumps.get() > 0) {
                    numJumps.decrementAndGet();

                } else {
                    ((Timer)e.getSource()).stop();
                }
            }
        });

        timer.start();
    }

    public void paintComponent(Graphics g, double space) {

    }
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

