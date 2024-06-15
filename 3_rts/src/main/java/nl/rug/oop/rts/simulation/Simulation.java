package nl.rug.oop.rts.simulation;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import lombok.*;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.simulation.events.Event;

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
    public void step() {
        Random random = new Random();
        List<Army> checkedArmies = new ArrayList<>();
        for (Node node : graph.getNodes()) {
            processArmyNode(node, random, checkedArmies);
        }
        for (Edge edge : graph.getEdges()) {
            processArmyEdge(edge, random, checkedArmies);
        }
    }

    private void processArmyNode(Node node, Random random, List<Army> checkedArmies) {
        List<Army> armiesToRemove = new ArrayList<>();
        for (Army army : node.getArmies()) {
            triggerEvent(node.getEvents(), army, random);
            int choice = random.nextInt(0, node.getEdges().size());
            armiesToRemove.add(army);
            graph.addArmyEdge(army, node.getEdges().get(choice));
        }
        for (Army army: armiesToRemove) {
            graph.removeArmyNode(army, node);
            checkedArmies.add(army);
        }
    }

    private void processArmyEdge(Edge edge, Random random, List<Army> checkedArmies) {
        List<Army> armiesToRemove = new ArrayList<>();
        for (Army army : edge.getArmies()) {
            if (checkedArmies.contains(army)) {
                continue;
            }
            triggerEvent(edge.getEvents(), army, random);
            if (army.getNode() == edge.getNode1()) {
                graph.addArmyNode(army, edge.getNode2());
            } else {
                graph.addArmyNode(army, edge.getNode1());
            }
            armiesToRemove.add(army);
        }

        for (Army army : armiesToRemove) {
            graph.removeArmyEdge(army, edge);
        }
    }

    private void triggerEvent(List<Event> events, Army army, Random random) {
        if (!events.isEmpty() && random.nextBoolean()) {
            events.get(random.nextInt(0, events.size())).occur(army);
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

    /**
     * Armies battle.
     */
    public void battle() {
        for (Node node : graph.getNodes()) {
            fight(node.getArmies(), node, null);
        }
        for (Edge edge : graph.getEdges()) {
            fight(edge.getArmies(), null, edge);
        }
    }

    private void fight(List<Army> armies, Node node, Edge edge) {
        List<Army> team1 = checkArmy(armies, 1);
        List<Army> team2 = checkArmy(armies, 2);
        if (team1.isEmpty() || team2.isEmpty()) {
            return;
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
                        if (node != null) {
                            graph.removeArmyNode(army1, node);
                        } else if (edge != null) {
                            graph.removeArmyEdge(army1, edge);
                        }
                    }
                    health2 -= damage1;
                }
                if (health2 <= 0) {
                    if (node != null) {
                        graph.removeArmyNode(army2, node);
                    } else if (edge != null) {
                        graph.removeArmyEdge(army2, edge);
                    }
                }
            }
        }
    }
}
