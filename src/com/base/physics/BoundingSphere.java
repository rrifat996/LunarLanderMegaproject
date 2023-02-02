package com.base.physics;

import org.joml.Vector3f;

public class BoundingSphere {
	private final Vector3f mCenter;
	private final float mRadius;

	public BoundingSphere(Vector3f mCenter, float mRadius) {
		this.mCenter = mCenter;
		this.mRadius = mRadius;
	}
	public IntersectData intersectBoundingSphere(BoundingSphere other) {
		float radiusDistance = mRadius + other.getRadius();
		float centerDistance = other.getmCenter().sub(mCenter).length();
		
		if(centerDistance < radiusDistance) 
			return new IntersectData(true, centerDistance - radiusDistance);
		else {
			return new IntersectData(false, centerDistance - radiusDistance);
		}
	}
	
	
	public Vector3f getmCenter() {
		return mCenter;
	}
	public float getRadius() {
		return mRadius;
	}
	
	
	
}
