package pacman.model.entity.dynamic.ghost.strategy;

import pacman.model.entity.dynamic.ghost.observer.BlinkyPositionObserver;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;

public interface GhostStrategy extends BlinkyPositionObserver {

    /**
     * Calculates the target location for the CHASE state
     * @return the target location
     */
    Vector2D getChaseTargetLocation(Vector2D pacmanLocation, Vector2D currentGhostLocation, Direction pacmanDirection);

    Vector2D getScatterTargetLocation();
}
