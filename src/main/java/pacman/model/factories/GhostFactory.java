package pacman.model.factories;

import javafx.scene.image.Image;
import pacman.ConfigurationParseException;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.ghost.GhostImpl;
import pacman.model.entity.dynamic.ghost.GhostMode;
import pacman.model.entity.dynamic.ghost.strategy.*;
import pacman.model.entity.dynamic.physics.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
        IMAGES.put(RenderableType.BINKY, new Image("maze/ghosts/blinky.png"));
        IMAGES.put(RenderableType.PINKY, new Image("maze/ghosts/pinky.png"));
        IMAGES.put(RenderableType.INKY, new Image("maze/ghosts/inky.png"));
        IMAGES.put(RenderableType.CLYDE, new Image("maze/ghosts/clyde.png"));
    }

    private static final Map<Character, GhostStrategy> TARGETCORNER = new HashMap<>();
    static {
        TARGETCORNER.put(RenderableType.PINKY, new PinkyStrategy());
        TARGETCORNER.put(RenderableType.INKY, new InkyStrategy());
        TARGETCORNER.put(RenderableType.BINKY, new BinkyStrategy());
        TARGETCORNER.put(RenderableType.CLYDE, new ClydeStrategy());
    }

    private static final Image BLINKY_IMAGE = new Image("maze/ghosts/blinky.png");
    private static final Image GHOST_IMAGE = BLINKY_IMAGE;
    List<Vector2D> targetCorners = Arrays.asList(
            new Vector2D(0, TOP_Y_POSITION_OF_MAP),
            new Vector2D(RIGHT_X_POSITION_OF_MAP, TOP_Y_POSITION_OF_MAP),
            new Vector2D(0, BOTTOM_Y_POSITION_OF_MAP),
            new Vector2D(RIGHT_X_POSITION_OF_MAP, BOTTOM_Y_POSITION_OF_MAP)
    );

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public Renderable createRenderable(
            Vector2D position
    ) {
        try {
            position = position.add(new Vector2D(4, -4));

            BoundingBox boundingBox = new BoundingBoxImpl(
                    position,
                    GHOST_IMAGE.getHeight(),
                    GHOST_IMAGE.getWidth()
            );

            KinematicState kinematicState = new KinematicStateImpl.KinematicStateBuilder()
                    .setPosition(position)
                    .build();

            return new GhostImpl(
                    GHOST_IMAGE,
                    boundingBox,
                    kinematicState,
                    GhostMode.SCATTER,
                    targetCorners.get(getRandomNumber(0, targetCorners.size() - 1)));
        } catch (Exception e) {
            throw new ConfigurationParseException(
                    String.format("Invalid ghost configuration | %s ", e));
        }
    }


}
