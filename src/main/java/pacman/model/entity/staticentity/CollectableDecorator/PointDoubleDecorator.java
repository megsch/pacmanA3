package pacman.model.entity.staticentity.CollectableDecorator;

import javafx.scene.image.Image;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.staticentity.collectable.Collectable;

public class PointDoubleDecorator extends CollectableDecorator {

    private final int MULTIPLIER = 2;

    public PointDoubleDecorator(Collectable collectable) {
        super(collectable);
    }

    @Override
    public Image getImage() {
        return this.collectable.getImage();
    }

    @Override
    public double getWidth() {
        return this.collectable.getWidth();
    }

    @Override
    public double getHeight() {
        return this.collectable.getHeight();
    }

    @Override
    public Vector2D getPosition() {
        return this.collectable.getPosition();
    }

    @Override
    public Layer getLayer() {
        return this.collectable.getLayer();
    }

    @Override
    public BoundingBox getBoundingBox() {
        return this.collectable.getBoundingBox();
    }

    @Override
    public void reset() {
        this.collectable.reset();
    }

    @Override
    public int getPoints() {
        return this.collectable.getPoints() * MULTIPLIER;
    }
}
