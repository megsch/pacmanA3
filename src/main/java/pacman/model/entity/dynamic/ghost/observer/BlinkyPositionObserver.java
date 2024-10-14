package pacman.model.entity.dynamic.ghost.observer;

import pacman.model.entity.dynamic.physics.Vector2D;

public interface BlinkyPositionObserver {

    /**
     * Updates observer with the new position of Blinky
     *
     * @param position the Blinky's position
     */
    void updateBlinkyLocation(Vector2D position);
}
