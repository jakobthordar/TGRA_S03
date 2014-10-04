package is.ru.tgra.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.utils.BufferUtils;
import is.ru.tgra.Color3;
import is.ru.tgra.Point3D;

import java.nio.FloatBuffer;

/**
 * <p></p>
 *
 * @author jakob
 *         Created on 30.9.2014.
 */
public class Box extends AbstractShape {

    private static FloatBuffer vertexBuffer;

    public Box() {
    }

    public Box(Point3D position, float size, Color3 color) {
        this.position = position;
        this.size = size;
        this.color = color;
    }

    public static void loadVertices() {
        vertexBuffer = BufferUtils.newFloatBuffer(72);
        vertexBuffer.put(new float[] {
                -0.5f, -0.5f, -0.5f, -0.5f,  0.5f, -0.5f,
                 0.5f, -0.5f, -0.5f,  0.5f,  0.5f, -0.5f,
                 0.5f, -0.5f, -0.5f,  0.5f,  0.5f, -0.5f,
                 0.5f, -0.5f,  0.5f,  0.5f,  0.5f,  0.5f,
                 0.5f, -0.5f,  0.5f,  0.5f,  0.5f,  0.5f,
                -0.5f, -0.5f,  0.5f, -0.5f,  0.5f,  0.5f,
                -0.5f, -0.5f,  0.5f, -0.5f,  0.5f,  0.5f,
                -0.5f, -0.5f, -0.5f, -0.5f,  0.5f, -0.5f,
                -0.5f,  0.5f, -0.5f, -0.5f,  0.5f,  0.5f,
                 0.5f,  0.5f, -0.5f,  0.5f,  0.5f,  0.5f,
                -0.5f, -0.5f, -0.5f, -0.5f, -0.5f,  0.5f,
                 0.5f, -0.5f, -0.5f,  0.5f, -0.5f,  0.5f});
        vertexBuffer.rewind();
    }

    @Override
    public void draw() {


        Gdx.gl11.glPushMatrix();
        Gdx.gl11.glVertexPointer(3, GL11.GL_FLOAT, 0, vertexBuffer);

        Gdx.gl11.glTranslatef(position.x, position.y, position.z);
        Gdx.gl11.glScalef(size, size, size);

        float[] materialDiffuse = {color.r, color.g, color.b, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffuse, 0);

        Gdx.gl11.glNormal3f(0.0f, 0.0f, -1.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
        Gdx.gl11.glNormal3f(1.0f, 0.0f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glNormal3f(0.0f, 0.0f, 1.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 8, 4);
        Gdx.gl11.glNormal3f(-1.0f, 0.0f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 12, 4);
        Gdx.gl11.glNormal3f(0.0f, 1.0f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 16, 4);
        Gdx.gl11.glNormal3f(0.0f, -1.0f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 20, 4);

        Gdx.gl11.glPopMatrix();
    }

    @Override
    public void update(float deltaTime) {

    }




}
