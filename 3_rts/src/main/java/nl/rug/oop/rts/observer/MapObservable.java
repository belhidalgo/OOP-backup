package nl.rug.oop.rts.observer;

import java.util.List;

/**
 * The Observable interface.
 */
public interface MapObservable {
    /**
     * Get the list of observers.
     * @return the list of observers.
     */
    List<MapObserver> getObservers();

    /**
     * Add an observer to the list of observers.
     * @param observer - the observer we want to add.
     */
    default void addObserver(MapObserver observer) {
        getObservers().add(observer);
    }

    /**
     * remove an observer from the list of observers.
     * @param observer - the observer we want to remove.
     */
    void removeObserver(MapObserver observer);

    /**
     * Notify all observers of the changes made and update them accordingly.
     */
    default void notifyObservers() {
        for (MapObserver observer : getObservers()) {
            observer.update();
        }
    }
}
