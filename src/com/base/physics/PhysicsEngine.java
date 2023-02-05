package com.base.physics;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

import physicsObjects.LunarLander;

public class PhysicsEngine {
	private LunarLander lunarLander;
	private List<PhysicsObject> mObjects;
	private final float G = -0.05f;
	
	
	public PhysicsEngine() {
		mObjects = new ArrayList<>();
	}
	public void addObject(PhysicsObject object) {
		mObjects.add(object);
	}
	public void simulate(float delta) {
		/*System.out.println(mObjects.get(0).getmPosition().y);
		System.out.println(mObjects.get(1).getmVelocity());
		System.out.println(mObjects.get(2).getmVelocity());
		System.out.println(mObjects.get(3).getmVelocity());
		System.out.println(mObjects.get(4).getmVelocity());
		System.out.println(mObjects.get(5).getmVelocity());*/
		for(PhysicsObject object : mObjects) {
			if(object.getCollider().getmType() == Collider.Type.TYPE_SPHERE.ordinal())
				object.getmVelocity().y += G;
				object.integrate(delta);
			    
		}
	}
	public void setLunarLander(LunarLander ll) {
		this.lunarLander = ll;
	}
	public void handleCollisions() {
		IntersectData intersectData = mObjects.get(0).getCollider()
				.intersect(mObjects.get(5).getCollider());
		if (intersectData.ismDoesIntersect()) {
			
			
			Vector3f direction = intersectData.getmDirection().normalize();
			
		} 
		
		
		
		
	}
}
