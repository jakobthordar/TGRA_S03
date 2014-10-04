package is.ru.tgra.objects;

import is.ru.tgra.Color3;
import is.ru.tgra.Point3D;

import java.util.ArrayList;
import java.util.List;

/**
 * Shape factory used to new objects.
 *
 * @author jakob
 * Created on 30.9.2014.
 */
public class ObjectFactory {


    protected static ObjectFactory instance = new ObjectFactory();

    //protected static FirstPersonCamera camFirstPerson;
    protected static List<ObjectReference> objects = new ArrayList<ObjectReference>();
    protected static List<Box> boxes = new ArrayList<Box>();
    //protected static List<Pyramid> pyramids = new ArrayList<Pyramid>();

    private ObjectFactory() {
    }

    public static ObjectFactory getInstance() {
        return instance;
    }

    /**
     * Creates a box and adds it to the shapes and boxes list.
     * @param position Initial position of the box.
     * @param size Initial size factor.
     * @param color The color of the box.
     */
    public Box createBox(Point3D position, float size, Color3 color) {
        Box box = new Box(position, size, color);
        objects.add(box);
        boxes.add(box);
        return box;
    }

    public Box createPyramidBox(Point3D position, float size, Color3 color) {
        Box box = new Box(position, size, color);
        return box;
    }
    /*

    public static FirstPersonCamera createFirstPersonCamera() {
        camFirstPerson = new FirstPersonCamera();
        camFirstPerson.lookAt(new Point3D(0.0f, 0.0f, 5.0f), new Point3D(0.0f, 0.0f, 0.0f), new Vector3(0.0f, 1.0f, 0.0f));
        camFirstPerson.perspective(75.0f, 1.333333f, 0.2f, 10.0f);
        objects.add(camFirstPerson);
        return camFirstPerson;
    }

    public static Pyramid createPyramid() {
        Pyramid pyramid = new Pyramid();
        objects.add(pyramid);
        pyramids.add(pyramid);
        return pyramid;
    }

    public static FirstPersonCamera getCamFirstPerson() {
        return camFirstPerson;
    }
    */

    public List<ObjectReference> getObjects() {
        return objects;
    }

    public static List<Box> getBoxes() {
        return boxes;
    }
}
