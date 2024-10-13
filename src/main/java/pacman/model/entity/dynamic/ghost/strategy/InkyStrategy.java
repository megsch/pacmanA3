package pacman.model.entity.dynamic.ghost.strategy;

import pacman.model.entity.dynamic.DynamicEntity;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;

public class InkyStrategy implements GhostStrategy {
    @Override
    public Vector2D getChaseTargetLocation(DynamicEntity pacman, Vector2D currentGhostLocation,
                                           Vector2D additionalLocation) {
        Vector2D pacmanLocation = pacman.getPosition();
        Direction pacmanDirection = pacman.getDirection();
        Vector2D pacmanShifted = switch (pacmanDirection) {
            case UP -> pacmanLocation.add(new Vector2D(0, -2 * 16));
            case DOWN -> pacmanLocation.add(new Vector2D(0, 2 * 16));
            case LEFT -> pacmanLocation.add(new Vector2D(-2 * 16, 0));
            case RIGHT -> pacmanLocation.add(new Vector2D(2 * 16, 0));
        };

        // Get Vector from blinky to pacman shifted
        Vector2D difference = new Vector2D(pacmanLocation.getX() - additionalLocation.getX(),
                pacmanLocation.getY() - additionalLocation.getY());

        return additionalLocation.add(new Vector2D(difference.getX() * 2, difference.getY() * 2));
    }

    @Override
    public Vector2D getScatterTargetLocation() {
        return new Vector2D(448, 16 * 3);
    }
}
