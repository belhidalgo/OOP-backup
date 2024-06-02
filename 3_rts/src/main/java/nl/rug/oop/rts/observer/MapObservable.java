package nl.rug.oop.rts.observer;

/**
 * The model.
 */
public interface MapObservable {
    void addObserver(MapObserver observer);

    void removeObserver(MapObserver observer);

    void notifyObservers();
}
