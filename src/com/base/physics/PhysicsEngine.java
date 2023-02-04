package com.base.physics;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

public class PhysicsEngine {
	private List<PhysicsObject> mObjects;
	private final float G = -2f;
	
	
	public PhysicsEngine() {
		mObjects = new ArrayList<>();
	}
	public void addObject(PhysicsObject object) {
		mObjects.add(object);
	}
	public void simulate(float delta) {
		System.out.println(mObjects.get(0).getmVelocity());
		System.out.println(mObjects.get(1).getmVelocity());
		System.out.println(mObjects.get(2).getmVelocity());
		System.out.println(mObjects.get(3).getmVelocity());
		System.out.println(mObjects.get(4).getmVelocity());
		System.out.println(mObjects.get(5).getmVelocity());
		for(PhysicsObject object : mObjects) {
			if(object.getCollider().getmType() == Collider.Type.TYPE_SPHERE.ordinal())
				object.getmVelocity().y += G;
			object.integrate(delta);    
		}
	}
	public void handleCollisions() {
		for(int i = 0; i < mObjects.size(); i++) {
			for(int j = i + 1; j < mObjects.size(); j++) {
				IntersectData intersectData = mObjects.get(i).getCollider()
						.intersect(mObjects.get(j).getCollider());
				if (intersectData.ismDoesIntersect()) {
					Vector3f direction = intersectData.getmDirection().normalize();
					
					mObjects.get(j).setmVelocity(mObjects.get(j).getmVelocity().reflect(direction));
				}
			}
		}
	}
}
