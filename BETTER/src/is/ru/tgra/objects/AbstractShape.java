package is.ru.tgra.objects;

import com.badlogic.gdx.math.Vector3;
import is.ru.tgra.Color3;
import is.ru.tgra.Point3D;

/**
 * <p></p>
 *
 * @author jakob
 *         Created on 30.9.2014.
 */
public abstract class AbstractShape implements CollidableObject {

    protected Point3D position;
    protected float xSize;
    protected float ySize;
    protected float zSize;
    protected Color3 color;
    protected ObjectFactory objectFactory = ObjectFactory.getInstance();

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public void setColor(Color3 color) {
        this.color = color;
    }

    @Override
    public abstract void draw();
    
    /**
     * This function calculates the time until intersection
     * @param position position
     * @param b point of line
     * @param c second point of line
     * @param motion motion
     * @return
     */
    public float tHit(Point3D position, Point3D b, Point3D c, Vector3 motion) {

        // TODO: Create function/class which find a normal between two points, maybe?
        float tempX = b.x - c.x;
        float tempZ = b.z - c.z;
        Vector3 n = new Vector3(-tempZ, 0, tempX);

        Vector3 tempPoint = new Vector3(b.x - position.x, 0, b.z - position.z);

        float result = (n.dot(tempPoint) / n.dot(motion));

        return result;
    }
    
    /**
     * This function checks if pHit is on a line between a and v
     * @param a point of line
     * @param b second point of line
     * @param pHit pHit
     * @return returns true if pHit is on line between a and v
     */
    public boolean checkIfOnLine(Point3D a, Point3D b, Point3D pHit) {
        return (a.lengthTo(pHit) + b.lengthTo(pHit)) == a.lengthTo(b);
    }
    
    /**
     * This function calculates the point at which the vector v will intersect
     * the line between points b and c;
     * @param position This is the position vector
     * @param motion This is the motion vector
     * @param tHit This is the tHit time
     * @return returns the point the movement vector intersects the line between a and b
     */
    public Point3D pHit(Point3D position, Vector3 motion, float tHit) {
        Point3D result = new Point3D();

        result.x = position.x + motion.x * tHit;
        result.y = 0;
        result.z = position.z + motion.z * tHit;

        return result;
    }

    @Override
    public abstract void update(float deltaTime);

    @Override
    public abstract void collision(ObjectReference or, float deltaTime);

    @Override
    public String toString() {
        return "AbstractShape{" +
                "position=" + position +
                ", color=" + color +
                '}';
    }
}
