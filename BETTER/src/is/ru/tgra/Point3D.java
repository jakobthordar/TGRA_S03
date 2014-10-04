package is.ru.tgra;

import com.badlogic.gdx.math.Vector3;

public class Point3D
{
	public float x;
	public float y;
	public float z;
	
	public Point3D(float xx, float yy, float zz)
	{
		x = xx;
		y = yy;
		z = zz;
	}
	
	public void set(float xx, float yy, float zz)
	{
		x = xx;
		y = yy;
		z = zz;
	}

    public void add(Vector3 v) {
        x = x + v.x;
        y = y + v.y;
        z = z + v.z;
    }
}
