package nl.rug.oop.rts.simulation;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import lombok.*;
import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.util.*;

/**
 * Class responsible for the simulations.
 */
@Getter
@Setter
@AllArgsConstructor
public class Simulation {
    private Graph graph;

    /**
     * Armies step from node over to the edges.
     */
    //SwingUtilities.invokeAndWait()
    public void step() {
        Random random = new Random();
        List<Army> checkedArmies = new ArrayList<>();
        for (Node node : graph.getNodes()) {
            List<Army> armiesToRemove = new ArrayList<>();
            for (Army army : node.getArmies()) {
                int choice = random.nextInt(0, node.getEdges().size());
                armiesToRemove.add(army);
                graph.addArmyEdge(army, node.getEdges().get(choice));
            }
            for (Army army : armiesToRemove) {
                graph.removeArmyNode(army, node);
                checkedArmies.add(army);
            }
        }
        for (Edge edge : graph.getEdges()) {
            List<Army> armiesToRemove = new ArrayList<>();
            for (Army army : edge.getArmies()) {
                if (checkedArmies.contains(army)) {
                    continue;
                }
                armiesToRemove.add(army);
                if (army.getNode() == edge.getNode1()) {
                    graph.addArmyNode(army, edge.getNode2());
                } else {
                    graph.addArmyNode(army, edge.getNode1());
                }
            }
            for (Army army : armiesToRemove) {
                graph.removeArmyEdge(army, edge);
            }
        }
    }

    private List<Army> checkArmy(List<Army> armies, int chosenTeam) {
        ArrayList<Army> team = new ArrayList<>();
        for (Army army : armies) {
            if (army.getTeam() == chosenTeam) {
                team.add(army);
            }
        }
        return team;
    }

    /*private void fight(Graph graph, boolean isNode) {
        if (isNode) {
            List<Edge> iteration = graph.getEdges();
        } else {
            List<Node> iteration = graph.getNodes();
        }
        for ()
    }*/

    public void battle() {
        for (Node node : graph.getNodes()) {
            List<Army> team1 = checkArmy(node.getArmies(), 1);
            List<Army> team2 = checkArmy(node.getArmies(), 2);
            if (team1.isEmpty() || team2.isEmpty()) {
                continue;
            }
            for (Army army1 : team1) {
                int health1 = army1.getNumUnits() * army1.getUnits().get(0).getHealth();
                int damage1 = army1.getNumUnits() * army1.getUnits().get(0).getDamage();
                for (Army army2 : team2) {
                    int health2 = army2.getNumUnits() * army2.getUnits().get(0).getHealth();
                    int damage2 = army2.getNumUnits() * army2.getUnits().get(0).getDamage();
                    while (health1 > 0 && health2 > 0) {
                        health1 -= damage2;
                        if (health1 <= 0) {
                            graph.removeArmyNode(army1, node);
                        }
                        health2 -= damage1;
                    }
                    if (health2 <= 0) {
                        graph.removeArmyNode(army2, node);
                    }
                }
            }
        }
        for (Edge edge : graph.getEdges()) {
            List<Army> team1 = checkArmy(edge.getArmies(), 1);
            List<Army> team2 = checkArmy(edge.getArmies(), 2);
            if (team1.isEmpty() || team2.isEmpty()) {
                continue;
            }
            for (Army army1 : team1) {
                int health1 = army1.getNumUnits() * army1.getUnits().get(0).getHealth();
                int damage1 = army1.getNumUnits() * army1.getUnits().get(0).getDamage();
                for (Army army2 : team2) {
                    int health2 = army2.getNumUnits() * army2.getUnits().get(0).getHealth();
                    int damage2 = army2.getNumUnits() * army2.getUnits().get(0).getDamage();
                    while (health1 > 0 && health2 > 0) {
                        health1 -= damage2;
                        if (health1 <= 0) {
                            graph.removeArmyEdge(army1, edge);
                        }
                        health2 -= damage1;
                    }
                    if (health2 <= 0) {
                        graph.removeArmyEdge(army2, edge);
                    }
                }
            }
        }
    }
}
