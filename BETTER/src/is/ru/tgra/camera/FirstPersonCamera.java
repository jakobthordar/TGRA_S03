package is.ru.tgra.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import is.ru.tgra.Point3D;
import is.ru.tgra.objects.CollidableObject;

import java.util.ArrayList;
import java.util.List;

public class FirstPersonCamera extends AbstractCamera
{

    public boolean isActivated;

    public FirstPersonCamera() {
        this.lookAt(new Point3D(3.0f, 3.0f, 3.0f), new Point3D(0.0f, 3.0f, 0.0f), new Vector3(0.0f, 1.0f, 0.0f));
        this.perspective(75.0f, 1.333333f, 0.1f, farPlane);
        this.isActivated = true;
        this.hCol = false;
        this.vCol = false;

        float left = this.eye.x - (20 / 2f);
        float right = this.eye.x + (20 / 2f);
        float top = this.eye.z + (20 / 2f);
        float bottom = this.eye.z - (20 / 2f);

        this.TL = new Point3D(left, 0, top);
        this.TR = new Point3D(right, 0, top);
        this.BL = new Point3D(left, 0, bottom);
        this.BR = new Point3D(right, 0, bottom);
        points.add(TL);
        points.add(TR);
        points.add(BL);
        points.add(BR);

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
        Vector3 newDir = new Vector3();

        if(inputHandler.isWPressed && !vCol && !hCol) {
            newDir.z = 1;
            this.speed = -maxSpeed;
        }
        if(inputHandler.isSPressed) {
            newDir.z = 1;
            this.speed = maxSpeed;
        }
        if(inputHandler.isLeftPressed) {
            this.yaw(90.0f * deltaTime);
        }
        if(inputHandler.isRightPressed) {
            this.yaw(-90.0f * deltaTime);
        }
        if(inputHandler.isUpPressed) {
            this.pitch(90.0f * deltaTime);
        }
        if(inputHandler.isDownPressed) {
            this.pitch(-90.0f * deltaTime);
        }


        this.setDirection(newDir);

        for (CollidableObject co : objectFactory.getCollidableObjects()) {
            co.collision(objectFactory.getPlayer(), deltaTime);
        }

        this.slide(deltaTime);

        this.speed = 0;
    }

    @Override
    public Point3D getPosition() {
        return eye;
    }

    public Vector3 getDirection() {
        return this.direction;
    }
}
