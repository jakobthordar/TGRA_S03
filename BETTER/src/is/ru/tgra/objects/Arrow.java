package is.ru.tgra.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;
import is.ru.tgra.Color3;
import is.ru.tgra.Point3D;
import is.ru.tgra.camera.FirstPersonCamera;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

/**
 * <h1>Arrow</h1>
 * <h2>is.ru.tgra.objects</h2>
 * <p></p>
 * Created on 2.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Arrow implements ObjectReference {

    private static FloatBuffer vertexBuffer;
    private Color3 color = Color3.pastelYellow;
    private ObjectFactory objectFactory = ObjectFactory.getInstance();
    private FirstPersonCamera camFirstPerson;

    public Arrow() {
        camFirstPerson = objectFactory.getCamFirstPerson();
    }

    public static void loadVertices()
    {
        vertexBuffer = BufferUtils.newFloatBuffer(126);
        vertexBuffer.put(new float[] {-0.5f, 0.25f, -1.0f, 0.5f, 0.25f, -1.0f, -0.5f, 0.25f, 0.0f, 0.5f, 0.25f, 0.0f,
                -1.0f, 0.25f, 0.0f, 0.0f, 0.25f, 1.0f, 1.0f, 0.25f, 0.0f,
                -0.5f, -0.25f, -1.0f, 0.5f, -0.25f, -1.0f, -0.5f, -0.25f, 0.0f, 0.5f, -0.25f, 0.0f,
                -1.0f, -0.25f, 0.0f, 0.0f, -0.25f, 1.0f, 1.0f, -0.25f, 0.0f,
                -0.5f, 0.25f, -1.0f, 0.5f, 0.25f, -1.0f, -0.5f, -0.25f, -1.0f, 0.5f, -0.25f, -1.0f,
                -1.0f, 0.25f, 0.0f, -0.5f, 0.25f, 0.0f, -1.0f, -0.25f, 0.0f, -0.5f, -0.25f, 0.0f,
                1.0f, 0.25f, 0.0f, 0.5f, 0.25f, 0.0f, 1.0f, -0.25f, 0.0f, 0.5f, -0.25f, 0.0f,
                -0.5f, 0.25f, -1.0f, -0.5f, 0.25f, 0.0f, -0.5f, -0.25f, -1.0f, -0.5f, -0.25f, 0.0f,
                0.5f, 0.25f, -1.0f, 0.5f, 0.25f, 0.0f, 0.5f, -0.25f, -1.0f, 0.5f, -0.25f, 0.0f,
                -1.0f, 0.25f, 0.0f, 0.0f, 0.25f, 1.0f, -1.0f, -0.25f, 0.0f, 0.0f, -0.25f, 1.0f,
                0.0f, 0.25f, 1.0f, 1.0f, 0.25f, 0.0f, 0.0f, -0.25f, 1.0f, 1.0f, -0.25f, 0.0f});
        vertexBuffer.rewind();
    }

    @Override
    public void draw() {

        Gdx.gl11.glPushMatrix();
        float[] matrix = new float[16];
        matrix[0] = camFirstPerson.u.x;	matrix[4] = camFirstPerson.v.x;	matrix[8] = camFirstPerson.n.x;	matrix[12] = camFirstPerson.eye.x;
        matrix[1] = camFirstPerson.u.y;	matrix[5] = camFirstPerson.v.y;	matrix[9] = camFirstPerson.n.y;	matrix[13] = camFirstPerson.eye.y;
        matrix[2] = camFirstPerson.u.z;	matrix[6] = camFirstPerson.v.z;	matrix[10] = camFirstPerson.n.z;matrix[14] = camFirstPerson.eye.z;
        matrix[3] = 0;					matrix[7] = 0;					matrix[11] = 0;					matrix[15] = 1;

        Gdx.gl11.glMultMatrixf(matrix, 0);
        Gdx.gl11.glRotatef(180.0f, 0, 1, 0);
        Gdx.gl11.glVertexPointer(3, GL11.GL_FLOAT, 0, vertexBuffer);

        float[] materialDiffuse = {color.r, color.g, color.b, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffuse, 0);

        Gdx.gl11.glNormal3f(0.0f, 1.0f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLES, 4, 3);

        Gdx.gl11.glNormal3f(0.0f, -1.0f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 7, 4);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLES, 11, 3);

        Gdx.gl11.glNormal3f(0.0f, 0.0f, -1.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 14, 4);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 18, 4);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 22, 4);


        Gdx.gl11.glNormal3f(-1.0f, 0.0f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 26, 4);
        Gdx.gl11.glNormal3f(1.0f, 0.0f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 30, 4);

        float sqrt2 = (float)Math.sqrt(2.0);
        Gdx.gl11.glNormal3f(-sqrt2, 0.0f, sqrt2);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 34, 4);
        Gdx.gl11.glNormal3f(sqrt2, 0.0f, sqrt2);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 38, 4);
        Gdx.gl11.glPopMatrix();
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public Point3D getPosition() {
        return this.camFirstPerson.eye;
    }
}
