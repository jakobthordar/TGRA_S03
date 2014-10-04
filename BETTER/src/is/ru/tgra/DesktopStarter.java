package is.ru.tgra;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 * <h1>DesktopStarter</h1>
 * <h2>is.ru.tgra</h2>
 * <p></p>
 * Created on 2.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class DesktopStarter {

    public static void main(String[] args) {
        new LwjglApplication(new Display(), "TGRA_S03", 800, 600, false);
    }
}
