package is.ru.tgra.maze;

import is.ru.tgra.Color3;
import is.ru.tgra.Point3D;
import is.ru.tgra.objects.ObjectFactory;
import is.ru.tgra.objects.ObjectReference;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Maze</h1>
 * <h2>is.ru.tgra.maze</h2>
 * <p></p>
 * Created on 4.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Maze implements ObjectReference {

    private int size;
    private float cellsize;
    private Point3D position;
    private float axis;
    private Point3D bottomLeftCorner;
    private List<Wall> walls = new ArrayList<Wall>();
    private ObjectFactory objectFactory = ObjectFactory.getInstance();

    public Maze() {
    }

    public Maze(int size, int cellsize) {
        if (size < 0)
            size = 0;
        if (cellsize < 0)
            cellsize = 0;
        this.position = new Point3D(0, -1.0f, 0);
        this.size = size;
        this.cellsize = cellsize;
        this.axis = size * cellsize;
        this.bottomLeftCorner = new Point3D(position.x - axis / 2, position.y, position.z - axis / 2);
        makeMaze();
    }

    public void makeMaze() {
        createFloor();
        createBorderWalls();
        createWalls();
    }

    private void createBorderWalls() {
        Wall l = new Wall(new Coordinate(0, 0), new Coordinate(0, size), this);
        Wall r = new Wall(new Coordinate(size, 0), new Coordinate(size, size), this);
        Wall b = new Wall(new Coordinate(0, 0), new Coordinate(size, 0), this);
        Wall t = new Wall(new Coordinate(0, size), new Coordinate(size, size), this);
        walls.add(l);
        walls.add(r);
        walls.add(b);
        walls.add(t);
    }

    private void createWalls() {
        Wall wall1 = new Wall(new Coordinate(2, 2), new Coordinate(8, 2), this);
        Wall wall2 = new Wall(new Coordinate(2, 4), new Coordinate(2, 8), this);
        Wall wall3 = new Wall(new Coordinate(2, 4), new Coordinate(8, 4), this);
        Wall wall4 = new Wall(new Coordinate(4, 6), new Coordinate(6, 6), this);
        Wall wall5 = new Wall(new Coordinate(8, 2), new Coordinate(8, 8), this);
        Wall wall6 = new Wall(new Coordinate(4, 6), new Coordinate(4, 8), this);
        Wall wall7 = new Wall(new Coordinate(4, 8), new Coordinate(8, 8), this);
        //Wall wall8 = new Wall(new Coordinate(0, size), new Coordinate(size, size), this);
        walls.add(wall1);
        walls.add(wall2);
        walls.add(wall3);
        walls.add(wall4);
        walls.add(wall5);
        walls.add(wall6);
        walls.add(wall7);
        //walls.add(wall8);
    }

    private void createFloor() {
        objectFactory.createBox(position, axis, 0.1f, axis, Color3.pastelBlue);
    }

    public int getSize() {
        return size;
    }

    public float getCellsize() {
        return cellsize;
    }

    @Override
    public void draw() {
        for (Wall w : walls) {
            w.draw();
        }
    }

    @Override
    public void update(float deltaTime) {

    }

    public Point3D getBottomLeftCorner() {
        return bottomLeftCorner;
    }
}
