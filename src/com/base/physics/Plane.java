package com.base.physics;

import org.joml.Vector3f;

public class Plane {
	private final Vector3f mNormal;
	private final float mDistance;
	
	public Plane(Vector3f mNormal, float mDistance) {
		this.mNormal = mNormal;
		this.mDistance = mDistance;
	}
	IntersectData intersectSphere(BoundingSphere other) {
		float distanceFromSphereCenter = Math.abs(mNormal.dot(other.getmCenter()) + mDistance);
		float distanceFromSphere = distanceFromSphereCenter - other.getRadius();
		
		return new IntersectData(distanceFromSphere < 0, distanceFromSphere	);
	}
	public Plane Normalized() {
		float magnitude = mNormal.length();
		
		return new Plane(mNormal.div(magnitude), mDistance / magnitude);
	}
	public Vector3f getmNormal() {
		return mNormal;
	}
	public float getmDistance() {
		return mDistance;
	}
}
