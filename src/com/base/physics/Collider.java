package com.base.physics;

import org.joml.Vector3f;

public abstract class Collider {
	enum Type {
	    TYPE_SPHERE,
	    TYPE_AABB,
	    TYPE_SIZE
	  }
	private int mType;

	public abstract void transform(Vector3f translation);
	
	public Vector3f getCenter() {
		return new Vector3f();
	}
	public IntersectData intersect(Collider other) {
		if(mType == Type.TYPE_SPHERE.ordinal() && mType == other.getmType()) {
			BoundingSphere self = (BoundingSphere) this;
			return self.intersectBoundingSphere((BoundingSphere) other );
		}
		System.out.println("collision is not implemented between specified");
		return null;
	}
	public Collider(int mType) {
		this.mType = mType;
	}
	public int getmType() {
		return mType;
	}



}
