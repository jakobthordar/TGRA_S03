package is.ru.tgra.objects;

import is.ru.tgra.Point3D;

/**
 * A class which represents all drawable objects.
 * All objects know how to draw themselves and update themselves.
 * All object should be able to return their position.
 *
 * @author jakob
 * Created on 29.9.2014.
 */
public interface ObjectReference {

    /**
     * All object get drawn in this function.
     */
    public void draw();

    public void update(float deltaTime);

    public Point3D getPosition();
}
