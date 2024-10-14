package pacman.model.entity.dynamic.player.observer;

import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;

/***
 * Observer for PlayerPositionObserver
 */
public interface PlayerPositionObserver {

    /**
     * Updates observer with the new position of the player
     *
     * @param position the player's position
     */
    void updateLocation(Vector2D position);

    /**
     * Updates observer with the new direction of the player
     * @param direction the player's direction
     */
    void updateDirection(Direction direction);
}
