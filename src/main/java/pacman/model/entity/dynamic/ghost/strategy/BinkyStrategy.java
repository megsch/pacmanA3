package pacman.model.entity.dynamic.ghost.strategy;

import pacman.model.entity.dynamic.DynamicEntity;
import pacman.model.entity.dynamic.physics.Vector2D;

public class BinkyStrategy implements GhostStrategy {

    /**
     * Binky strategy, pacman's position
     * @param pacman
     * @param currentGhostLocation
     * @param additionalLocation
     * @return pacman's position
     */
    @Override
    public Vector2D getChaseTargetLocation(DynamicEntity pacman, Vector2D currentGhostLocation,
                                      Vector2D additionalLocation) {
        return pacman.getPosition();
    }

    /**
     * Binky strategy for bottom right corner
     * @return
     */
    @Override
    public Vector2D getScatterTargetLocation() {
        return new Vector2D(448, 16*3);
    }
}
