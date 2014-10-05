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
public class Pyramid implements ObjectReference {

    protected float pyramidRotate;
    protected List<Box> pyramidBoxes = new ArrayList<Box>();
    protected ObjectFactory factory = ObjectFactory.getInstance();
    protected int totalLevels;

    public Pyramid() {
        pyramidRotate = 0.0f;
        totalLevels = 5;
        generatePyramid();
    }
    public Pyramid(int totalLevels) {
        pyramidRotate = 0.0f;
        this.totalLevels = totalLevels;
        generatePyramid();
    }

    private void generatePyramid() {
        for(int level = 0; level < totalLevels; level++) {
            for(int i = 0; i < totalLevels - level; i++) {
                for(int j = 0; j < totalLevels - level; j++) {
                    pyramidBoxes.add(factory.createPyramidBox(new Point3D(level * 0.6f + i * 1.2f, level * 1.0f, level * 0.6f + j * 1.2f),
                            1,
                            new Color3(1.0f, 0.5f, 0.0f)));
                }
            }
        }
    }

    @Override
    public void draw() {
        Gdx.gl11.glPushMatrix();

        //Gdx.gl11.glTranslatef(5.0f * MathUtils.cos(0.2f * pyramidRotate * (float) Math.PI / 180.0f), 0.0f, 0.0f);

        Gdx.gl11.glRotatef(pyramidRotate, 0, 1, 0);
        Gdx.gl11.glTranslatef(-2.4f, 0.0f, -2.4f);
        for(Box b : pyramidBoxes)
        {
            b.draw();
        }
        Gdx.gl11.glPopMatrix();
    }

    @Override
    public void update(float deltaTime) {
        pyramidRotate += 180.0f * deltaTime;
    }
}
