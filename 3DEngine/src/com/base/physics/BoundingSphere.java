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
		Vector3f direction = new Vector3f(other.getmCenter());
		direction.sub(mCenter);
		float centerDistance = Math.abs(direction.length());
		direction.normalize();  
		
		//System.out.println(radiusDistance);
		//System.out.println(centerDistance);
		
		return new IntersectData(centerDistance < radiusDistance, direction.mul(centerDistance));
	}
	
	
	public Vector3f getmCenter() {
		return mCenter;
	}
	public float getRadius() {
		return mRadius;
	}
	
	
	
	
}
