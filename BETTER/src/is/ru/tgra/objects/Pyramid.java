package is.ru.tgra.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import is.ru.tgra.Color3;
import is.ru.tgra.Point3D;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author jakob
 *         Created on 30.9.2014.
 */
public class Pyramid implements CollidableObject {

    protected float pyramidRotate;
    protected List<Box> pyramidBoxes = new ArrayList<Box>();
    protected ObjectFactory factory = ObjectFactory.getInstance();
    protected int totalLevels;
    protected Point3D position;
    protected float width;
    protected float boxwidth;
    protected float pyramidSpeed;
    protected float chunkSize;
    protected float flatness;

    public Pyramid() {
        pyramidRotate = 0.0f;
        this.pyramidSpeed = 180.0f;
        totalLevels = 5;
        this.boxwidth = 1.2f;
        this.width = boxwidth * totalLevels;
        this.position = new Point3D(-2.4f, 0.0f, -2.4f);
        this.chunkSize = 0.0f;
        this.flatness = 1.5f;
        generatePyramid();
    }
    public Pyramid(int totalLevels, Point3D position, float pyramidSpeed) {
        this.pyramidRotate = 0.0f;
        this.pyramidSpeed = pyramidSpeed;
        this.totalLevels = totalLevels;
        this.position = position;
        this.boxwidth = 1.2f;
        this.width = boxwidth * totalLevels;
        this.chunkSize = 0.0f;
        this.flatness = 1.5f;
        generatePyramid();
    }

    private void generatePyramid() {
        /*for(int level = 0; level < totalLevels; level++) {
            for(int i = 0; i < totalLevels - level; i++) {
                for(int j = 0; j < totalLevels - level; j++) {
                    pyramidBoxes.add(factory.createPyramidBox(new Point3D(level * 0.6f + i * 1.2f, level * 1.2f, level * 0.6f + j * 1.2f),
                            1.2f,
                            new Color3(1.0f, 0.5f, 0.0f)));
                }
            }
        }*/

        for(int level = 0; level < totalLevels; level++) {
            for(int i = 0; i < totalLevels - level; i++) {
                chunkSize = (1.2f * (i + 1)) * flatness;
                pyramidBoxes.add(factory.createPyramidBox(new Point3D(width/2, 1.2f * level, width/2),
                    chunkSize,
                    1.2f,
                    new Color3(1.0f, 0.5f, 0.0f)));
            }
        }
    }

    @Override
    public void draw() {
        Gdx.gl11.glPushMatrix();

        //Gdx.gl11.glTranslatef(5.0f * MathUtils.cos(0.2f * pyramidRotate * (float) Math.PI / 180.0f), 0.0f, 0.0f);
        Gdx.gl11.glTranslatef(position.x, position.y, position.z);

        Gdx.gl11.glRotatef(pyramidRotate, 0, 1, 0);
        Gdx.gl11.glTranslatef(-width/2, 0.0f, -width/2);
        for(Box b : pyramidBoxes)
        {
            b.draw();
        }
        Gdx.gl11.glPopMatrix();
    }

    @Override
    public void update(float deltaTime) {
        pyramidRotate += pyramidSpeed * deltaTime;
    }

    @Override
    public Point3D getPosition() {
        return position;
    }

    @Override
    public void collision(ObjectReference or) {
    }
}
