package is.ru.tgra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

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

    public boolean isWPressed;
    public boolean isAPressed;
    public boolean isSPressed;
    public boolean isDPressed;

    public boolean isUpPressed;
    public boolean isDownPressed;
    public boolean isLeftPressed;
    public boolean isRightPressed;

    public int xMovement;
    public int yMovement;
    public int oldX;
    public int oldY;

    private InputHandler() {
    }

    public static InputHandler getInstance() {
        return instance;
    }

    @Override
    public boolean keyDown(int i) {
        switch (i) {
            case Input.Keys.W:
                isWPressed = true;
                break;
            case Input.Keys.A:
                isAPressed = true;
                break;
            case Input.Keys.S:
                isSPressed = true;
                break;
            case Input.Keys.D:
                isDPressed = true;
                break;
            case Input.Keys.UP:
                isUpPressed = true;
                break;
            case Input.Keys.DOWN:
                isDownPressed = true;
                break;
            case Input.Keys.LEFT:
                isLeftPressed = true;
                break;
            case Input.Keys.RIGHT:
                isRightPressed = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        switch (i) {
            case Input.Keys.W:
                isWPressed = false;
                break;
            case Input.Keys.A:
                isAPressed = false;
                break;
            case Input.Keys.S:
                isSPressed = false;
                break;
            case Input.Keys.D:
                isDPressed = false;
                break;
            case Input.Keys.UP:
                isUpPressed = false;
                break;
            case Input.Keys.DOWN:
                isDownPressed = false;
                break;
            case Input.Keys.LEFT:
                isLeftPressed = false;
                break;
            case Input.Keys.RIGHT:
                isRightPressed = false;
                break;
        }
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
        xMovement = i - oldX;
        yMovement = i2 - oldY;
        return true;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
