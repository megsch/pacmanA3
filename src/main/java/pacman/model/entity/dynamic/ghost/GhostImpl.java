package pacman.model.entity.dynamic.ghost;

import javafx.scene.image.Image;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.ghost.observer.BlinkyPositionObserver;
import pacman.model.entity.dynamic.ghost.state.GhostState;
import pacman.model.entity.dynamic.ghost.state.ScatterState;
import pacman.model.entity.dynamic.ghost.strategy.BlinkyStrategy;
import pacman.model.entity.dynamic.ghost.strategy.GhostStrategy;
import pacman.model.entity.dynamic.ghost.strategy.InkyStrategy;
import pacman.model.entity.dynamic.physics.*;
import pacman.model.level.Level;
import pacman.model.maze.Maze;

import java.util.*;

/**
 * Concrete implementation of Ghost entity in Pac-Man Game
 */
public class GhostImpl implements Ghost {

    private static final int minimumDirectionCount = 8;
    private final Layer layer = Layer.FOREGROUND;
    private final Image image;
    private final Image frightenedImage;
    private final BoundingBox boundingBox;
    private final Vector2D startingPosition;
    private GhostStrategy ghostStrategy;
    private KinematicState kinematicState;
    private GhostState ghostState;
    private Vector2D targetLocation;
    private Vector2D playerPosition;
    private Direction playerDirection;
    private Direction currentDirection;
    private Set<Direction> possibleDirections;
    private Map<GhostMode, Double> speeds;
    private int currentDirectionCount = 0;
    private List<BlinkyPositionObserver> blinkyPositionObservers;
    private int tickCount;
    private int points;

    public GhostImpl(Image image, Image frightenedImage, BoundingBox boundingBox, KinematicState kinematicState,
                     GhostStrategy ghostStrategy) {
        this.image = image;
        this.frightenedImage = frightenedImage;
        this.boundingBox = boundingBox;
        this.kinematicState = kinematicState;
        this.startingPosition = kinematicState.getPosition();
        this.ghostState = new ScatterState(this);
        this.possibleDirections = new HashSet<>();
        this.ghostStrategy = ghostStrategy;
        this.targetLocation = getTargetLocation();
        this.currentDirection = null;
        this.blinkyPositionObservers = new ArrayList<>();
        this.playerPosition = new Vector2D(224, 8*34);
        this.playerDirection = Direction.LEFT;
        this.tickCount = 0;
        this.points = 200;
    }

    @Override
    public void setSpeeds(Map<GhostMode, Double> speeds) {
        this.speeds = speeds;
    }

    @Override
    public GhostMode getGhostMode() {
        return this.ghostState.getGhostMode();
    }

    @Override
    public Image getImage() {
        return this.ghostState.getImage();
//        if (ghostMode == GhostMode.FRIGHTENED) {
//            return frightenedImage;
//        }
//        return image;
    }

    @Override
    public void update() {
        this.updateDirection();
        this.kinematicState.update();
        this.boundingBox.setTopLeft(this.kinematicState.getPosition());
        notifyBlinkyObserversLocation();
    }

    private void updateDirection() {
        // Ghosts update their target location when they reach an intersection
        if (Maze.isAtIntersection(this.possibleDirections)) {
            this.targetLocation = getTargetLocation();
        }

        Direction newDirection = selectDirection(possibleDirections);

        // Ghosts have to continue in a direction for a minimum time before changing direction
        if (this.currentDirection != newDirection) {
            this.currentDirectionCount = 0;
        }
        this.currentDirection = newDirection;

        switch (currentDirection) {
            case LEFT -> this.kinematicState.left();
            case RIGHT -> this.kinematicState.right();
            case UP -> this.kinematicState.up();
            case DOWN -> this.kinematicState.down();
        }
    }

    private Vector2D getTargetLocation() {
        return this.ghostState.getTargetLocation(this.playerPosition,
                    this.kinematicState.getPosition(), this.playerDirection);
//        return switch (this.ghostMode) {
//            case CHASE -> this.ghostStrategy.getChaseTargetLocation(this.playerPosition,
//                    this.kinematicState.getPosition(), this.playerDirection);
//            case SCATTER -> this.ghostStrategy.getScatterTargetLocation();
//            case FRIGHTENED -> this.ghostStrategy.getFrightenedTargetLocation();
//        };
    }

    private Direction selectDirection(Set<Direction> possibleDirections) {
        if (possibleDirections.isEmpty()) {
            return currentDirection;
        }

        // ghosts have to continue in a direction for a minimum time before changing direction
        if (currentDirection != null && currentDirectionCount < minimumDirectionCount) {
            currentDirectionCount++;
            return currentDirection;
        }

        Map<Direction, Double> distances = new HashMap<>();

        for (Direction direction : possibleDirections) {
            // ghosts never choose to reverse travel
            if (currentDirection == null || direction != currentDirection.opposite()) {
                distances.put(direction, Vector2D.calculateEuclideanDistance(this.kinematicState.getPotentialPosition(direction), this.targetLocation));
            }
        }

        // only go the opposite way if trapped
        if (distances.isEmpty()) {
            return currentDirection.opposite();
        }

        // select the direction that will reach the target location fastest
        return Collections.min(distances.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

//    @Override
//    public void setGhostMode(GhostMode ghostMode) {
//        this.ghostMode = ghostMode;
//        if (this.speeds != null) {
//            this.kinematicState.setSpeed(speeds.get(ghostMode));
//        }
//        // ensure direction is switched
//        this.currentDirectionCount = minimumDirectionCount;
//        resetTick();
//        System.out.println(ghostMode);
//    }

    @Override
    public boolean collidesWith(Renderable renderable) {
        return boundingBox.collidesWith(kinematicState.getSpeed(), kinematicState.getDirection(), renderable.getBoundingBox());
    }

    @Override
    public void collideWith(Level level, Renderable renderable) {
        if (level.isPlayer(renderable) && !isCollectable()) {
            level.handleLoseLife();
        }
    }

    @Override
    public void updateLocation(Vector2D playerPosition) {
        this.playerPosition = playerPosition;
    }

    @Override
    public void updateDirection(Direction direction) {
        this.playerDirection = direction;
    }

    @Override
    public void registerBlinkyObserver(BlinkyPositionObserver observer) {
        if (this.ghostStrategy instanceof BlinkyStrategy
        && observer instanceof InkyStrategy) {
            this.blinkyPositionObservers.add(observer);
        }
    }

    @Override
    public void removeBlinkyObserver(BlinkyPositionObserver observer) {
        this.blinkyPositionObservers.remove(observer);
    }

    @Override
    public void notifyBlinkyObserversLocation() {
        for (BlinkyPositionObserver observer : this.blinkyPositionObservers) {
            observer.updateBlinkyLocation(this.kinematicState.getPosition());
        }
    }

    @Override
    public Vector2D getPositionBeforeLastUpdate() {
        return this.kinematicState.getPreviousPosition();
    }

    @Override
    public double getHeight() {
        return this.boundingBox.getHeight();
    }

    @Override
    public double getWidth() {
        return this.boundingBox.getWidth();
    }

    @Override
    public Vector2D getPosition() {
        return this.kinematicState.getPosition();
    }

    @Override
    public void setPosition(Vector2D position) {
        this.kinematicState.setPosition(position);
    }

    @Override
    public Layer getLayer() {
        return this.layer;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    @Override
    public void reset() {
        // return ghost to starting position
        this.kinematicState = new KinematicStateImpl.KinematicStateBuilder()
                .setPosition(startingPosition)
                .build();
        this.boundingBox.setTopLeft(startingPosition);
//        this.setGhostMode(GhostMode.SCATTER);
        this.setGhostState(new ScatterState(this));
        this.currentDirectionCount = minimumDirectionCount;
    }

    @Override
    public void setPossibleDirections(Set<Direction> possibleDirections) {
        this.possibleDirections = possibleDirections;
    }

    @Override
    public Direction getDirection() {
        return this.kinematicState.getDirection();
    }

    @Override
    public Vector2D getCenter() {
        return new Vector2D(boundingBox.getMiddleX(), boundingBox.getMiddleY());
    }

    @Override
    public GhostStrategy getGhostStrategy() {
        return this.ghostStrategy;
    }

    @Override
    public void setGhostStrategy(GhostStrategy ghostStrategy) {
        this.ghostStrategy = ghostStrategy;
    }

    @Override
    public void incrementTick() {
        this.tickCount++;
    }

    @Override
    public void resetTick() {
        this.tickCount = 0;
    }

    @Override
    public int getTick() {
        return this.tickCount;
    }

    @Override
    public Image getNormalImage() {
        return this.image;
    }

    @Override
    public Image getFrightenedImage() {
        return this.frightenedImage;
    }

    @Override
    public void setGhostState(GhostState state) {
        this.ghostState = state;
        GhostMode ghostMode = this.ghostState.getGhostMode();
        if (this.speeds != null) {
            this.kinematicState.setSpeed(speeds.get(ghostMode));
        }
        // ensure direction is switched
        this.currentDirectionCount = minimumDirectionCount;
        resetTick();
        System.out.println(ghostMode);
    }

    @Override
    public void changeGhostState() {
        this.ghostState.changeState();
    }

    @Override
    public void collect() {
        reset();
    }

    @Override
    public boolean isCollectable() {
        return (ghostState.isCollectable());
    }

    @Override
    public int getPoints() {
        return this.points;
    }
}
