package is.ru.tgra.maze;

import is.ru.tgra.Color3;
import is.ru.tgra.Point3D;
import is.ru.tgra.objects.Box;
import is.ru.tgra.objects.ObjectFactory;
import is.ru.tgra.objects.ObjectReference;

/**
 * <h1>Wall</h1>
 * <h2>is.ru.tgra.maze</h2>
 * <p></p>
 * Created on 4.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Wall implements ObjectReference {

    private Coordinate startCoordinate;
    private Coordinate endCoordinate;
    private Point3D startPoint;
    private Point3D endPoint;
    private float length;
    private float thickness = 0.5f;
    private float tallness = 10f;
    private Box box;
    private ObjectFactory objectFactory = ObjectFactory.getInstance();

    public Wall() {
    }

    public Wall(Coordinate sCoord, Coordinate eCoord, Maze maze) {
        this.startCoordinate = sCoord;
        this.endCoordinate = eCoord;
        Point3D blc = maze.getBottomLeftCorner();
        float cellsize = maze.getCellsize();

        this.startPoint = new Point3D(blc.x + cellsize * sCoord.x, 0f, blc.z + cellsize * sCoord.y);
        this.endPoint = new Point3D(blc.x + cellsize * eCoord.x, 0f, blc.z + cellsize * eCoord.y);
        this.length = startPoint.lengthTo(endPoint);
        if (startCoordinate.x == endCoordinate.x) {
            Point3D newPos = new Point3D(startPoint.x, startPoint.y, startPoint.z + length / 2);
            box = objectFactory.createWallBox(newPos, thickness, tallness, length + thickness, Color3.pastelRed);
        }
        if (startCoordinate.y == endCoordinate.y) {
            Point3D newPos = new Point3D(startPoint.x + length / 2, startPoint.y, startPoint.z);
            box = objectFactory.createWallBox(newPos, length + thickness, tallness, thickness, Color3.pastelRed);
        }
    }

    public void setBox(Box box) {
        this.box = box;
    }

    @Override
    public void draw() {
        this.box.draw();
    }

    @Override
    public void update(float deltaTime) {

    }
}
