package nl.rug.oop.rts.saving;

import lombok.*;
import nl.rug.oop.rts.armies.Army;
import nl.rug.oop.rts.armies.Faction;
import nl.rug.oop.rts.armies.Unit;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import nl.rug.oop.rts.simulation.events.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Load a saved game.
 */
@NoArgsConstructor
public class GraphStateLoad {
    private Graph graph;
    private Node currentNode;
    private Edge currentEdge;
    private static final String MARK = "\"";

    /**
     * Constructor.
     * @param graph - the graph we want to build on.
     */
    public GraphStateLoad(Graph graph) {
        this.graph = graph;
        this.currentNode = null;
        this.currentEdge = null;
    }

    /**
     * Load the game from a file.
     * @param file - the file from which we want to load the game.
     */
    public void loadSimulationState(File file) {
        try {
            try (FileReader fr = new FileReader(file)) {
                BufferedReader br = new BufferedReader(fr);
                cleanGraph();
                jsonToGraph(br);
            }
        } catch (IOException e) {
            System.err.println("Error loading simulation state: " + e.getMessage());
        }
    }

    private void cleanGraph() {
        List<Node> nodes = new ArrayList<>(graph.getNodes());
        for (Node node : nodes) {
            graph.removeNode(node);
        }
    }

    private void jsonToGraph(BufferedReader br) throws IOException {
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            json.append(line).append("\n");
        }
        String jsonString = json.toString().stripIndent();
        String nodesString = getArray(jsonString, "Nodes");
        String edgesString = getArray(jsonString, "Edges");

        List<Node> nodes = jsonToNodes(nodesString);
        for (Node node : nodes) {
            graph.addNode(node);
            if (currentNode != null && currentNode == node) {
                graph.setCurrent(node);
            }
        }

        List<Edge> edges = jsonToEdges(edgesString);
        for (Edge edge : edges) {
            graph.addEdge(edge);
            if (currentEdge != null && currentEdge == edge) {
                graph.setCurrentEdge(edge);
            }
        }
    }

    private List<Node> jsonToNodes(String json) {
        List<Node> nodes = new ArrayList<>();
        String[] nodesString = json.split("\n},\n\\{");
        for (String node : nodesString) {
            if (node.isEmpty()){
                continue;
            }
            json = json.replace("{", "").replace("}", "");
            nodes.add(jsonToNode(node));
        }
        return nodes;
    }

    private Node jsonToNode(String json) {
        int id = Integer.parseInt(getValue(json, "Id"));
        int x = Integer.parseInt(getValue(json, "XCoordinate"));
        int y = Integer.parseInt(getValue(json, "YCoordinate"));
        boolean isSelected = Boolean.parseBoolean(getValue(json, "isCurrent"));
        String name = getValue(json, "Name").replace(MARK, "");

        Node node = new Node(id, x, y, name);
        String eventsString = getArray(json, "Events");
        String armiesString = getArray(json, "Armies");

        List<Event> events = jsonToEvents(eventsString);
        List<Army> armies = jsonToArmies(armiesString);

        for (Event event : events) {
            node.getEvents().add(event);
        }
        for (Army army : armies) {
            node.getArmies().add(army);
        }
        if (isSelected) {
            currentNode = node;
        }
        return node;
    }

    private List<Edge> jsonToEdges(String json) {
        List<Edge> edges = new ArrayList<>();
        String[] edgeArray = json.split("\n},\n\\{");
        for (String edgeString : edgeArray) {
            if (edgeString.isEmpty()) {
                continue;
            }
            json = json.replace("{", "").replace("}", "");
            edges.add(jsonToEdge(edgeString));
        }
        return edges;
    }

    private Edge jsonToEdge(String json) {
        int id = Integer.parseInt(getValue(json, "Id"));
        String name = getValue(json, "Name").replace(MARK, "");
        Node node1 = searchNode(Integer.parseInt(getValue(json, "Node1")));
        Node node2 = searchNode(Integer.parseInt(getValue(json, "Node2")));
        boolean isSelected = Boolean.parseBoolean(getValue(json, "isSelected"));
        Edge edge = new Edge(id, name, node1, node2);
        String eventsString = getArray(json, "Events");
        String armiesString = getArray(json, "Armies");

        List<Army> armies = jsonToArmies(armiesString);
        List<Event> events = jsonToEvents(eventsString);

        for (Army army : armies) {
            edge.getArmies().add(army);
        }
        for (Event event : events) {
            edge.getEvents().add(event);
        }
        if (isSelected) {
            currentEdge = edge;
        }
        return edge;
    }

    private Node searchNode(int id) {
        for (Node node : graph.getNodes()) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null;
    }

    private List<Army> jsonToArmies(String json) {
        List<Army> armies = new ArrayList<>();
        if (json.isEmpty()) {
            return armies;
        }
        String[] armyArray = json.split("\n},\n\\{");
        for (String army : armyArray) {
            json = json.replace("\n{", "").replace("\n}", "");
            armies.add(jsonToArmy(army));
        }
        return armies;
    }

    private Army jsonToArmy(String json) {
        String factionString = getValue(json, "Faction").replace(MARK, "");
        Faction faction = Faction.valueOf(factionString);
        int team = Integer.parseInt(getValue(json, "Team"));
        String unitsString = getArray(json, "Units").stripIndent();
        List<Unit> units = jsonToUnits(unitsString);
        return new Army(faction, units.size(), units, team);
    }

    private List<Unit> jsonToUnits(String json) {
        List<Unit> units = new ArrayList<>();
        if (json.isEmpty()) {
            return units;
        }
        String[] unitArray = json.split("\n},\n\\{");
        for (String unit : unitArray) {
            if (unit.isEmpty()) {
                continue;
            }
            json = json.replace("{", "").replace("}", "");
            Unit newUnit = jsonToUnit(unit);
            units.add(newUnit);
        }
        return units;
    }

    private Unit jsonToUnit(String json) {
        String name = getValue(json, "Name").replace(MARK, "");
        int health = Integer.parseInt(getValue(json, "Health"));
        int damage = Integer.parseInt(getValue(json, "Strength"));
        return new Unit(damage, health, name);
    }

    private List<Event> jsonToEvents(String json) {
        List<Event> events = new ArrayList<>();
        if (json.isEmpty()) {
            return events;
        }
        String[] eventArray = json.split("\n},\n\\{");
        for (String event : eventArray) {
            if (event.isEmpty()) {
                continue;
            }
            json = json.replace("{", "").replace("}", "");
            Event newEvent = jsonToEvent(event);
            events.add(newEvent);
        }
        return events;
    }

    private Event jsonToEvent(String json) {
        String name = getValue(json, "Name").replace(MARK, "");
        switch (name) {
            case "DISASTER" -> {
                return new NaturalDisaster(graph);
            }
            case "REBELLION" -> {
                return new Rebellion(graph);
            }
            case "SUPERPOWER" -> {
                return new SuperPower(graph);
            }
            case "REINFORCEMENT" -> {
                return new Reinforcement(graph);
            }
            default -> {
                return null;
            }
        }
    }

    private String getArray(String json, String key) {
        String search = MARK + key + "\": [";
        int startIdx = json.indexOf(search) + search.length();
        int endIdx = json.indexOf(']', startIdx);
        if (json.charAt(startIdx) != ']') {
            endIdx = json.indexOf("\n  ]", startIdx);
        }
        return json.substring(startIdx, endIdx).stripIndent();
    }

    private String getValue(String json, String key) {
        String search = MARK + key + "\": ";
        int startIdx = json.indexOf(search) + search.length();
        int endIdx = json.indexOf(',', startIdx);
        if (endIdx == -1) {
            endIdx = json.indexOf('}', startIdx);
        }
        if (endIdx == -1) {
            endIdx = json.length();
        }
        String value = json.substring(startIdx, endIdx).stripIndent();
        while (value.charAt(value.length() - 1) == '\n') {
            value = value.substring(0, value.length() - 1);
        }
        return value;
    }
}
