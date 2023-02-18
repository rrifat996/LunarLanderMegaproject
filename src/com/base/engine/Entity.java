package com.base.engine;

import java.util.ArrayList;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import com.base.engine.entity.Model;
import com.base.physics.PhysicsObject;

public abstract class Entity {
	public static final Vector3f xAxis = new Vector3f(-1,0,0);
	public static final Vector3f yAxis = new Vector3f(0,-1,0);
	public static final Vector3f zAxis = new Vector3f(0,0,-1);
	
	private static final float F = 0.0001f;
	private static final float delta = 0.05f;
	private Model model;
	private Vector3f pos, rotation;
	private Vector3f v, w , alpha, a;
	private float scale;
	
	public Entity(Model model, Vector3f pos, Vector3f rotation, float scale) {
		this.model = model;
		this.pos = pos;
		this.rotation = rotation;
		this.scale = scale;
		this.alpha = new Vector3f(0,0,0);
		this.a = new Vector3f(0,0,0);
		this.alphaA = new Vector3f(0,0,0);
	}
	abstract public ArrayList<PhysicsObject> getHitpoints();
	
	abstract public void angleTransformer(ArrayList<Integer> controlList, Vector3f rotation);
	abstract public Vector3f getCalculatedAlpha();
	abstract public Vector3f getCalculatedA();
	
	public void transformation() {
		Vector3f toAdd3 = new Vector3f(a);
		Vector3f toAdd4 = new Vector3f(alpha);
		
		toAdd3.mul(delta);
		
		v.add(toAdd3);
		w.add(toAdd4);
		
		Vector3f toAdd1 = new Vector3f(v);
		pos.add(toAdd1);
		
		rotation.add(w);
		
		transform(getRotation());
	}
	

	abstract public void transform(Vector3f rotation);
	
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
	public Vector3f getAlpha() {
		return alpha;
	}
	public Vector3f getA() {
		return a;
	}
	public void setAlpha(float x, float y, float z) {
		this.alpha.x = x;
		this.alpha.y = y;
		this.alpha.z = z;
	}
	
	public void setAlpha(Vector3f alpha) {
		this.alpha = alpha;
	}
	public void setA(float x, float y, float z) {
		this.a.x = x;
		this.a.y = y;
		this.a.z = z;
	}
	public void setA(Vector3f a) {
		this.a = a;
	}
	public void setW(float x, float y, float z) {
		this.w.x = x;
		this.w.y = y;
		this.w.z = z;
	}
}
