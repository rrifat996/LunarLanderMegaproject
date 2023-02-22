package com.base.physics;

import org.joml.Vector3f;

public class IntersectData {
	 private final boolean mDoesIntersect;
	 private Vector3f mDirection;
 	 
	public IntersectData(boolean mDoesIntersect, Vector3f mDirection) {
		this.mDoesIntersect = mDoesIntersect;
		this.mDirection = mDirection;
	}
	public boolean ismDoesIntersect() {
		return mDoesIntersect;
	}
	public float getmDistance() {
		return mDirection.length();
	}
	public Vector3f getmDirection() {
		return mDirection;
	}
	 
	 
}
