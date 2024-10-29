package pacman.model.entity.dynamic.ghost.state;

import javafx.scene.image.Image;
import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.ghost.GhostMode;
import pacman.model.entity.dynamic.ghost.strategy.GhostStrategy;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;

public class ScatterState implements GhostState {

    private Ghost ghost;

    public ScatterState(Ghost ghost) {
        this.ghost = ghost;
    }

    @Override
    public Vector2D getTargetLocation(Vector2D pacmanLocation, Vector2D currentGhostLocation, Direction pacmanDirection) {
        return this.ghost.getGhostStrategy().getScatterTargetLocation();
    }

    @Override
    public boolean isCollectable() {
        return false;
    }

    @Override
    public Image getImage() {
        return this.ghost.getNormalImage();
    }

    @Override
    public void changeState() {
        this.ghost.setGhostState(new ChaseState(this.ghost));
    }

    @Override
    public GhostMode getGhostMode() {
        return GhostMode.SCATTER;
    }
}
