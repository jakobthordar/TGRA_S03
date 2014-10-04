package is.ru.tgra.objects;

/**
 * A class which represents all drawable objects.
 * All objects know how to draw themselves and update themselves.
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
}
