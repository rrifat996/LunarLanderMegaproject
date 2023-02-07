package physicsObjects;

import java.awt.geom.FlatteningPathIterator;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory;

import org.joml.Vector3f;
import com.base.engine.Entity;
import com.base.engine.entity.Model;
import com.base.physics.PhysicsObject;

public class Test extends Entity{
	public static final Vector3f xAxis = new Vector3f(-1,0,0);
	public static final Vector3f yAxis = new Vector3f(0,-1,0);
	public static final Vector3f zAxis = new Vector3f(0,0,-1);
	
	public float delta = 0.05f;
	
	int degrees =0;
	
	public Vector3f dir1;
	public Vector3f dir2;
	public Vector3f dir3;
	public Vector3f dir4;

	public Test(Model model, Vector3f pos, Vector3f rotation, float scale) {
		super(model, pos, rotation, scale);
		this.dir1 = new Vector3f(5,0,5);
		this.dir2 = new Vector3f(5,0,-5);
		this.dir3 = new Vector3f(-5,0,5);
		this.dir4 = new Vector3f(-5,0,-5);
		
	}
	public void transform(Vector3f v, Vector3f w) {
		
		addForDirection(dir1, w);
		addForDirection(dir2, w);
		addForDirection(dir3, w);
		addForDirection(dir4, w);
		
	}
	public void addForDirection(Vector3f dir, Vector3f w) {
		Vector3f toAdd1 = new Vector3f(dir);
		Vector3f toAdd2 = new Vector3f(dir);
		Vector3f toAdd3 = new Vector3f(dir);
		//
		Vector3f goal = new Vector3f(0,0,0);
		
		
		float reflectedLen = dir.z;

		goal.x = -1.0f * (reflectedLen - reflectedLen * org.joml.Math.cos(org.joml.Math.toRadians(w.z)));
		goal.y =  reflectedLen * org.joml.Math.sin(org.joml.Math.toRadians(w.z));
		goal.z = 0;
		float goalLen = goal.length();
		toAdd1.cross(zAxis);
		toAdd1.div(toAdd1.length()).mul(goalLen);
		
		reflectedLen = dir.y;
		
		goal.x =  reflectedLen * org.joml.Math.sin(org.joml.Math.toRadians(w.y));
		goal.z = -1.0f * (reflectedLen - reflectedLen * org.joml.Math.cos(org.joml.Math.toRadians(w.y)));
		goal.y = 0;
		goalLen = goal.length();
		toAdd2.cross(yAxis);
		toAdd2.div(toAdd2.length()).mul(goalLen);
		
		reflectedLen = dir.x;
		
		goal.y = (reflectedLen - reflectedLen * org.joml.Math.cos(org.joml.Math.toRadians(w.x)));
		goal.z = -1.0f * ( reflectedLen * org.joml.Math.sin(org.joml.Math.toRadians(w.x)));
		goal.x = 0;
		goalLen = goal.length();
		toAdd3.cross(xAxis);
		toAdd3.div(toAdd3.length()).mul(goalLen);
		
		dir.add(toAdd1);
		dir.add(toAdd2);
		dir.add(toAdd3);
		
		dir.div(dir.length()).mul((float)Math.sqrt(50f));

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
