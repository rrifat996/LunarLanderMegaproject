package physicsObjects;

import java.util.ArrayList;
import org.joml.Vector3f;
import com.base.engine.Entity;
import com.base.engine.entity.Model;
import com.base.physics.PhysicsObject;

public class Test extends Entity{
	public static final Vector3f xAxis = new Vector3f(-1,0,0);
	public static final Vector3f yAxis = new Vector3f(0,-1,0);
	public static final Vector3f zAxis = new Vector3f(0,0,-1);
	
	public Vector3f calculatedAlpha;
	
	public float delta = 0.05f;
	private static final float F = 0.0001f;
	
	public Vector3f dir1;
	public Vector3f dir2;
	public Vector3f dir3;
	public Vector3f dir4;
	
	public Vector3f thrust1;
	public Vector3f thrust2;
	public Vector3f thrust3;
	public Vector3f thrust4;

	public Test(Model model, Vector3f pos, Vector3f rotation, float scale) {
		super(model, pos, rotation, scale);
		this.dir1 = new Vector3f(5,0,5);
		this.dir2 = new Vector3f(5,0,-5);
		this.dir3 = new Vector3f(-5,0,5);
		this.dir4 = new Vector3f(-5,0,-5);

		
		this.thrust1 = new Vector3f(0,5,5);
		this.thrust2 = new Vector3f(-5,5,0);
		this.thrust3 = new Vector3f(0,5,-5);
		this.thrust4 = new Vector3f(5,5,0);
		
		this.calculatedAlpha = new Vector3f(0,0,0);
	}
	public void angleTransformer(ArrayList<Integer> controlList, Vector3f rotation) {
		Vector3f total = new Vector3f(rotation);

		for(int i = 0; i < controlList.size(); i++) {
			if(controlList.get(i) == 1) {
				Vector3f thrustCopy = new Vector3f(thrust1);
				Vector3f thrustCopy2 = new Vector3f(thrust1);

				Vector3f thrustingDir = new Vector3f(dir1);
				thrustingDir.cross(dir2);
				thrustingDir.div(thrustingDir.length()).mul(F);
				
				thrustCopy2.add(thrustingDir); 
				thrustCopy2.div(thrustCopy2.length()).mul(thrustCopy.length());
				
				thrustCopy2.sub(thrustCopy);
				 // xy , zaxiss
				if (thrustCopy2.y != 0)
					total.z += Math.toDegrees(Math.atan(thrustCopy2.x / thrustCopy2.y));
				if (thrustCopy2.x != 0)
					total.y += Math.toDegrees(Math.atan(thrustCopy2.z / thrustCopy2.x));
				if (thrustCopy2.z != 0)
					total.x += Math.toDegrees(Math.atan(thrustCopy2.y / thrustCopy2.z));
			}
		}
		calculatedAlpha = total;
	}
	public void transform(Vector3f w) {
		addForDirection(dir1, w);	
		addForDirection(dir2, w);

		addForDirection(dir3, w);

		addForDirection(dir4, w);

		
		addForDirection(thrust1, w);
		addForDirection(thrust2, w);
		addForDirection(thrust3, w);
		addForDirection(thrust4, w);
	}
	public void addForDirection(Vector3f dir, Vector3f w) {
		Vector3f toAdd1 = new Vector3f(dir);
		Vector3f toAdd2 = new Vector3f(dir);
		Vector3f toAdd3 = new Vector3f(dir);
		Vector3f goal = new Vector3f(0,0,0);
		
		float reflectedLen = org.joml.Math.sqrt(dir.x * dir.x + dir.y * dir.y);
		
		goal.x =  reflectedLen * org.joml.Math.sin(org.joml.Math.toRadians(w.z));
		goal.y = -1.0f * (reflectedLen - reflectedLen * org.joml.Math.cos(org.joml.Math.toRadians(w.z)));
		goal.z = 0;
		float goalLen = goal.length();
		toAdd1.cross(zAxis);
		toAdd1.div(toAdd1.length()).mul(goalLen);

		reflectedLen = org.joml.Math.sqrt(dir.x * dir.x + dir.z * dir.z);
		goal.x =  reflectedLen * org.joml.Math.sin(org.joml.Math.toRadians(w.y));
		goal.z = -1.0f * (reflectedLen - reflectedLen * org.joml.Math.cos(org.joml.Math.toRadians(w.y)));
		goal.y = 0;
		goalLen = goal.length();
		toAdd2.cross(yAxis);
		toAdd2.div(toAdd2.length()).mul(goalLen);

		reflectedLen = org.joml.Math.sqrt(dir.y * dir.y + dir.z * dir.z);
		goal.y =  reflectedLen * org.joml.Math.sin(org.joml.Math.toRadians(w.x));
		goal.z = -1.0f * (reflectedLen - reflectedLen * org.joml.Math.cos(org.joml.Math.toRadians(w.x)));
		goal.x = 0;
		goalLen = goal.length();
		toAdd3.cross(xAxis);
		toAdd3.div(toAdd3.length()).mul(goalLen);

		dir.x += toAdd1.x;
		dir.y += toAdd1.y;
		
		dir.x += toAdd2.x;
		dir.z += toAdd2.z;
		

		dir.y += toAdd3.y;
		dir.z += toAdd3.z;


		//System.out.println(toAdd1.x + " " + toAdd1.y + " " + toAdd1.z);
		//System.out.println(toAdd2.x + " " + toAdd2.y + " " + toAdd2.z);
		//System.out.println(toAdd3.x + " " + toAdd3.y + " " + toAdd3.z);

		
		dir.normalize(org.joml.Math.sqrt(50));
		
	}
	
	@Override
	public Vector3f getCalculatedAlpha() {
		return calculatedAlpha;
	}
	
	
	@Override
	public ArrayList<PhysicsObject> getHitpoints() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Vector3f getDir1() {
		// TODO Auto-generated method stub
		return dir1;
	}
	@Override
	public Vector3f getDir2() {
		// TODO Auto-generated method stub
		return dir2;
	}
	@Override
	public Vector3f getDir3() {
		// TODO Auto-generated method stub
		return dir3;
	}
	@Override
	public Vector3f getDir4() {
		// TODO Auto-generated method stub
		return dir4;
	}
	
}
