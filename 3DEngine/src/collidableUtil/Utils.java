package collidableUtil;

import org.joml.Vector3f;

public class Utils {
	public static Vector3f vecMax(Vector3f first, Vector3f second) {
		Vector3f result = new Vector3f();
		result.x = first.x > second.x ? first.x : second.x;
		result.y = first.y > second.y ? first.y : second.y;
		result.z = first.z > second.z ? first.z : second.z;
		return result;
	}
	public static float max(Vector3f vec) {
		float max = vec.x;
		if(vec.y > max)
			max = vec.y;
		if(vec.z > max)
			max = vec.z;
		return max;
	}
}
