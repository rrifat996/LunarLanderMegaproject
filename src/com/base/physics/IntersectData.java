package com.base.physics;

public class IntersectData {
	 private final boolean mDoesIntersect;
	 private final float mDistance;
	 
	public IntersectData(boolean mDoesIntersect, float mDistance) {
		this.mDoesIntersect = mDoesIntersect;
		this.mDistance = mDistance;
	}
	public boolean ismDoesIntersect() {
		return mDoesIntersect;
	}
	public float getmDistance() {
		return mDistance;
	}
	 
	 
}
