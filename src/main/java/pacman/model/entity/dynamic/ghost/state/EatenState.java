package pacman.model.entity.dynamic.ghost.state;

import javafx.scene.image.Image;
import pacman.model.entity.dynamic.ghost.Ghost;
import pacman.model.entity.dynamic.ghost.GhostMode;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;

import java.util.Map;

public class EatenState implements GhostState {

    private Ghost ghost;

    public EatenState(Ghost ghost) {
        this.ghost = ghost;
    }

    @Override
    public Vector2D getTargetLocation(Vector2D pacmanLocation, Vector2D currentGhostLocation, Direction pacmanDirection) {
        // Ghost isn't moving, just return a random target
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
        this.ghost.setGhostState(new ScatterState(this.ghost));
    }

    @Override
    public GhostMode getGhostMode() {
        // There is no config mode for eaten, so just return scatter
        return GhostMode.SCATTER;
    }

    @Override
    public void checkGhostMode(Map<GhostMode, Integer> modeLengths) {
        // A second is about 29 frames
        int second = 29;
        if (ghost.getTick() == second) {
            changeState();
        }
    }

    @Override
    public boolean doUpdatePosition() {
        return false;
    }
}
