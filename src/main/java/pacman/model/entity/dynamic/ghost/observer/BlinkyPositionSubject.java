package pacman.model.entity.dynamic.ghost.observer;


public interface BlinkyPositionSubject {
    /**
     * Adds an observer to list of observers for subject
     *
     * @param observer observer for BlinkyPositionObserver
     */
    void registerBlinkyObserver(BlinkyPositionObserver observer);

    /**
     * Removes an observer from list of observers for subject
     *
     * @param observer observer for BlinkyPositionObserver
     */
    void removeBlinkyObserver(BlinkyPositionObserver observer);

    /**
     * Notifies observer of change in Blinky's position
     */
    void notifyBlinkyObserversLocation();
}
