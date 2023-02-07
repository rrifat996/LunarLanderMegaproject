package com.base.engine;

import java.util.ArrayList;

import org.joml.Vector3f;

import com.base.engine.entity.Model;
import com.base.physics.PhysicsObject;

public abstract class Entity {
	public static final float delta = 0.05f;
	private Model model;
	private Vector3f pos, rotation;
	private Vector3f v, w;
	private float scale;
	
	public Entity(Model model, Vector3f pos, Vector3f rotation, float scale) {
		this.model = model;
		this.pos = pos;
		this.rotation = rotation;
		this.scale = scale;
	}
	abstract public ArrayList<PhysicsObject> getHitpoints();
	
	public void transformation() {
		Vector3f toAdd1 = new Vector3f(v);
		pos.add(toAdd1);
		Vector3f toAdd2 = new Vector3f(w);
		rotation.add(toAdd2);
		transform(v, w);
	}
	
	abstract public void transform(Vector3f v, Vector3f w);
	
	abstract public Vector3f getDir1();
	abstract public Vector3f getDir2();
	abstract public Vector3f getDir3();
	abstract public Vector3f getDir4();
	
	public void incPos(float x, float y, float z) {
		this.pos.x += x;
		this.pos.y += y;
		this.pos.z += z;
		
	}
	public void setPos(Vector3f pos) {
		this.pos = pos;
	}
	public void incRotation(float x, float y, float z) {
		this.rotation.x += x;
		this.rotation.y += y;
		this.rotation.z += z;
		
	}
	public void setRotation(float x, float y, float z) {
		this.rotation.x = x;
		this.rotation.y = y;
		this.rotation.z = z;
	}
	
	public Model getModel() {
		return model;
	}
	public Vector3f getPos() {
		return pos;
	}
	public Vector3f getRotation() {
		return rotation;
	}
	public float getScale() {
		return scale;
	}
	public Vector3f getV() {
		return v;
	}
	public void setV(Vector3f v) {
		this.v = v;
	}
	public Vector3f getW() {
		return w;
	}
	public void setW(Vector3f w) {
		this.w = w;
	}
	
	
	
}
