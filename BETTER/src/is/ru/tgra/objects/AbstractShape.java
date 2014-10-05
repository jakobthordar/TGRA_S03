package is.ru.tgra.objects;

import is.ru.tgra.Color3;
import is.ru.tgra.Point3D;

/**
 * <p></p>
 *
 * @author jakob
 *         Created on 30.9.2014.
 */
public abstract class AbstractShape implements ObjectReference {

    protected Point3D position;
    protected float xSize;
    protected float ySize;
    protected float zSize;
    protected Color3 color;

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public void setColor(Color3 color) {
        this.color = color;
    }

    @Override
    public abstract void draw();

    @Override
    public abstract void update(float deltaTime);

    @Override
    public String toString() {
        return "AbstractShape{" +
                "position=" + position +
                ", color=" + color +
                '}';
    }
}
