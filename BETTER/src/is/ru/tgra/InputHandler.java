package is.ru.tgra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

/**
 * <h1>InputHandler</h1>
 * <h2>is.ru.tgra</h2>
 * <p></p>
 * Created on 2.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class InputHandler implements InputProcessor {

    private static InputHandler instance = new InputHandler();

    public boolean keyDown;

    private InputHandler() {
    }

    public static InputHandler getInstance() {
        return instance;
    }

    @Override
    public boolean keyDown(int i) {
        keyDown = true;
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        keyDown = false;
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
