package pacman.model.entity.dynamic.ghost.state;

import javafx.scene.image.Image;
import pacman.model.entity.dynamic.ghost.GhostMode;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;

public interface GhostState {

    /**
     * Gets Ghost's target location
     * @param pacmanLocation pacman's location
     * @param currentGhostLocation Current Ghost's location
     * @param pacmanDirection Pacman's direction
     * @return The coordinates of the Ghost's target location
     */
    Vector2D getTargetLocation(Vector2D pacmanLocation, Vector2D currentGhostLocation, Direction pacmanDirection);

    /**
     * See if Ghost can be collected by pacman
     * @return True if the Ghost can be collected.
     */
    boolean isCollectable();

    /**
     * Get the image for the ghosts current state
     * @return The ghost image
     */
    Image getImage();

    /**
     * Change the Ghost's state
     */
    void changeState();

    /**
     * Get ghost mode this state represents
     * @return Ghost mode
     */
    GhostMode getGhostMode();
}
