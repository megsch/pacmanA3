package pacman.model.entity.dynamic.ghost.strategy;

import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;

public class BlinkyStrategy implements GhostStrategy {

    /**
     * Binky strategy, pacman's position
     *
     * @param pacmanLocation
     * @param currentGhostLocation
     * @param pacmanDirection
     * @return pacman's position
     */
    @Override
    public Vector2D getChaseTargetLocation(Vector2D pacmanLocation, Vector2D currentGhostLocation, Direction pacmanDirection) {
        return pacmanLocation;
    }

    /**
     * Binky strategy for bottom right corner
     * @return
     */
    @Override
    public Vector2D getScatterTargetLocation() {
        return new Vector2D(448, 16*3);
    }

    @Override
    public void updateBlinkyLocation(Vector2D position) {
        return;
    }
}
