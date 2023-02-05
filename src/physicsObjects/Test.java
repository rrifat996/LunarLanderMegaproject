package physicsObjects;

import java.awt.geom.FlatteningPathIterator;
import java.util.ArrayList;

import javax.swing.text.Position;

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
		this.dir1 = new Vector3f(pos);
		dir1.add(new Vector3f(5,0,5));
		this.dir2 = new Vector3f(pos);
		dir2.add(new Vector3f(-1,0,1));
		this.dir3 = new Vector3f(pos);
		dir3.add(new Vector3f(1,0,-1));
		this.dir4 = new Vector3f(pos);
		dir4.add(new Vector3f(-1,0,-1));
		
	}
	public void transform(Vector3f v, Vector3f w) {
		addForDirection(dir1, v, w);
		addForDirection(dir2, v, w);
		addForDirection(dir3, v, w);
		addForDirection(dir4, v, w);
		

	}
	public void addForDirection(Vector3f dir, Vector3f v, Vector3f w) {
		Vector3f toAdd1 = new Vector3f(dir);
		Vector3f toAdd2 = new Vector3f(dir);
		Vector3f toAdd3 = new Vector3f(dir);
		//
		Vector3f goal = new Vector3f(0,0,0);
		
		
		goal.x = -1.0f * (5.0f - 5.0f * org.joml.Math.cos(org.joml.Math.toRadians(w.z)));
		goal.y =  5.0f * org.joml.Math.sin(org.joml.Math.toRadians(w.z));
		goal.z = 0;
		float goalLen = goal.length();
		toAdd1.cross(zAxis);
		toAdd1.div(toAdd1.length()).mul(goalLen);
		
		goal.x =  5.0f * org.joml.Math.sin(org.joml.Math.toRadians(w.y));
		goal.z = -1.0f * (5.0f - 5.0f * org.joml.Math.cos(org.joml.Math.toRadians(w.y)));
		goal.y = 0;
		goalLen = goal.length();
		toAdd2.cross(yAxis);
		toAdd2.div(toAdd2.length()).mul(goalLen);
		
		goal.y = (5.0f - 5.0f * org.joml.Math.cos(org.joml.Math.toRadians(w.x)));
		goal.z = -1.0f * ( 5.0f * org.joml.Math.sin(org.joml.Math.toRadians(w.x)));
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
	public void setPtrs(Entity spherEntity1, Entity spherEntity2, Entity spherEntity3, Entity spherEntity4) {
		spherEntity1.setPos(dir1);
		spherEntity2.setPos(dir2);
		spherEntity3.setPos(dir3);
		spherEntity4.setPos(dir4);
		
	}

}
