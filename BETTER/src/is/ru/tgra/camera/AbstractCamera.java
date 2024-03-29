package is.ru.tgra.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import is.ru.tgra.InputHandler;
import is.ru.tgra.Point3D;
import is.ru.tgra.Vector3Helper;
import is.ru.tgra.objects.CollidableObject;
import is.ru.tgra.objects.ObjectFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author jakob
 *         Created on 30.9.2014.
 */
public abstract class AbstractCamera implements Camera {

    public Point3D eye;
    public Vector3 u;
    public Vector3 v;
    public Vector3 n;
    protected int vertexCount = 50;
    protected List<Point3D> points = new ArrayList<Point3D>();
    public boolean hCol;
    public boolean vCol;

    protected Point3D TL;
    protected Point3D TR;
    protected Point3D BL;
    protected Point3D BR;

    protected float maxSpeed = 10.0f;
    protected float speed = 0.0f;
    protected float farPlane = 350;
    protected Vector3 direction = new Vector3();
    protected Vector3 motion = new Vector3();

    protected float left;
    protected float right;
    protected float bottom;
    protected float top;
    protected float N;
    protected float F;
    protected InputHandler inputHandler = InputHandler.getInstance();
    protected ObjectFactory objectFactory = ObjectFactory.getInstance();

    @Override
    public abstract void setup();

    public void update(float deltaTime) {

    }

    public void lookAt(Point3D pEye, Point3D pCenter, Vector3 up) {
        eye = pEye;
        n = Vector3Helper.difference(pEye, pCenter);
        n.nor();
        u = Vector3Helper.cross(up, n);
        u.nor();
        v = Vector3Helper.cross(n, u);
    }

    private float tangent(float angle) {
        return MathUtils.sin(angle * MathUtils.PI / 180.0f) / MathUtils.cos(angle * MathUtils.PI / 180.0f);
    }

    public void perspective(float angle, float ratio, float N, float F) {
        top = N * tangent(angle / 2.0f);
        bottom = -top;
        right = top * ratio;
        left = -right;

        this.N = N;
        this.F = F;
    }

    public void setModelViewMatrix() {
        Vector3 minusEye = Vector3Helper.difference(new Point3D(0, 0, 0), eye);

        float[] matrix = new float[16];
        matrix[0] = u.x;	matrix[4] = u.y;	matrix[8] = u.z;	matrix[12] = Vector3Helper.dot(minusEye, u);
        matrix[1] = v.x;	matrix[5] = v.y;	matrix[9] = v.z;	matrix[13] = Vector3Helper.dot(minusEye, v);
        matrix[2] = n.x;	matrix[6] = n.y;	matrix[10] = n.z;	matrix[14] = Vector3Helper.dot(minusEye, n);
        matrix[3] = 0;		matrix[7] = 0;		matrix[11] = 0;		matrix[15] = 1;

        Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW);
        Gdx.gl11.glLoadMatrixf(matrix, 0);
    }

    public void setProjectionMatrix() {
        float[] matrix = new float[16];
        matrix[0] = 2*N / (right-left);	matrix[4] = 0;	                matrix[8] = 0;	            matrix[12] = 0;
        matrix[1] = 0;	                matrix[5] = 2*N / (top-bottom);	matrix[9] = 0;	            matrix[13] = 0;
        matrix[2] = 0;	                matrix[6] = 0;	                matrix[10] = -(F+N)/(F-N);	matrix[14] = -(2*F*N)/(F-N);
        matrix[3] = 0;		            matrix[7] = 0;		            matrix[11] = -1;		    matrix[15] = 0;

        Gdx.gl11.glMatrixMode(GL11.GL_PROJECTION);
        Gdx.gl11.glLoadMatrixf(matrix, 0);
    }

    public void setMatrices() {
        setProjectionMatrix();
        setModelViewMatrix();
    }

    /*public void slide(float delU, float delV, float delN) {
        eye.add(Vector3Helper.scale(u, delU));
        eye.add(Vector3Helper.scale(v, delV));
        eye.add(Vector3Helper.scale(n, delN));
    }*/

    public void setDirection(Vector3 direction) {
        Vector3 newMotion = direction.nor();
        this.motion.x = newMotion.x * speed;
        this.motion.y = newMotion.y * speed;
        this.motion.z = newMotion.z * speed;
        this.direction.x = this.n.x * speed;
        this.direction.y = this.n.y * speed;
        this.direction.z = this.n.z * speed;
    }

    public void verticalCollision() {
        vCol = true;
    }

    public void horizontalCollision() {
        hCol = true;
    }

    public List<Point3D> getPoints() {
        return points;
    }

    public void slide(float deltaTime) {
        if (!vCol) {
            eye.x = eye.x + u.x * (this.motion.x * deltaTime);
            eye.x = eye.x + n.x * (this.motion.y * deltaTime);
            eye.x = eye.x + v.x * (this.motion.z * deltaTime);
        }
        if (!hCol) {
            eye.z = eye.z + u.z * (this.motion.x * deltaTime);
            eye.z = eye.z + n.z * (this.motion.y * deltaTime);
            eye.z = eye.z + v.z * (this.motion.z * deltaTime);
        }
        eye.y = eye.y + u.y * (this.motion.x * deltaTime);
        eye.y = eye.y + n.y * (this.motion.y * deltaTime);
        eye.y = eye.y + v.y * (this.motion.z * deltaTime);
        hCol = false;
        vCol = false;
    }

    public void roll(float angle) {
        float cos = MathUtils.cos(angle * MathUtils.PI / 180.0f);
        float sin = MathUtils.sin(angle * MathUtils.PI / 180.0f);

        Vector3 newU = Vector3Helper.add(Vector3Helper.scale(u, cos), Vector3Helper.scale(v, sin));
        v = Vector3Helper.add(Vector3Helper.scale(u, -sin), Vector3Helper.scale(v, cos));
        u = newU;
    }

    public void yaw(float angle) {
        float cos = MathUtils.cos(angle * MathUtils.PI / 180.0f);
        float sin = MathUtils.sin(angle * MathUtils.PI / 180.0f);

        Vector3 newN = Vector3Helper.add(Vector3Helper.scale(n, cos), Vector3Helper.scale(u, sin));
        u = Vector3Helper.add(Vector3Helper.scale(n, -sin), Vector3Helper.scale(u, cos));
        n = newN;
    }

    public void pitch(float angle) {
        float cos = MathUtils.cos(angle * MathUtils.PI / 180.0f);
        float sin = MathUtils.sin(angle * MathUtils.PI / 180.0f);

        Vector3 newV = Vector3Helper.add(Vector3Helper.scale(v, cos), Vector3Helper.scale(n, sin));
        n = Vector3Helper.add(Vector3Helper.scale(v, -sin), Vector3Helper.scale(n, cos));
        v = newV;
    }

    /**
     * Swallow the draw function.
     */
    @Override
    public void draw() {
    }
}
