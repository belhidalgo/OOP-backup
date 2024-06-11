package nl.rug.oop.rts.simulation;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import lombok.*;
import nl.rug.oop.rts.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
}
