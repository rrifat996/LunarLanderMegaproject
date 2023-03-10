package com.base.physics;

import org.joml.Vector3f;

public class Plane extends Collider{
	private final Vector3f mNormal;
	private final float mDistance;
	
	public Plane(Vector3f mNormal, float mDistance) {
		super(Collider.Type.TYPE_PLANE.ordinal());
		this.mNormal = mNormal;
		this.mDistance = mDistance;
	}
	@Override
	public void transform(Vector3f translation) {
		int x = 5;
		
	}
	
	IntersectData intersectSphere(BoundingSphere other) {
		//System.out.println(" ** "  + mNormal);
		Vector3f newNormal = new Vector3f(mNormal);
		float distanceFromSphereCenter = Math.abs(-1 * newNormal.dot(other.getmCenter()) + mDistance);
		float distanceFromSphere = distanceFromSphereCenter - other.getRadius();
		//System.out.println(distanceFromSphere);
		newNormal = new Vector3f(mNormal);
		return new IntersectData(distanceFromSphere < 0, newNormal.mul(distanceFromSphere));
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
