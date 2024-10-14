package pacman.model.entity.dynamic.ghost.strategy;

import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;

public class ClydeStrategy implements GhostStrategy {
    @Override
    public Vector2D getChaseTargetLocation(Vector2D pacmanLocation, Vector2D currentGhostLocation, Direction pacmanDirection) {
        double distance = Vector2D.calculateEuclideanDistance(pacmanLocation, currentGhostLocation);

        if (distance > 16*8) {
            return pacmanLocation;
        } else {
            return getScatterTargetLocation();
        }
    }

    @Override
    public Vector2D getScatterTargetLocation() {
        return new Vector2D(0, 16 * 34);
    }

    @Override
    public void updateBlinkyLocation(Vector2D position) {
        return;
    }
}
