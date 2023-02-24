package physicsObjects;

import java.util.ArrayList;

import org.joml.Vector3f;

import com.base.engine.Entity;
import com.base.engine.entity.Model;
import com.base.physics.BoundingSphere;
import com.base.physics.Collider;
import com.base.physics.PhysicsObject;

public class Star extends Entity{
	private PhysicsObject hitpoint1;

	public Star(Model model, Vector3f pos, Vector3f rotation, float scale) {
		super(model, pos, rotation, scale);
		Collider sphere0 = new BoundingSphere(pos, 0.0001f);
		hitpoint1 = new PhysicsObject(new Vector3f(0,0,0), sphere0);
	}

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

	@Override
	public Vector3f getCalculatedA() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
