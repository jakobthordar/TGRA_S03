package is.ru.tgra.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import is.ru.tgra.InputHandler;
import is.ru.tgra.Point3D;
import is.ru.tgra.Vector3Helper;

public class FirstPersonCamera extends AbstractCamera
{
	public Point3D eye;
	public Vector3 u;
    public Vector3 v;
    public Vector3 n;

	public float left;
	public float right;
	public float bottom;
	public float top;
	public float N;
	public float F;

    public InputHandler inputHandler = InputHandler.getInstance();

	public FirstPersonCamera() {
	}

	public void lookAt(Point3D pEye, Point3D pCenter, Vector3 up) {
		eye = pEye;
		n = Vector3Helper.difference(pEye, pCenter);
		n.nor();
		u = up.crs(n);
		u.nor();
		v = n.crs(u);
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
		matrix[0] = 2*N / (right-left);	matrix[4] = 0;	matrix[8] = 0;	matrix[12] = 0;
		matrix[1] = 0;	matrix[5] = 2*N / (top-bottom);	matrix[9] = 0;	matrix[13] = 0;
		matrix[2] = 0;	matrix[6] = 0;	matrix[10] = -(F+N)/(F-N);	matrix[14] = -(2*F*N)/(F-N);
		matrix[3] = 0;		matrix[7] = 0;		matrix[11] = -1;		matrix[15] = 0;

		Gdx.gl11.glMatrixMode(GL11.GL_PROJECTION);
		Gdx.gl11.glLoadMatrixf(matrix, 0);
	}
	
	public void setMatrices() {
		setProjectionMatrix();
		setModelViewMatrix();
	}
	
	public void slide(float delU, float delV, float delN) {
        eye.add(Vector3Helper.scale(u, delU));
        eye.add(Vector3Helper.scale(v, delV));
        eye.add(Vector3Helper.scale(n, delN));
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
		
		Vector3 newN = n.mul(cos).add(u.mul(sin));
		u = n.mul(-sin).add(u.mul(cos));
		n = newN;
	}

	public void pitch(float angle) {
		float cos = MathUtils.cos(angle * MathUtils.PI / 180.0f);
		float sin = MathUtils.sin(angle * MathUtils.PI / 180.0f);
		
		Vector3 newV = v.mul(cos).add(n.mul(sin));
		n = v.mul(-sin).add(n.mul(cos));
		v = newV;
	}

    @Override
    public void setup() {

    }

    @Override
    public void update(float deltaTime) {

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.roll(90.0f * deltaTime);
            System.out.println("A");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.roll(-90.0f * deltaTime);
            System.out.println("D");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.pitch(-90.0f * deltaTime);
            System.out.println("W");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.pitch(90.0f * deltaTime);
            System.out.println("S");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.I)) {
            this.slide(0,0, -5.0f * deltaTime);
            System.out.println("I");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.K)) {
            this.slide(0,0, 5.0f * deltaTime);
            System.out.println("K");
        }
    }
}
