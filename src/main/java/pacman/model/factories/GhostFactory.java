package pacman.model.factories;

import javafx.scene.image.Image;
import pacman.ConfigurationParseException;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.ghost.GhostImpl;
import pacman.model.entity.dynamic.ghost.GhostMode;
import pacman.model.entity.dynamic.ghost.strategy.*;
import pacman.model.entity.dynamic.physics.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete renderable factory for Ghost objects
 */
public class GhostFactory implements RenderableFactory {

    private static final int RIGHT_X_POSITION_OF_MAP = 448;
    private static final int TOP_Y_POSITION_OF_MAP = 16 * 3;
    private static final int BOTTOM_Y_POSITION_OF_MAP = 16 * 34;

    private static final Map<Character, Image> IMAGES = new HashMap<>();
    static {
        IMAGES.put(RenderableType.BLINKY, new Image("maze/ghosts/blinky.png"));
        IMAGES.put(RenderableType.PINKY, new Image("maze/ghosts/pinky.png"));
        IMAGES.put(RenderableType.INKY, new Image("maze/ghosts/inky.png"));
        IMAGES.put(RenderableType.CLYDE, new Image("maze/ghosts/clyde.png"));
    }

    private static final Image FRIGHTENEDIMAGE = new Image("maze/ghosts/frightened.png");

    private static final Map<Character, GhostStrategy> GHOSTSTRATEGY = new HashMap<>();
    static {
        GHOSTSTRATEGY.put(RenderableType.PINKY, new PinkyStrategy());
        GHOSTSTRATEGY.put(RenderableType.INKY, new InkyStrategy());
        GHOSTSTRATEGY.put(RenderableType.BLINKY, new BlinkyStrategy());
        GHOSTSTRATEGY.put(RenderableType.CLYDE, new ClydeStrategy());
    }

    private final Image image;
    private final GhostStrategy strategy;

    public GhostFactory(char renderableType) {
        this.image = IMAGES.get(renderableType);
        this.strategy = GHOSTSTRATEGY.get(renderableType);
    }

    @Override
    public Renderable createRenderable(
            Vector2D position
    ) {
        try {
            position = position.add(new Vector2D(4, -4));

            BoundingBox boundingBox = new BoundingBoxImpl(
                    position,
                    this.image.getHeight(),
                    this.image.getWidth()
            );

            KinematicState kinematicState = new KinematicStateImpl.KinematicStateBuilder()
                    .setPosition(position)
                    .build();

            return new GhostImpl(
                    this.image,
                    FRIGHTENEDIMAGE,
                    boundingBox,
                    kinematicState,
                    this.strategy);
        } catch (Exception e) {
            throw new ConfigurationParseException(
                    String.format("Invalid ghost configuration | %s ", e));
        }
    }


}
