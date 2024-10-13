package pacman.model.entity.dynamic.ghost.strategy;

import pacman.model.entity.dynamic.DynamicEntity;
import pacman.model.entity.dynamic.physics.Vector2D;

public interface GhostStrategy {

    /**
     * Calculates the target location for the CHASE state
     * @return the target location
     */
    Vector2D getChaseTargetLocation(DynamicEntity pacman, Vector2D currentGhostLocation,
                               Vector2D additionalLocation);

    Vector2D getScatterTargetLocation();
}
