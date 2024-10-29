package pacman.model.entity.staticentity.CollectableDecorator;

import pacman.model.entity.staticentity.collectable.Collectable;

public abstract class CollectableDecorator implements Collectable {
    protected Collectable collectable;

    public CollectableDecorator(Collectable collectable) {
        this.collectable = collectable;
    }

    public void collect() {
        this.collectable.collect();
    }

    public boolean isCollectable() {
        return this.collectable.isCollectable();
    }

    public int getPoints() {
        return this.collectable.getPoints();
    }
}
