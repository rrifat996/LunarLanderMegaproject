package com.base.physics;

import org.joml.Vector3f;

public class BoundingSphere extends Collider{
	private final Vector3f mCenter;
	private final float mRadius;
	
	
	@Override
	public Vector3f getCenter() {
		return mCenter;
	}
	@Override
	public void transform(Vector3f translation) {
		mCenter.add(translation);
	}

	public BoundingSphere(Vector3f mCenter, float mRadius) {
		super(Collider.Type.TYPE_SPHERE.ordinal());
		this.mCenter = mCenter;
		this.mRadius = mRadius;
	}
	public IntersectData intersectBoundingSphere(BoundingSphere other) {
		float radiusDistance = mRadius + other.getRadius();
		Vector3f direction = other.getmCenter().sub(mCenter);
		float centerDistance = direction.length();
		direction.normalize();  
		
		float distance = centerDistance - radiusDistance;
		
		return new IntersectData(distance < 0, direction.mul(distance));
	}
	
	
	public Vector3f getmCenter() {
		return mCenter;
	}
	public float getRadius() {
		return mRadius;
	}
	
	
	
	
}
