package nl.rug.oop.rts.simulation;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.model.Graph;
import lombok.*;
import nl.rug.oop.rts.model.Node;

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
        for (Node node : graph.getNodes()) {
            for (Army army : node.getArmies()) {
                int choice = random.nextInt(0, node.getEdges().size());

            }
        }
    }

    /*
    14. For now, all that happens in a single time-step is that every army will randomly pick one of the outgoing
     edges of the node it is currently located on and move to this edge.

15. At this point in the simulation step, every army is located at an edge. Later, we will add more stuff
on these edges, but for now, the armies immediately move to the other node of the edge.

16. Add a button that allows the user to simulate a single time step.

17. Add a few armies and try to simulate a number of time steps. Verify that the armies move as expected.

     */
}
