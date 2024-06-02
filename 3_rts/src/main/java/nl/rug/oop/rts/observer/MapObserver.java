package nl.rug.oop.rts.observer;

import nl.rug.oop.rts.model.Graph;

/**
 * The view.
 */
public interface MapObserver {
    void update(Graph graph);
}
