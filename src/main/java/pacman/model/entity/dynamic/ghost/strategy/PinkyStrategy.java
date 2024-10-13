package pacman.model.entity.dynamic.ghost.strategy;

import pacman.model.entity.dynamic.DynamicEntity;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;

public class PinkyStrategy implements GhostStrategy {

    @Override
    public Vector2D getChaseTargetLocation(DynamicEntity pacman, Vector2D currentGhostLocation,
                                    Vector2D additionalLocation) {
        Vector2D currentPacman = pacman.getPosition();
        Direction direction = pacman.getDirection();

        return switch (direction) {
            case UP -> currentPacman.add(new Vector2D(0, -4*16));
            case DOWN -> currentPacman.add(new Vector2D(0, 4*16));
            case LEFT -> currentPacman.add(new Vector2D(-4*16, 0));
            case RIGHT -> currentPacman.add(new Vector2D(4*16, 0));
        };
    }

    @Override
    public Vector2D getScatterTargetLocation() {
        return new Vector2D(0, 16 * 3);
    }
}
