package pacman.model.entity.dynamic.ghost.strategy;

import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;

public class PinkyStrategy implements GhostStrategy {

    @Override
    public Vector2D getChaseTargetLocation(Vector2D pacmanLocation, Vector2D currentGhostLocation, Direction pacmanDirection) {
        return switch (pacmanDirection) {
            case UP -> pacmanLocation.add(new Vector2D(0, -4*16));
            case DOWN -> pacmanLocation.add(new Vector2D(0, 4*16));
            case LEFT -> pacmanLocation.add(new Vector2D(-4*16, 0));
            case RIGHT -> pacmanLocation.add(new Vector2D(4*16, 0));
        };
    }

    @Override
    public Vector2D getScatterTargetLocation() {
        return new Vector2D(0, 16 * 3);
    }

    @Override
    public void updateBlinkyLocation(Vector2D position) {
        return;
    }
}
