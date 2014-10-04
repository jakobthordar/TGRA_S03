package is.ru.tgra;

import com.badlogic.gdx.math.Vector3;

public class Vector3Helper
{

	public static float length(Vector3 v)
	{
		return (float)Math.sqrt(v.x*v.x+v.y*v.y+v.z*v.z);
	}

	public static Vector3 difference(Point3D P1, Point3D P2)
	{
		return new Vector3(P1.x - P2.x, P1.y - P2.y, P1.z - P2.z);
	}
	
	public static float dot(Vector3 v1, Vector3 v2)
	{
		return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
	}
	
	public static Vector3 cross(Vector3 P1, Vector3 P2)
	{
		return new Vector3(P1.y*P2.z - P1.z*P2.y, P1.z*P2.x - P1.x*P2.z, P1.x*P2.y - P1.y*P2.x);
	}

    public static Vector3 scale(Vector3 v, float s) {
        return new Vector3(v.x*s, v.y*s, v.z*s);
    }

    public static Vector3 add(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.x+v2.x, v1.y+v2.y, v1.z+v2.z);
    }
}
