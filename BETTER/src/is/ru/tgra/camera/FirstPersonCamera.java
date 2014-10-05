package is.ru.tgra.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import is.ru.tgra.Point3D;
import is.ru.tgra.objects.CollidableObject;

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

        Vector3 direction = new Vector3();
        direction.x = 0;
        direction.y = 0;
        direction.z = 0;
        if(inputHandler.isWPressed) {
            direction.z = -1;
        }
        /*if(inputHandler.isAPressed) {
            direction.x = -1;
        }*/
        if(inputHandler.isSPressed) {
            direction.z = 1;
        }
        /*if(inputHandler.isDPressed) {
            direction.x = 1;
        }*/
        if(inputHandler.isLeftPressed) {
            this.yaw(90.0f * deltaTime);
        }
        if(inputHandler.isRightPressed) {
            this.yaw(-90.0f * deltaTime);
        }
        this.setDirection(direction);
        for (CollidableObject co : objectFactory.getCollidableObjects()) {
            co.collision(objectFactory.getPlayer());
        }
        this.slide(deltaTime);
    }

    public void verticalCollision() {
        this.movement.x = 0;
    }

    public void horizontalCollision() {
        this.movement.z = 0;
    }

    @Override
    public Point3D getPosition() {
        return eye;
    }

    public Vector3 getMovement() {
        Vector3 dir = new Vector3(n);
        //dir.nor();
        /*dir.x *= speed;
        dir.y *= speed;
        dir.z *= speed;*/
        return dir;
    }
}
