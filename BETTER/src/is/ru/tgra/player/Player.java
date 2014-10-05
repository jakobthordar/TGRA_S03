package is.ru.tgra.player;

import com.badlogic.gdx.math.Vector3;
import is.ru.tgra.Point3D;
import is.ru.tgra.camera.FirstPersonCamera;
import is.ru.tgra.camera.ThirdPersonCamera;
import is.ru.tgra.camera.TopDownCamera;
import is.ru.tgra.objects.Arrow;
import is.ru.tgra.objects.ObjectFactory;
import is.ru.tgra.objects.ObjectReference;

/**
 * <h1>Player</h1>
 * <h2>is.ru.tgra.player</h2>
 * <p></p>
 * Created on 4.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Player implements ObjectReference {

    private FirstPersonCamera firstPersonCamera;
    private TopDownCamera topDownCamera;
    private ThirdPersonCamera thirdPersonCamera;
    private Arrow arrow;
    private ObjectFactory objectFactory = ObjectFactory.getInstance();

    public Player() {
    }

    public void setFirstPersonCamera(FirstPersonCamera firstPersonCamera) {
        this.firstPersonCamera = firstPersonCamera;
    }

    public void setTopDownCamera(TopDownCamera topDownCamera) {
        this.topDownCamera = topDownCamera;
    }

    public void setThirdPersonCamera(ThirdPersonCamera thirdPersonCamera) {
        this.thirdPersonCamera = thirdPersonCamera;
    }

    public void setArrow(Arrow arrow) {
        this.arrow = arrow;
    }

    public Arrow getArrow() {
        return arrow;
    }

    public void switchCameras() {
        firstPersonCamera.switchActivated();
    }

    @Override
    public void draw() {

    }

    public void verticalCollision() {
        firstPersonCamera.verticalCollision();
    }

    public void horizontalCollision() {
        firstPersonCamera.horizontalCollision();
    }

    @Override
    public void update(float deltaTime) {
        firstPersonCamera.update(deltaTime);
    }

    public Vector3 getDirection() {
        return firstPersonCamera.getDirection();
    }

    @Override
    public Point3D getPosition() {
        return firstPersonCamera.getPosition();
    }
}
