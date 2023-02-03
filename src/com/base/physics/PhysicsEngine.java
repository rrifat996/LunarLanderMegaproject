package com.base.physics;

import java.util.Iterator;
import java.util.List;

import org.ietf.jgss.Oid;
import org.joml.Vector3f;

public class PhysicsEngine {
	private List<PhysicsObject> mObjects;
	
	
	public PhysicsEngine() {
		
	}
	public void addObject(PhysicsObject object) {
		mObjects.add(object);
	}
	public void simulate(float delta) {
		for(PhysicsObject object : mObjects) {
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
					Vector3f otherDirection =  direction.reflect(mObjects.get(i).getmVelocity().normalize());
					
					mObjects.get(i).setmVelocity(mObjects.get(i).getmVelocity().reflect(otherDirection));
					mObjects.get(j).setmVelocity(mObjects.get(j).getmVelocity().reflect(direction));
				}
			}
		}
	}
}
