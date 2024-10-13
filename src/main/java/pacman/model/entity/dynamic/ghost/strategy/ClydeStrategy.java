package pacman.model.entity.dynamic.ghost.strategy;

import pacman.model.entity.dynamic.DynamicEntity;
import pacman.model.entity.dynamic.physics.Vector2D;

public class ClydeStrategy implements GhostStrategy {
    @Override
    public Vector2D getChaseTargetLocation(DynamicEntity pacman, Vector2D currentGhostLocation,
                                           Vector2D additionalLocation) {
        Vector2D pacmanLocation = pacman.getPosition();
        double distance = Vector2D.calculateEuclideanDistance(pacmanLocation, currentGhostLocation);

        if (distance > 8) {
            return pacmanLocation;
        } else {
            return getScatterTargetLocation();
        }
    }

    @Override
    public Vector2D getScatterTargetLocation() {
        return new Vector2D(0, 16 * 34);
    }
}
