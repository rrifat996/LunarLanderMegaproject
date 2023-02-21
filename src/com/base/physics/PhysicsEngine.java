package com.base.physics;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.IsoCountryCode;

import org.joml.Vector3f;

import com.base.engine.Entity;

import physicsObjects.LunarLander;

public class PhysicsEngine {
	private List<Entity> mObjects;
	private Entity centerEntity;
	private final float G = -0.0001f;
	int counter = 20;
	
	
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
		//centerEntity.getV().y += G;
		
		boolean waitNext = false;
		
		for(Entity object : mObjects) {
			if(object.getPos().y < -12 && !waitNext) {
				waitNext = true;
				System.out.println("collision!");
				System.out.println(centerEntity.getPos());
				float objectX = object.getPos().x;
				float objectZ = object.getPos().z;
				float entityX = centerEntity.getPos().x;
				float entityZ = centerEntity.getPos().z;
				
				float len = (float) Math.sqrt((objectX - entityX) * (objectX - entityX) + 
						(objectZ - entityZ) * (objectZ - entityZ));
				System.out.println("len :" +  len);
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

				baban.normalize(Math.abs(wiLen));
				baban.mul(wiRev);
				//sub?
				centerEntity.getW().sub(baban);
				
				baban = new Vector3f(0,1,0);
				baban.cross(anan);
				baban.normalize(1);
				
				baban.normalize(Math.abs(wfLen));
				if(wfLen < 0)
					baban.mul(-1);
				centerEntity.getW().add(baban);
				centerEntity.getV().y = vfLen;
				
				centerEntity.getPos().y += 1;
				
				System.out.println("V: " + centerEntity.getV());
				System.out.println("W: " + centerEntity.getW().x + "," + centerEntity.getW().z);
				
				System.out.println(counter);

				
				centerEntity.getPos().x = 0;
				centerEntity.getPos().y = 0;
				centerEntity.getPos().z = 0;
				
				centerEntity.getRotation().x = 20;
				centerEntity.getRotation().y = counter;
				centerEntity.getRotation().z = 0;
				
				counter += 20;
				
				centerEntity.setV(new Vector3f(0,0,0));
				centerEntity.setW(new Vector3f(0,0,0));
				

			}
			    
		}
	}
	public void handleCollisions() {
		
		
		
		
		
	}
}
