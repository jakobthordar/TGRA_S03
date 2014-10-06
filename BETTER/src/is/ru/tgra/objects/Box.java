package is.ru.tgra.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.BufferUtils;
import is.ru.tgra.Color3;
import is.ru.tgra.Point3D;
import is.ru.tgra.player.Player;

import java.nio.FloatBuffer;

/**
 * <p></p>
 *
 * @author jakob
 *         Created on 30.9.2014.
 */
public class Box extends AbstractShape {

    private static FloatBuffer vertexBuffer;
    private Point3D TL;
    private Point3D TR;
    private Point3D BL;
    private Point3D BR;

    public Box() {
    }

    public Box(Point3D position, float xSize, float ySize, float zSize, Color3 color) {
        this.position = position;
        this.ySize = ySize;
        this.xSize = xSize;
        this.zSize = zSize;
        this.color = color;

        float left = this.position.x - (xSize / 2f);
        float right = this.position.x + (xSize / 2f);
        float top = this.position.z + (zSize / 2f);
        float bottom = this.position.z - (zSize / 2f);

        this.TL = new Point3D(left, 0, top);
        this.TR = new Point3D(right, 0, top);
        this.BL = new Point3D(left, 0, bottom);
        this.BR = new Point3D(right, 0, bottom);
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
        Gdx.gl11.glScalef(xSize, ySize, zSize);

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

    /*
    @Override
    public void collision(ObjectReference or, float deltaTime) {
        System.out.println(objectFactory.getPlayer().getDirection());
        Player player = (Player) or;
        Point3D playerPos = player.getPosition();
        Vector3 direction = player.getDirection();

        if (playerPos.x < position.x) {

        }
    }
    */

    @Override
    public void collision(ObjectReference or, float deltaTime) {
        Player player = (Player) or;
        float tHit;
        Point3D pHit;
        Vector3 motion = player.getDirection();
        Point3D position = player.getPosition();

        tHit = this.tHit(position, BL, BR, motion);
        if (tHit <= deltaTime && tHit > 0) {
            pHit = this.pHit(position, motion, tHit);
            if (checkIfOnLine(BL, BR, pHit)) {
                player.horizontalCollision();
            }
        }

        tHit = this.tHit(position, BL, TL, motion);
        if (tHit <= deltaTime && tHit > 0) {
            pHit = this.pHit(position, motion, tHit);
            if (checkIfOnLine(BL, TL, pHit)) {
                player.verticalCollision();
            }
        }

        tHit = this.tHit(position, TL, TR, motion);
        if (tHit <= deltaTime && tHit > 0) {
            pHit = this.pHit(position, motion, tHit);
            if (checkIfOnLine(TL, TR, pHit)) {
                player.horizontalCollision();
            }
        }

        tHit = this.tHit(position, BR, TR, motion);
        if (tHit <= deltaTime && tHit > 0) {
            pHit = this.pHit(position, motion, tHit);
            if (checkIfOnLine(BR, TR, pHit)) {
                player.verticalCollision();
            }
        }
    }

    @Override
    public Point3D getPosition() {
        return position;
    }
}
