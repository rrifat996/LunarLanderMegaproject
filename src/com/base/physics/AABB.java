package com.base.physics;

import org.joml.Vector3f;

import collidableUtil.Utils;


public class AABB {
	private final Vector3f mMinExtents;  
	private final Vector3f mMaxExtents;
	
	public IntersectData intersectAABB(AABB other) {
		Vector3f distance1 = other.getmMinExtents().sub(mMaxExtents);
		Vector3f distance2 = mMinExtents.sub(other.getmMaxExtents());
		Vector3f distance = Utils.vecMax(distance1, distance2);
		float maxDistance = Utils.max(distance);
		
		return new IntersectData(maxDistance < 0, distance);
	}
	
	public AABB(Vector3f mMinExtents, Vector3f mMaxExtents) {
		this.mMinExtents = mMinExtents;
		this.mMaxExtents = mMaxExtents;
	}
	public Vector3f getmMinExtents() {
		return mMinExtents;
	}
	public Vector3f getmMaxExtents() {
		return mMaxExtents;
	}  
	
	
}
