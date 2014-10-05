package is.ru.tgra.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import is.ru.tgra.Point3D;

/**
 * <h1>ThirdPersonCamera</h1>
 * <h2>is.ru.tgra.camera</h2>
 * <p></p>
 * Created on 4.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class ThirdPersonCamera extends AbstractCamera {

    private boolean isActivated;

    public ThirdPersonCamera() {
        FirstPersonCamera FPS = objectFactory.getCamFirstPerson();
        this.lookAt(new Point3D(FPS.eye.x, FPS.eye.y + 5, FPS.eye.z), FPS.eye, new Vector3(0.0f, 1.0f, 0.0f));
        this.perspective(75.0f, 1.333333f, 0.2f, farPlane);
        this.isActivated = false;
    }

    public void switchActivated() {
        this.isActivated = !isActivated;
    }

    public boolean isActivated() {
        return isActivated;
    }

    @Override
    public void setup() {
        if (isActivated) {
            Gdx.gl11.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            this.setMatrices();
        }
    }
}
