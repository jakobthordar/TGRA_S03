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
        //createWalls();
        mazeTester();
    }

    public void mazeTester() {
        MazeGenerator maze = new PrimMazeGenerator(size, size);
        maze.generate();
        int height = maze.getHeight();
        int width = maze.getWidth();
        boolean[] horizWalls = maze.getHorizWalls();
        boolean[] vertWalls = maze.getVertWalls();
        Wall wall;
        maze.print(System.out);
        for (int y = 0; y < height; y++) {
            int rowBase = y * width;
            for (int x = 0; x < width; x++) {
                if (horizWalls[rowBase + x]) {
                    //horizontal wall
                    wall = objectFactory.createWall(new Cell(x, y), new Cell(x + 1, y), this);
                    walls.add(wall);
                }
                else {
                    //not horizontal wall
                }
            }

            rowBase = y * (width + 1);
            for (int x = 0; x < width; x++) {
                if (vertWalls[rowBase + x]) {
                    wall = objectFactory.createWall(new Cell(x, y), new Cell(x, y + 1), this);
                    walls.add(wall);
                    //vertical wall
                }
                else {
                    //not vertical wall
                }
            }
        }

        /*
        int rowBase = height * width;
        for (int x = 0; x < width; x++) {
            if (horizWalls[rowBase + x]) {
                wall = objectFactory.createWall(new Cell(x, height), new Cell(x+1, height), this);
                walls.add(wall);
                //horizontall wall
            }
            else {
                //not horizontal wall
            }
        }
        */
    }

    private void createBorderWalls() {
        Wall l = objectFactory.createWall(new Cell(0, 0), new Cell(0, size), this);
        Wall r = objectFactory.createWall(new Cell(size, 0), new Cell(size, size), this);
        Wall b = objectFactory.createWall(new Cell(0, 0), new Cell(size, 0), this);
        Wall t = objectFactory.createWall(new Cell(0, size), new Cell(size, size), this);
        walls.add(l);
        walls.add(r);
        walls.add(b);
        walls.add(t);
    }

    private void createWalls() {
        Wall wall1 = objectFactory.createWall(new Cell(2, 2), new Cell(8, 2), this);
        Wall wall2 = objectFactory.createWall(new Cell(2, 4), new Cell(2, 8), this);
        Wall wall3 = objectFactory.createWall(new Cell(2, 4), new Cell(8, 4), this);
        Wall wall4 = objectFactory.createWall(new Cell(4, 6), new Cell(6, 6), this);
        Wall wall5 = objectFactory.createWall(new Cell(8, 2), new Cell(8, 8), this);
        Wall wall6 = objectFactory.createWall(new Cell(4, 6), new Cell(4, 8), this);
        Wall wall7 = objectFactory.createWall(new Cell(4, 8), new Cell(8, 8), this);
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
        objectFactory.createBox(position, axis, 0.1f, axis, Color3.wineRed);
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

    @Override
    public Point3D getPosition() {
        return position;
    }

    public Point3D getBottomLeftCorner() {
        return bottomLeftCorner;
    }
}
