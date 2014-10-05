package is.ru.tgra.objects;

import is.ru.tgra.Color3;
import is.ru.tgra.Point3D;
import is.ru.tgra.camera.Camera;
import is.ru.tgra.camera.FirstPersonCamera;
import is.ru.tgra.camera.TopDownCamera;
import is.ru.tgra.maze.Cell;
import is.ru.tgra.maze.Maze;
import is.ru.tgra.maze.Wall;
import is.ru.tgra.player.Player;

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

    protected static FirstPersonCamera camFirstPerson;
    protected static TopDownCamera topDownCamera;
    protected static Arrow arrow;

    protected static Player player;

    protected static Maze maze;

    protected static List<ObjectReference> objects = new ArrayList<ObjectReference>();
    protected static List<CollidableObject> collidableObjects = new ArrayList<CollidableObject>();
    protected static List<Camera> cameras = new ArrayList<Camera>();
    protected static List<Pyramid> pyramids = new ArrayList<Pyramid>();

    private ObjectFactory() {
    }

    public static ObjectFactory getInstance() {
        return instance;
    }

    public Box createBox(Point3D position, float xSize, float ySize, float zSize, Color3 color) {
        Box box = new Box(position, xSize, ySize, zSize, color);
        objects.add(box);
        return box;
    }

    public Box createWallBox(Point3D position, float xSize, float ySize, float zSize, Color3 color) {
        Box box = new Box(position, xSize, ySize, zSize, color);
        return box;
    }

    public Wall createWall(Cell cell1, Cell cell2, Maze maze) {
        Wall wall = new Wall(cell1, cell2, maze);
        collidableObjects.add(wall);
        return wall;
    }

    public void createPlayer() {
        FirstPersonCamera fpc = this.createFirstPersonCamera();
        TopDownCamera tdc = this.createTopDownCamera();
        Arrow arrow = this.createArrow();
        Player newPlayer = new Player();
        newPlayer.setFirstPersonCamera(fpc);
        newPlayer.setTopDownCamera(tdc);
        newPlayer.setArrow(arrow);
        player = newPlayer;
        objects.add(player);
    }

    public Arrow createArrow() {
        Arrow arrow = new Arrow();
        return arrow;
    }

    public void createMaze(int size, int cellsize) {
        maze = new Maze(size, cellsize);
        objects.add(maze);
    }

    public Maze getMaze() {
        return maze;
    }

    public Player getPlayer() {
        return player;
    }

    public Box createPyramidBox(Point3D position, float floorSize, float boxHeight, Color3 color) {
        Box box = new Box(position, floorSize, boxHeight, floorSize, color);
        return box;
    }

    public List<CollidableObject> getCollidableObjects() {
        return collidableObjects;
    }

    public Pyramid createPyramid() {
        Pyramid pyramid = new Pyramid();
        objects.add(pyramid);
        pyramids.add(pyramid);
        collidableObjects.add(pyramid);
        return pyramid;
    }

    public Pyramid createPyramid(int levels, Point3D position, float pyramidRotate) {
        Pyramid pyramid = new Pyramid(levels, position, pyramidRotate);
        objects.add(pyramid);
        pyramids.add(pyramid);
        return pyramid;
    }

    private FirstPersonCamera createFirstPersonCamera() {
        camFirstPerson = new FirstPersonCamera();
        //objects.add(camFirstPerson);
        cameras.add(camFirstPerson);
        return camFirstPerson;
    }

    private TopDownCamera createTopDownCamera() {
        topDownCamera = new TopDownCamera();
        //objects.add(topDownCamera);
        cameras.add(topDownCamera);
        return topDownCamera;
    }

    public FirstPersonCamera getCamFirstPerson() {
        return camFirstPerson;
    }

    public TopDownCamera getTopDownCamera() {
        return topDownCamera;
    }

    public List<Camera> getCameras() {
        return cameras;
    }

    public List<ObjectReference> getObjects() {
        return objects;
    }
}
