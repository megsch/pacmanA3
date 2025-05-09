package pacman.model.entity.dynamic.ghost;

import pacman.model.entity.dynamic.DynamicEntity;
import pacman.model.entity.dynamic.ghost.observer.BlinkyPositionSubject;
import pacman.model.entity.dynamic.ghost.state.GhostState;
import pacman.model.entity.dynamic.ghost.strategy.GhostStrategy;
import pacman.model.entity.dynamic.player.observer.PlayerPositionObserver;
import pacman.model.entity.staticentity.collectable.Collectable;
import javafx.scene.image.Image;

import java.util.Map;

/**
 * Represents Ghost entity in Pac-Man Game
 */
public interface Ghost extends DynamicEntity, PlayerPositionObserver, BlinkyPositionSubject, Collectable {

    /***
     * Sets the speeds of the Ghost for each GhostMode
     * @param speeds speeds of the Ghost for each GhostMode
     */
    void setSpeeds(Map<GhostMode, Double> speeds);

//    /**
//     * Sets the mode of the Ghost used to calculate target position
//     *
//     * @param ghostMode mode of the Ghost
//     */
//    void setGhostMode(GhostMode ghostMode);
//
    /**
     * Gets the Ghost mode
     * @return the Ghost's mode
     */
    GhostMode getGhostMode();

    /**
     * Get the ghost's strategy
     * @return the ghost's strategy
     */
    GhostStrategy getGhostStrategy();

    /**
     * Set the ghost's strategy
     * @param ghostStrategy the ghost's strategy
     */
    void setGhostStrategy(GhostStrategy ghostStrategy);

    /**
     * Increments the Ghost's tick counter
     */
    void incrementTick();

    /**
     * Resets the Ghost's tick counter to 0
     */
    void resetTick();

    /**
     * Get the Ghost tick
     * @return the ghost's tick
     */
    int getTick();

    /**
     * Get the Ghost's normal image
     * @return normal image
     */
    Image getNormalImage();

    /**
     * Get the ghost's frightened Image
     * @return frightened image
     */
    Image getFrightenedImage();

    /**
     * Sets Ghost state
     */
    void setGhostState(GhostState state);

    /**
     * Changes the Ghost state
     */
    void changeGhostState();

    /**
     * Check if the tick count has reached mode length.
     * Will change to next state if it has.
     * @param modeLengths the modelengths from the config file
     */
    void checkTick(Map<GhostMode, Integer> modeLengths);
}
