package is.ru.tgra.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.BufferUtils;
import is.ru.tgra.Point3D;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

/**
 * <h1>TopDownCamera</h1>
 * <h2>is.ru.tgra.camera</h2>
 * <p></p>
 * Created on 4.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class TopDownCamera extends AbstractCamera {

    private static FloatBuffer vertexBuffer2DBox;
    private float distance = 30;

    public TopDownCamera() {
        this.perspective(40.0f, 1.333333f, 5.0f, farPlane);
    }

    public static void loadVertices() {
        vertexBuffer2DBox = BufferUtils.newFloatBuffer(8);
        vertexBuffer2DBox.put(new float[] {0,0, 0,1, 1,0, 1,1});
        vertexBuffer2DBox.rewind();
    }

    @Override
    public void setup() {
        Gdx.gl11.glViewport(0, 0, 320, 240);
        Gdx.gl11.glClear(GL11.GL_DEPTH_BUFFER_BIT);


        Gdx.gl11.glDisable(GL11.GL_DEPTH_TEST);
        Gdx.gl11.glDisable(GL11.GL_LIGHTING);


        Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW);
        Gdx.gl11.glLoadIdentity();
        Gdx.gl11.glMatrixMode(GL11.GL_PROJECTION);
        Gdx.gl11.glLoadIdentity();
        Gdx.glu.gluOrtho2D(Gdx.gl10, 0, 1, 0, 1);


        Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, vertexBuffer2DBox);
        Gdx.gl11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);

        Gdx.gl11.glEnable(GL11.GL_LIGHTING);
        Gdx.gl11.glEnable(GL11.GL_DEPTH_TEST);

        //camFirstPerson.setMatrices();
        FirstPersonCamera FPC = objectFactory.getCamFirstPerson();
        this.lookAt(new Point3D(FPC.eye.x, distance, FPC.eye.z),
                FPC.eye, new Vector3(0, 0, -1));
        this.setMatrices();
    }

    @Override
    public Point3D getPosition() {
        return eye;
    }
}
