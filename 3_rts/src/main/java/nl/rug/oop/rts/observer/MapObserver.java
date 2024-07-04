package nl.rug.oop.rts.observer;

/**
 * The view.
 */
public interface MapObserver {

    void update();

    default void observe(MapObservable observable) {
        observable.addObserver(this);
    }
}
