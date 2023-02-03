package com.base.physics;

import org.joml.Vector3f;

public class PhysicsObject {
	
	private Vector3f mPosition;
	private Vector3f mVelocity;
	private Collider mCollider;
	private Vector3f mOldPosition;
	
	public PhysicsObject(Vector3f mVelocity, Collider collider) {
		this.mPosition = collider.getCenter(); 
		this.mOldPosition = collider.getCenter();
		this.mVelocity = mVelocity;
		this.mCollider = collider;
	}
	
	public Collider getCollider() {
		Vector3f translation = mPosition.sub(mOldPosition);
		mOldPosition = mPosition;
		
		mCollider.transform(translation);
		return mCollider;

	}
	public void integrate(float delta) {
		 mPosition.add(mVelocity.mul(delta));
	}
	
	public Vector3f getmPosition() {
		return mPosition;
	}
	public void setmPosition(Vector3f mPosition) {
		this.mPosition = mPosition;
	}
	public Vector3f getmVelocity() {
		return mVelocity;
	}
	public void setmVelocity(Vector3f mVelocity) {
		this.mVelocity = mVelocity;
	}
	
	
	
}
