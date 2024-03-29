package is.ru.tgra;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.utils.BufferUtils;
import is.ru.tgra.camera.Camera;
import is.ru.tgra.camera.FirstPersonCamera;
import is.ru.tgra.camera.TopDownCamera;
import is.ru.tgra.maze.Cell;
import is.ru.tgra.maze.MazeGenerator;
import is.ru.tgra.maze.PrimMazeGenerator;
import is.ru.tgra.maze.Wall;
import is.ru.tgra.objects.*;
import is.ru.tgra.player.Player;

import java.nio.FloatBuffer;
import java.util.List;

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

    private ObjectFactory objectFactory = ObjectFactory.getInstance();
    private InputHandler inputHandler = InputHandler.getInstance();


    Display() {
    }

    public void createSetup() {
        Gdx.gl11.glEnable(GL11.GL_LIGHTING);
        Gdx.gl11.glEnable(GL11.GL_LIGHT0);
        Gdx.gl11.glEnable(GL11.GL_DEPTH_TEST);
        Gdx.gl11.glEnable(GL11.GL_NORMALIZE);
        Gdx.gl11.glClearColor(0.624f, 0.62f, 0.776f, 1.0f);

        Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        Gdx.input.setInputProcessor(inputHandler);
        Box.loadVertices();
        TopDownCamera.loadVertices();
        Arrow.loadVertices();
    }


    @Override
    public void create() {
        createSetup();
        objectFactory.createPlayer();
        objectFactory.createPyramid(10, new Point3D(-75.0f, 15.0f, -50.0f), 80.0f);
        objectFactory.createPyramid(20, new Point3D(-87.0f, 15.0f, 0.0f), 50.0f);
        objectFactory.createPyramid(30, new Point3D(-100.0f, 15.0f, 67.0f), 20.0f);
        objectFactory.createMaze(20, 10);
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

    private void display() {
        Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        for (Camera c : objectFactory.getCameras()) {
            c.setup();

            float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
            Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_AMBIENT_AND_DIFFUSE, lightDiffuse, 0);

            /*float[] lightAmbient = {1.0f, 1.0f, 1.0f, 1.0f};
            Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_AMBIENT_AND_DIFFUSE, lightDiffuse, 0);*/

            float[] lightPosition = {5.0f, 15.0f, 15.0f, 0.0f};
            Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition, 0);

            if (!c.equals(objectFactory.getPlayer().getFirstPersonCamera())) {
                objectFactory.getPlayer().getArrow().draw();
            }

            for (ObjectReference o : objectFactory.getObjects()) {
                o.draw();
            }
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
