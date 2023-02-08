package physicsObjects;

import java.util.ArrayList;

import org.joml.Vector3f;

import com.base.engine.Entity;
import com.base.engine.entity.Model;
import com.base.physics.Collider;
import com.base.physics.PhysicsObject;
import com.base.physics.Plane;

public class Platform extends Entity{

	private PhysicsObject hitpoint1;
	
	public Platform(Model model, Vector3f pos, Vector3f rotation, float distance, float scale) {
		super(model, pos, rotation, scale);
		Collider plane = new Plane(new Vector3f(0,1,0), distance);
		hitpoint1 = new PhysicsObject(new Vector3f(0,0,0), plane);
	}

	@Override
	public ArrayList<PhysicsObject> getHitpoints() {
		ArrayList<PhysicsObject> list = new ArrayList<>();
		list.add(hitpoint1);
		return list;
	}

	@Override
	public void transform(Vector3f w) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public Vector3f getDir1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector3f getDir2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector3f getDir3() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector3f getDir4() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void angleTransformer(ArrayList<Integer> controlList, Vector3f rotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector3f getCalculatedAlpha() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
