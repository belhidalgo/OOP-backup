package nl.rug.oop.rts.saving;

import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.armies.Unit;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import lombok.*;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.simulation.events.Event;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class to save the current state of the game.
 */
@AllArgsConstructor
@NoArgsConstructor
public class GraphStateSave {
    private Graph graph;
    private static final String NEW_LINE = "\n";
    private static final String COLON = ",";
    private static final String COLON_NL = COLON + NEW_LINE;

    /**
     * Save the current game in a file.
     * @param filename - the file where we want to save our game.
     */
    public void saveSimulationState(String filename) {
        try {
            String json = graphToJson(graph);
            try (FileWriter fw = new FileWriter(filename)) {
                fw.write(json);
            }
        } catch (IOException e) {
            System.err.println("Error saving simulation state: " + e.getMessage());
        }
    }

    private String graphToJson(Graph graph) {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"Nodes\": [");
        if (!graph.getNodes().isEmpty()) {
            json.append(NEW_LINE);
        }
        List<Node> nodes = graph.getNodes();
        for (int i = 0; i < nodes.size(); i++) {
            json.append(nodeToJson(nodes.get(i)));
            if (i < nodes.size() - 1) {
                json.append(COLON);
            }
            json.append(NEW_LINE);
        }
        if (graph.getNodes().isEmpty()) {
            json.append("]");
        } else {
            json.append("  ],\n");
        }
        json.append("  \"Edges\": [");
        if (!graph.getEdges().isEmpty()) {
            json.append(NEW_LINE);
        }
        List<Edge> edges = graph.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            json.append(edgeToJson(edges.get(i)));
            if (i < edges.size() - 1) {
                json.append(COLON);
            }
            json.append(NEW_LINE);
        }
        if (graph.getEdges().isEmpty()) {
            json.append(NEW_LINE);
        } else {
            json.append("  ]\n");
        }
        json.append("}");
        return json.toString();
    }

    private String nodeToJson(Node node) {
        StringBuilder json = new StringBuilder();
        json.append("    {\n");
        json.append("      \"Id\": ").append(node.getId()).append(COLON_NL);
        json.append("      \"XCoordinate\": ").append(node.getX()).append(COLON_NL);
        json.append("      \"YCoordinate\": ").append(node.getY()).append(COLON_NL);
        boolean isSelected = (graph.getCurrent() == node);
        json.append("      \"isCurrent\": ").append(isSelected).append(COLON_NL);
        json.append("      \"Name\": \"").append(node.getName()).append("\",\n");
        return getString(json, node.getArmies(), node.getEvents());
    }

    private String getString(StringBuilder json, List<Army> armies, List<Event> events) {
        boolean isEmpty = armies.isEmpty();
        json.append("      \"Armies\": [");
        if (!isEmpty){
            json.append(NEW_LINE);
        }
        for (int i = 0; i < armies.size(); i++) {
            json.append(armyToJson(armies.get(i)));
            if (i < armies.size() - 1) {
                json.append(COLON);
            }
            json.append(NEW_LINE);
        }
        if (isEmpty) {
            json.append("],\n");
        } else {
            json.append("      ],\n");
        }
        isEmpty = events.isEmpty();
        json.append("      \"Events\": [");
        if (!isEmpty) {
            json.append(NEW_LINE);
        }
        for (int i = 0; i < events.size(); i++) {
            json.append(eventToJson(events.get(i)));
            if (i < events.size() - 1) {
                json.append(COLON);
            }
            json.append(NEW_LINE);
        }
        if (isEmpty) {
            json.append("]\n");
        } else {
            json.append("      ]\n");
        }
        json.append("    }");
        return json.toString();
    }

    private String edgeToJson(Edge edge) {
        StringBuilder json = new StringBuilder();
        json.append("    {\n");
        json.append("      \"Id\": ").append(edge.getId()).append(COLON_NL);
        json.append("      \"Name\": \"").append(edge.getName()).append("\",\n");
        boolean isSelected = (graph.getCurrentEdge() == edge);
        json.append("      \"Node1\": ").append(edge.getNode1().getId()).append(COLON_NL);
        json.append("      \"Node2\": ").append(edge.getNode2().getId()).append(COLON_NL);
        json.append("      \"IsSelected\": ").append(isSelected).append(COLON_NL);
        return getString(json, edge.getArmies(), edge.getEvents());
    }

    private String armyToJson(Army army) {
        StringBuilder json = new StringBuilder();
        json.append("        {\n");
        json.append("          \"Faction\": \"").append(army.getFaction()).append("\",\n");
        json.append("          \"Team\": ").append(army.getTeam()).append(COLON_NL);
        json.append("          \"Units\": [\n");
        List<Unit> units = army.getUnits();
        for (int i = 0; i < units.size(); i++) {
            json.append(unitToJson(units.get(i)));
            if (i < units.size() - 1) {
                json.append(COLON);
            }
            json.append(NEW_LINE);
        }
        json.append("          ]\n");
        json.append("        }");
        return json.toString();
    }

    private String unitToJson(Unit unit) {
        return "            {\n" +
                "              \"Name\": \"" + unit.getName() + "\",\n" +
                "              \"Strength\": " + unit.getDamage() + COLON_NL +
                "              \"Health\": " + unit.getHealth() + NEW_LINE +
                "            }";
    }

    private String eventToJson(Event event) {
        return "        {\n" +
                "          \"Name\": \"" + event.getPossibleEvents().name() + "\"\n" +
                "        }";
    }
}
