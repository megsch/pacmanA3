package pacman.model.entity.dynamic.ghost.state;

import javafx.scene.image.Image;
import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.ghost.GhostMode;
import pacman.model.entity.dynamic.ghost.strategy.GhostStrategy;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;

public class FrightenedState implements GhostState {

    private Ghost ghost;

    public FrightenedState(Ghost ghost) {
        this.ghost = ghost;
    }

    @Override
    public Vector2D getTargetLocation(Vector2D pacmanLocation, Vector2D currentGhostLocation, Direction pacmanDirection) {
        return ghost.getGhostStrategy().getFrightenedTargetLocation();
    }

    @Override
    public boolean isCollectable() {
        return true;
    }

    @Override
    public Image getImage() {
        return ghost.getFrightenedImage();
    }

    @Override
    public void changeState() {

    }

    @Override
    public GhostMode getGhostMode() {
        return GhostMode.FRIGHTENED;
    }
}
