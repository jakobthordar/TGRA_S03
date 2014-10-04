package is.ru.tgra;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.utils.BufferUtils;
import is.ru.tgra.objects.Box;
import is.ru.tgra.objects.ObjectFactory;
import is.ru.tgra.objects.ObjectReference;

import java.nio.FloatBuffer;

/**
 * <h1>Display</h1>
 * <h2>is.ru.tgra</h2>
 * <p></p>
 * Created on 2.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Display implements ApplicationListener {

    ObjectFactory objectFactory = ObjectFactory.getInstance();
    InputHandler inputHandler = InputHandler.getInstance();

    Display() {
    }

    public void createSetup() {
        Gdx.gl11.glEnable(GL11.GL_LIGHTING);
        Gdx.gl11.glEnable(GL11.GL_LIGHT0);
        Gdx.gl11.glEnable(GL11.GL_DEPTH_TEST);
        Gdx.gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl11.glMatrixMode(GL11.GL_PROJECTION);
        Gdx.gl11.glLoadIdentity();
        Gdx.glu.gluPerspective(Gdx.gl11, 75, 1.333333f, 1.0f, 10.0f);
        Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        Gdx.input.setInputProcessor(inputHandler);
        Box.loadVertices();
    }

    @Override
    public void create() {
        createSetup();
        objectFactory.createBox(new Point3D(0, 0, 0), 2, new Color3(1f, 0.2f, 0.0f));
        objectFactory.createBox(new Point3D(-2.5f, 0, 0), 2, new Color3(1f, 0.2f, 0.0f));
        objectFactory.createBox(new Point3D(-5, 0, 0), 2, new Color3(0.2f, 1.0f, 0.0f));
    }

    @Override
    public void resume() {

    }

    private void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        for (ObjectReference o : objectFactory.getObjects()) {
            o.update(deltaTime);
        }
    }

    public void displaySetup() {
        Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);

        Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW);
        Gdx.gl11.glLoadIdentity();
        Gdx.glu.gluLookAt(Gdx.gl11, 3.0f, 3.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);

        float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, lightDiffuse, 0);

        float[] lightPosition = {5.0f, 10.0f, 15.0f, 0.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition, 0);
    }

    private void display() {
        displaySetup();
        for (ObjectReference o : objectFactory.getObjects()) {
            o.draw();
        }
    }

    @Override
    public void render() {
        update();
        display();
    }

    @Override
    public void resize(int i, int i2) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void dispose() {

    }
}
