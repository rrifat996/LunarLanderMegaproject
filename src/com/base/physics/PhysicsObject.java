package com.base.physics;

import org.joml.Vector3f;

public class PhysicsObject {
	
	private Vector3f mPosition;
	private Vector3f mVelocity;
	
	
	public void integrate(float delta) {
		
	}
	public PhysicsObject(Vector3f mPosition, Vector3f mVelocity) {
		this.mPosition = mPosition;
		this.mVelocity = mVelocity;
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
