package is.ru.tgra.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import is.ru.tgra.Point3D;

public class FirstPersonCamera extends AbstractCamera
{

    public boolean isActivated;

    public FirstPersonCamera() {
        this.lookAt(new Point3D(3.0f, 3.0f, 3.0f), new Point3D(0.0f, 3.0f, 0.0f), new Vector3(0.0f, 1.0f, 0.0f));
        this.perspective(75.0f, 1.333333f, 0.2f, farPlane);
        this.isActivated = true;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void switchActivated() {
        this.isActivated = !isActivated;
    }

    @Override
    public void setup() {
        if (isActivated) {
            Gdx.gl11.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            this.setMatrices();
        }
    }

    @Override
    public void update(float deltaTime) {

        if(inputHandler.isWPressed) {
            this.slide(0, 0, deltaTime * -speed);
        }
        if(inputHandler.isAPressed) {
            this.slide(deltaTime * -speed, 0, 0);
        }
        if(inputHandler.isSPressed) {
            this.slide(0, 0, deltaTime * speed);
        }
        if(inputHandler.isDPressed) {
            this.slide(deltaTime * speed, 0, 0);
        }
        if(inputHandler.isUpPressed) {
            this.pitch(-90.0f* deltaTime);
        }
        if(inputHandler.isDownPressed) {
            this.pitch(90.0f * deltaTime);
        }
        if(inputHandler.isLeftPressed) {
            this.yaw(90.0f * deltaTime);
        }
        if(inputHandler.isRightPressed) {
            this.yaw(-90.0f * deltaTime);
        }

        //this.yaw(inputHandler.xMovement * deltaTime);

        //this.pitch(inputHandler.yMovement * deltaTime);
    }
}
