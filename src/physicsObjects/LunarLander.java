package physicsObjects;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

import com.base.engine.Entity;
import com.base.engine.entity.Model;
import com.base.physics.BoundingSphere;
import com.base.physics.Collider;
import com.base.physics.PhysicsObject;

public class LunarLander extends Entity{
	private PhysicsObject hitpointCenter;
	private PhysicsObject hitpoint1;
	private PhysicsObject hitpoint2;
	private PhysicsObject hitpoint3;
	private PhysicsObject hitpoint4;

	public LunarLander(Model model, Vector3f pos, Vector3f rotation, float scale) {
		super(model, pos, rotation, scale);
		Vector3f temp;
		Collider sphere0 = new BoundingSphere(pos, scale);
		hitpointCenter = new PhysicsObject(new Vector3f(0, 0,0), sphere0);
		
		temp = new Vector3f(pos);
		Collider sphere1 = new BoundingSphere(temp.add(new Vector3f(5,0,5)), scale);
		hitpoint1 = new PhysicsObject(new Vector3f(0,0,0), sphere1);
		
		temp = new Vector3f(pos);
		Collider sphere2 = new BoundingSphere(temp.add(new Vector3f(5,0,-5)), scale);
		hitpoint2 = new PhysicsObject(new Vector3f(0,0,0), sphere2);
		
		temp = new Vector3f(pos);
		Collider sphere3 = new BoundingSphere(temp.add(new Vector3f(-5,0,5)), scale);
		hitpoint3 = new PhysicsObject(new Vector3f(0,0,0), sphere3);
		
		temp = new Vector3f(pos);
		Collider sphere4 = new BoundingSphere(temp.add(new Vector3f(-5,0,-5)), scale);
		hitpoint4 = new PhysicsObject(new Vector3f(0,0,0), sphere4);
	}
	
	public ArrayList<PhysicsObject> getHitpoints() {
		ArrayList<PhysicsObject> list = new ArrayList<>();
		list.add(hitpointCenter);
		list.add(hitpoint1);
		list.add(hitpoint2);
		list.add(hitpoint3);
		list.add(hitpoint4);
		return list;
	}
	

	public PhysicsObject getHitpointCenter() {
		return hitpointCenter;
	}

	public void setHitpointCenter(PhysicsObject hitpointCenter) {
		this.hitpointCenter = hitpointCenter;
	}

	public PhysicsObject getHitpoint1() {
		return hitpoint1;
	}

	public void setHitpoint1(PhysicsObject hitpoint1) {
		this.hitpoint1 = hitpoint1;
	}

	public PhysicsObject getHitpoint2() {
		return hitpoint2;
	}

	public void setHitpoint2(PhysicsObject hitpoint2) {
		this.hitpoint2 = hitpoint2;
	}

	public PhysicsObject getHitpoint3() {
		return hitpoint3;
	}

	public void setHitpoint3(PhysicsObject hitpoint3) {
		this.hitpoint3 = hitpoint3;
	}

	public PhysicsObject getHitpoint4() {
		return hitpoint4;
	}

	public void setHitpoint4(PhysicsObject hitpoint4) {
		this.hitpoint4 = hitpoint4;
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
	public void transform(Vector3f w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector3f getCalculatedAlpha() {
		// TODO Auto-generated method stub
		return null;
	}



	
	
	

}
