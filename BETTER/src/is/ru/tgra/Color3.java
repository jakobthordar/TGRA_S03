package is.ru.tgra;

/**
 *
 *
 * @author jakob
 * Created on 30.9.2014.
 */
public class Color3 {

    public float r;
    public float g;
    public float b;
    public static Color3 floorColor = new Color3(0.55f, 0.5f, 0.25f);
    public static Color3 pastelYellow = new Color3(0.82f, 0.677f, 0.251f);
    public static Color3 pastelBlue = new Color3(0.38f, 0.631f, 0.953f);
    public static Color3 pastelRed = new Color3(0.831f, 0.373f, 0.251f);
    public static Color3 wineRed = new Color3(0.69f, 0.0f, 0.082f);
    public static Color3 grey = new Color3(0.282f, 0.282f, 0.282f);

    public Color3(float rr, float gg, float bb) {
        r = rr;
        g = gg;
        b = bb;
    }

    public void set(float rr, float gg, float bb) {
        r = rr;
        g = gg;
        b = bb;
    }
}
