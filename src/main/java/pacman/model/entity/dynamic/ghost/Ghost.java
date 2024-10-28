package pacman.model.entity.dynamic.ghost;

import pacman.model.entity.dynamic.DynamicEntity;
import pacman.model.entity.dynamic.ghost.observer.BlinkyPositionSubject;
import pacman.model.entity.dynamic.ghost.strategy.GhostStrategy;
import pacman.model.entity.dynamic.player.observer.PlayerPositionObserver;
import pacman.model.entity.staticentity.collectable.Collectable;

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

    /**
     * Sets the mode of the Ghost used to calculate target position
     *
     * @param ghostMode mode of the Ghost
     */
    void setGhostMode(GhostMode ghostMode);

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
}
