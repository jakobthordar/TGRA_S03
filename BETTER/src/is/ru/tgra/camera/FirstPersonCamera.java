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
        this.perspective(75.0f, 1.333333f, 0.2f, farPlane);
        this.isActivated = true;
        this.hCol = false;
        this.vCol = false;

        float center_x = 0.0f;
        float center_z = 0.0f;
        float outerVertexCount = vertexCount - 1;
        for (int i = 0; i < outerVertexCount; ++i) {
            float percent = (i / (float) (outerVertexCount - 1));
            float rad = percent * 2 * (float) Math.PI;

            float outer_x = center_x + 7.0f * (float) Math.cos(rad);
            float outer_z = center_z + 7.0f * (float) Math.sin(rad);
            this.points.add(new Point3D(outer_x, 0, outer_z));
        }
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
            newDir.z = -1;
            this.speed = maxSpeed;
        }
        if(inputHandler.isSPressed) {
            newDir.z = -1;
            this.speed = -maxSpeed;
        }
        if(inputHandler.isLeftPressed) {
            this.yaw(90.0f * deltaTime);
        }
        if(inputHandler.isRightPressed) {
            this.yaw(-90.0f * deltaTime);
        }


        this.setDirection(newDir);

        for (CollidableObject co : objectFactory.getCollidableObjects()) {
            co.collision(objectFactory.getPlayer(), deltaTime);
        }


        this.slide(deltaTime);

        /*
        Point3D point;
        for (int i = 0; i < points.size(); i++) {
            point = points.get(i);
            point.x += motion.x * deltaTime;
            point.z += motion.z * deltaTime;
            points.set(i, point);
        }
        */
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
