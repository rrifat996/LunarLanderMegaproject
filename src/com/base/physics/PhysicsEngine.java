package com.base.physics;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

import com.base.engine.Entity;

import physicsObjects.LunarLander;

public class PhysicsEngine {
	private List<Entity> mObjects;
	private Entity centerEntity;
	private final float G = -0.0001f;
	
	
	public PhysicsEngine() {
		mObjects = new ArrayList<>();
	}
	public void addObject(Entity object) {
		mObjects.add(object);
	}
	public void setCenter(Entity object) {
		this.centerEntity = object;

	}
	public void simulate(float delta) {
		centerEntity.getV().y += G;
		
		for(Entity object : mObjects) {
			if(object.getPos().y < -12) {
				System.out.println("collision!");
				float objectX = object.getPos().x;
				float objectZ = object.getPos().z;
				float entityX = centerEntity.getPos().x;
				float entityZ = centerEntity.getPos().z;
				
				float len = (float) Math.sqrt((objectX - entityX) * (objectX - entityX) + 
						(objectZ - entityZ) * (objectZ - entityZ));
				
				//float wiLen = org.joml.Math.sqrt(centerEntity.getW().x * centerEntity.getW().x + centerEntity.getW().z * centerEntity.getW().z);
				float viLen = Math.abs(centerEntity.getV().y);
				
				Vector3f anan = new Vector3f(objectX - entityX, 0, objectZ - entityZ);
				Vector3f baban = new Vector3f(0,1,0);
				baban.cross(anan);
				baban.normalize(1);
				
				Vector3f canan = new Vector3f(centerEntity.getW().x, 0, centerEntity.getW().z);
				
				float wiLen = Math.abs(baban.dot(canan));
				
				float wiRev = 1;
				float viRev = 1;
				if (baban.dot(canan) > 0) 
					wiRev = -1;
				if(centerEntity.getV().y < 0)
					viRev = -1;
				wiLen *= wiRev;
				viLen *= viRev;
				
				float vfLen = (2 * len * wiLen - len * len * viLen - viLen) / (len * len + 1);
				float wfLen = (wiLen - wiLen * len * len + 2 * viLen * len) / (len * len + 1);
				
				baban.normalize(wiLen);
				centerEntity.getW().sub(baban);
				baban.normalize(wfLen);
				centerEntity.getW().add(baban);
				
				centerEntity.getV().y = vfLen;
			}
			    
		}
	}
	public void handleCollisions() {
		
		
		
		
		
	}
}
