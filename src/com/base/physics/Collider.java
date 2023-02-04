package com.base.physics;

import org.joml.Vector3f;

public abstract class Collider {
	enum Type {
	    TYPE_SPHERE,
	    TYPE_AABB,
	    TYPE_PLANE
	  }
	private int mType;

	public abstract void transform(Vector3f translation);
	
	public Vector3f getCenter() {
		return new Vector3f();
	}
	public IntersectData intersect(Collider other) {
		if(mType == Type.TYPE_SPHERE.ordinal() && other.mType == Type.TYPE_SPHERE.ordinal()) {
			BoundingSphere self = (BoundingSphere) this;
			return self.intersectBoundingSphere((BoundingSphere) other );
		}
		else if(mType == Type.TYPE_SPHERE.ordinal() && other.mType == Type.TYPE_PLANE.ordinal()) {
			Plane otherOne = (Plane) other;
			return otherOne.intersectSphere((BoundingSphere)this);
		}
		else if(mType == Type.TYPE_PLANE.ordinal() && other.mType == Type.TYPE_SPHERE.ordinal()) {
			Plane thisOne = (Plane) this;
			return thisOne.intersectSphere((BoundingSphere)other );
		}
		else {
			System.out.println("collision is not implemented between specified");
			return null;
		}
	}
	public Collider(int mType) {
		this.mType = mType;
	}
	public int getmType() {
		return mType;
	}



}
