package is.ru.tgra.maze;

import com.badlogic.gdx.math.Vector3;
import is.ru.tgra.Color3;
import is.ru.tgra.Point3D;
import is.ru.tgra.objects.Box;
import is.ru.tgra.objects.CollidableObject;
import is.ru.tgra.objects.ObjectFactory;
import is.ru.tgra.objects.ObjectReference;
import is.ru.tgra.player.Player;

/**
 * <h1>Wall</h1>
 * <h2>is.ru.tgra.maze</h2>
 * <p></p>
 * Created on 4.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Wall implements CollidableObject {

    private Cell startCoordinate;
    private Cell endCoordinate;
    private Point3D startPoint;
    private Point3D endPoint;
    private float length;
    private float thickness = 0.5f;
    private float tallness = 10f;
    private boolean isVertical;
    private Point3D position;
    private Box box;
    private ObjectFactory objectFactory = ObjectFactory.getInstance();

    public Wall() {
    }

    public Wall(Cell sCoord, Cell eCoord, Maze maze) {
        this.startCoordinate = sCoord;
        this.endCoordinate = eCoord;
        Point3D blc = maze.getBottomLeftCorner();
        float cellsize = maze.getCellsize();

        this.startPoint = new Point3D(blc.x + cellsize * sCoord.x, 0f, blc.z + cellsize * sCoord.y);
        this.endPoint = new Point3D(blc.x + cellsize * eCoord.x, 0f, blc.z + cellsize * eCoord.y);
        this.length = startPoint.lengthTo(endPoint);
        if (startCoordinate.x == endCoordinate.x) {
            Point3D newPos = new Point3D(startPoint.x, startPoint.y, startPoint.z + length / 2);
            position = newPos;
            isVertical = false;
            box = objectFactory.createWallBox(newPos, thickness, tallness, length + thickness, Color3.pastelRed);
        }
        if (startCoordinate.y == endCoordinate.y) {
            Point3D newPos = new Point3D(startPoint.x + length / 2, startPoint.y, startPoint.z);
            position = newPos;
            isVertical = true;
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

    @Override
    public Point3D getPosition() {
        return position;
    }

    @Override
    public void collision(ObjectReference or) {
        Player player = (Player) or;
        if(isVertical) {
            if (player.getPosition().x > position.x) {
                if ((player.getPosition().x + player.getMovement().x) < position.x) {
                    player.verticalCollision();
                    System.out.println("Vertical");
                }
            }
            if (player.getPosition().x < position.x) {
                float movX = player.getMovement().x;
                float pPos = player.getPosition().x;
                if ((pPos + movX) > position.x) {
                    player.verticalCollision();
                    System.out.println("Vertical");
                }
            }
        }
        else {
            if (player.getPosition().y > position.y) {
                if ((player.getPosition().y + player.getMovement().y) < position.y) {
                    player.horizontalCollision();
                    System.out.println("Horizontal");
                }
            }
            if (player.getPosition().y < position.y) {
                if ((player.getPosition().y + player.getMovement().y) > position.y) {
                    player.horizontalCollision();
                    System.out.println("Horizontal");
                }
            }
        }
    }
}
