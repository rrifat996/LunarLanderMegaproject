package physicsObjects;

import java.util.ArrayList;

import org.joml.Matrix3d;
import org.joml.Matrix3dc;
import org.joml.Matrix3f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.vulkan.AMDTextureGatherBiasLod;

import com.base.engine.Entity;
import com.base.engine.entity.Model;
import com.base.physics.PhysicsObject;

public class Test extends Entity{
	public static final Vector3f xAxis = new Vector3f(-1,0,0);
	public static final Vector3f yAxis = new Vector3f(0,-1,0);
	public static final Vector3f zAxis = new Vector3f(0,0,-1);
	
	public Vector3f calculatedAlpha;
	public Vector3f calculatedA;
	
	public float delta = 0.05f;
	private static final float F = 0.0001f;
	
	public Vector3f dir1;
	public Vector3f dir2;
	public Vector3f dir3;
	public Vector3f dir4;
	
	public Vector3f thrust1;
	public Vector3f thrust2;
	public Vector3f thrust3;
	public Vector3f thrust4;

	public Test(Model model, Vector3f pos, Vector3f rotation, float scale) {
		super(model, pos, rotation, scale);
		this.dir1 = new Vector3f(5,-5,5);
		this.dir2 = new Vector3f(5,-5,-5);
		this.dir3 = new Vector3f(-5,-5,-5);
		this.dir4 = new Vector3f(-5,-5,5);

		
		this.thrust1 = new Vector3f(0,5,5);
		this.thrust2 = new Vector3f(5,5,0);
		this.thrust3 = new Vector3f(0,5,-5);
		this.thrust4 = new Vector3f(-5,5,0);
		
		this.calculatedAlpha = new Vector3f(0,0,0);
		this.calculatedA = new Vector3f(0,0,0);
	}
	public void angleTransformer(ArrayList<Integer> controlList, Vector3f rotation) {
		Vector3f total = new Vector3f(0,0,0);
		Vector3f total2 = new Vector3f(0,0,0);

		for(int i = 0; i < controlList.size(); i++) {
			Vector3f thrustingDir = new Vector3f(0,0,0);
			
			if(controlList.get(i) % 6 == 0) {
				thrustingDir = new Vector3f(dir1);
				thrustingDir.add(dir3);
				thrustingDir.mul(-1);
				total2.add(thrustingDir.normalize(0.05f));
				System.out.println(thrustingDir);}
			else if(controlList.get(i) % 6 == 1) {
				thrustingDir = new Vector3f(dir1);
				thrustingDir.add(dir3);
				total2.add(thrustingDir.normalize(0.05f));}
			else if(controlList.get(i) % 6 == 2) {
				thrustingDir = new Vector3f(dir2);
				thrustingDir.sub(dir3);
				total2.add(thrustingDir.normalize(0.05f));}
			else if(controlList.get(i) % 6 == 3) {
				thrustingDir = new Vector3f(dir2);
				thrustingDir.sub(dir3);
				thrustingDir.mul(-1);
				total2.add(thrustingDir.normalize(0.05f));}
			else if(controlList.get(i) % 6 == 4) {
				thrustingDir = new Vector3f(dir2);
				thrustingDir.sub(dir1);
				total2.add(thrustingDir.normalize(0.05f));}
			else if(controlList.get(i) % 6 == 5) {
				thrustingDir = new Vector3f(dir2);
				thrustingDir.sub(dir1);
				thrustingDir.mul(-1);
				total2.add(thrustingDir.normalize(0.05f));}
			// rotations
			Vector3f thrustPtr = new Vector3f(0,0,0);
			
			if(controlList.get(i) / 6 == 0)		thrustPtr = new Vector3f(thrust1);
			else if(controlList.get(i) / 6 == 1)thrustPtr = new Vector3f(thrust2);
			else if(controlList.get(i) / 6 == 2)thrustPtr = new Vector3f(thrust3);
			else if(controlList.get(i) / 6 == 3)thrustPtr = new Vector3f(thrust4);
		
			
			thrustPtr.cross(thrustingDir).normalize(0.05f); // random val

			total.add(thrustPtr);
		}
		calculatedAlpha = total;
		calculatedA = total2;
	}
	public void transform(Vector3f totalRotation) {
		addForDirection(dir1, 5.23f,-6.5f,5.23f, totalRotation);	
		addForDirection(dir2, 5.23f,-6.5f,-5.23f, totalRotation);
		addForDirection(dir3, -5.23f,-6.5f,-5.23f, totalRotation);
		addForDirection(dir4, -5.23f,-6.5f,5.23f, totalRotation);
		
		addForDirection(thrust1, 0,1.5f,4.5f, totalRotation);	
		addForDirection(thrust2, 4.5f,1.5f,0, totalRotation);
		addForDirection(thrust3, 0,1.5f,-4.5f, totalRotation);
		addForDirection(thrust4, -4.5f,1.5f,0, totalRotation);
	}
	public void addForDirection(Vector3f dir, float basisX, float basisY ,float basisZ, Vector3f totalRotation) {
		float x = basisX;
		float y = basisY;
		float z = basisZ;
		float newX = x * (float) Math.cos(Math.toRadians(totalRotation.z)) - y * (float) Math.sin(Math.toRadians(totalRotation.z));
		float newY = x * (float) Math.sin(Math.toRadians(totalRotation.z)) + y * (float) Math.cos(Math.toRadians(totalRotation.z));
		float newZ = z;
		x = newX;
		y = newY;
		z = newZ;
		newX = x * (float) Math.cos(Math.toRadians(totalRotation.y)) + z * (float) Math.sin(Math.toRadians(totalRotation.y));
		newY = y;
		newZ = -1 * x * (float) Math.sin(Math.toRadians(totalRotation.y)) + z * (float) Math.cos(Math.toRadians(totalRotation.y));
		x = newX;
		y = newY;
		z = newZ;
		newX = x;
		newY = y * (float) Math.cos(Math.toRadians(totalRotation.x)) - z * (float) Math.sin(Math.toRadians(totalRotation.x));
		newZ = y * (float) Math.sin(Math.toRadians(totalRotation.x)) + z * (float) Math.cos(Math.toRadians(totalRotation.x));
		dir.x = newX;
		dir.y = newY;
		dir.z = newZ;
	}
	
	@Override
	public Vector3f getCalculatedAlpha() {
		return calculatedAlpha;
	}
	
	
	@Override
	public ArrayList<PhysicsObject> getHitpoints() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Vector3f getDir1() {
		// TODO Auto-generated method stub
		return dir1;
	}
	@Override
	public Vector3f getDir2() {
		// TODO Auto-generated method stub
		return dir2;
	}
	@Override
	public Vector3f getDir3() {
		// TODO Auto-generated method stub
		return dir3;
	}
	@Override
	public Vector3f getDir4() {
		// TODO Auto-generated method stub
		return dir4;
	}
	@Override
	public Vector3f getCalculatedA() {
		return calculatedA;
	}
	
}
