package pacman.model.entity.dynamic.ghost.strategy;

import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;

public class InkyStrategy implements GhostStrategy {

    private Vector2D blinkyLocation;

    @Override
    public Vector2D getChaseTargetLocation(Vector2D pacmanLocation, Vector2D currentGhostLocation, Direction pacmanDirection) {
        Vector2D pacmanShifted = switch (pacmanDirection) {
            case UP -> pacmanLocation.add(new Vector2D(0, -2 * 16));
            case DOWN -> pacmanLocation.add(new Vector2D(0, 2 * 16));
            case LEFT -> pacmanLocation.add(new Vector2D(-2 * 16, 0));
            case RIGHT -> pacmanLocation.add(new Vector2D(2 * 16, 0));
        };

        // Get Vector from blinky to pacman shifted
        Vector2D difference = new Vector2D(pacmanShifted.getX() - this.blinkyLocation.getX(),
                pacmanShifted.getY() - this.blinkyLocation.getY());

        return this.blinkyLocation.add(new Vector2D(difference.getX() * 2, difference.getY() * 2));
    }

    @Override
    public Vector2D getScatterTargetLocation() {
        return new Vector2D(448, 16*34);
    }

    @Override
    public void updateBlinkyLocation(Vector2D position) {
        this.blinkyLocation = position;
    }
}
