package is.ru.tgra.camera;

/**
 * <p></p>
 *
 * @author jakob
 *         Created on 30.9.2014.
 */
public abstract class AbstractCamera implements Camera {

    @Override
    public abstract void setup();

    @Override
    public abstract void update(float deltaTime);

    /**
     * Swallow the draw function.
     */
    @Override
    public void draw() {
    }

}
