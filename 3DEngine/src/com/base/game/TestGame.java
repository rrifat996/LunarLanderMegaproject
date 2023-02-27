package com.base.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;import javax.swing.LayoutStyle.ComponentPlacement;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.sampling.BestCandidateSampling.Cube;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.vulkan.AMDTextureGatherBiasLod;

import com.base.engine.Camera;
import com.base.engine.Entity;
import com.base.engine.ILogic;
import com.base.engine.MouseInput;
import com.base.engine.ObjectLoader;
import com.base.engine.WindowManager;
import com.base.engine.entity.Model;
import com.base.engine.entity.Texture;
import com.base.engine.entity.terrain.Terrain;
import com.base.engine.rendering.RenderManager;
import com.base.engine.utils.Consts;
import com.base.physics.PhysicsEngine;
import com.base.physics.PhysicsObject;

import physicsObjects.Platform;
import physicsObjects.Star;
import physicsObjects.Test;

public class TestGame implements ILogic{
	private static final float CAMERA_MOVEMENT_SPEED = 0.2f;
	
	private final RenderManager renderer;
	private final ObjectLoader loader;
	private final WindowManager window;
	private final PhysicsEngine physEngine;
	Model landModel;
	
	private Entity centerEntity ;

	private int updateCounter = 0;
	
	private float prevDist = 1000;
	
	int counter =0 ;
	
	private List<Entity> entities;
	private Camera camera;
	
	private Vector3f cameraInc;
	
	private Entity spherEntity1;
	private Entity spherEntity2;
	private Entity spherEntity3;
	private Entity spherEntity4;
	
	private boolean engine0Requested = false;
	private boolean engine1Requested = false;
	private boolean engine2Requested = false;
	private boolean engine3Requested = false;
	private boolean engine4Requested = false;
	private boolean engine5Requested = false;
	private boolean engine6Requested = false;
	private boolean engine7Requested = false;
	private boolean engine8Requested = false;
	private boolean engine9Requested = false;
	private boolean engineERequested = false;
	private boolean engineRRequested = false;
	private boolean engineTRequested = false;
	private boolean engineYRequested = false;
	private boolean engineURequested = false;
	private boolean engineIRequested = false;
	private boolean engineORequested = false;
	private boolean enginePRequested = false;
	private boolean engineFRequested = false;
	private boolean engineGRequested = false;
	private boolean engineHRequested = false;
	private boolean engineJRequested = false;
	private boolean engineKRequested = false;
	private boolean engineLRequested = false;
	
	
	public TestGame() {
		renderer = new RenderManager();
		window = Main.getWindow();
		loader = new ObjectLoader();
		camera = new Camera();
		cameraInc = new Vector3f(0,0,0);
		physEngine = new PhysicsEngine();
	}
	

	@Override
	public void init() throws Exception {
		renderer.init();
		
		Model starModel = loader.loadOBJModel("./src/models/lolostar.obj");
		starModel.setTexture(new Texture(loader.loadTexture("./textures/yellow.jpg")));
		
		Model planeModel = loader.loadOBJModel("./src/models/cube.txt");
		planeModel.setTexture(new Texture(loader.loadTexture("./textures/grid2.jpg")));
		this.landModel = planeModel;
		
		Model landerModel = loader.loadOBJModel("./src/models/lolo2.obj");
		landerModel.setTexture(new Texture(loader.loadTexture("./textures/gray.jpg")));
		
		Model sphereModel1 = loader.loadOBJModel("./src/models/sphere.txt");
		sphereModel1.setTexture(new Texture(loader.loadTexture("./textures/grid.jpg")));
		Model sphereModel2 = loader.loadOBJModel("./src/models/sphere.txt");
		sphereModel2.setTexture(new Texture(loader.loadTexture("./textures/grid.jpg")));
		Model sphereModel3 = loader.loadOBJModel("./src/models/sphere.txt");
		sphereModel3.setTexture(new Texture(loader.loadTexture("./textures/grid.jpg")));
		Model sphereModel4 = loader.loadOBJModel("./src/models/sphere.txt");
		sphereModel4.setTexture(new Texture(loader.loadTexture("./textures/grid.jpg")));
		
		//STARS
		entities = new ArrayList<>();
		Random rnd = new Random();
		for(int i = 0; i < 200; i++) {
			float x = rnd.nextFloat() * 100 - 50;
			float y = Math.abs(rnd.nextFloat() * 100 - 50) ;
			float z = rnd.nextFloat() * 100 - 50;
			if(Math.sqrt(x * x + y * y + z * z) > 10) {
				Entity star = new Star(starModel, new Vector3f(x,y,z),
						new Vector3f(rnd.nextFloat() * 180, 
								 rnd.nextFloat() * 180, 0), 0.05f);
				entities.add(star); 
			}
		}
		Entity platform = new Platform(planeModel, new Vector3f(0,-50,0), new Vector3f(0,0,0), -5,50);
		entities.add(platform);
		
		Entity center = new Test(landerModel, new Vector3f(20,40,20), new Vector3f(0,0,0), 1);
		entities.add(center);
		this.centerEntity = center;
		
		physEngine.setCenter(centerEntity);
		
		Entity spherEntity1 = new Test(sphereModel1, new Vector3f(25.23f,33.5f,25.23f), new Vector3f(0,0,0), 0.1f);
		entities.add(spherEntity1);
		Entity spherEntity2 = new Test(sphereModel2, new Vector3f(25.23f,33.5f,15.23f), new Vector3f(0,0,0), 0.1f);
		entities.add(spherEntity2);
		Entity spherEntity3 = new Test(sphereModel3, new Vector3f(15.23f,33.5f,15.23f), new Vector3f(0,0,0), 0.1f);
		entities.add(spherEntity3);
		Entity spherEntity4 = new Test(sphereModel4, new Vector3f(15.23f,33.5f,25.23f), new Vector3f(0,0,0), 0.1f);
		entities.add(spherEntity4);
		
		this.spherEntity1 = spherEntity1;
		this.spherEntity2 = spherEntity2;
		this.spherEntity3 = spherEntity3;
		this.spherEntity4 = spherEntity4;
		
		physEngine.addObject(spherEntity1);
		physEngine.addObject(spherEntity2);
		physEngine.addObject(spherEntity3);
		physEngine.addObject(spherEntity4);
	
		centerEntity.setV(new Vector3f(0,0,0));
		centerEntity.setW(new Vector3f(0,0,0));
		
	}

	public List<Float> getInfo(boolean engineFired) {
		List<Float> list = Arrays.asList(new Float[18]);
		list.set(0, centerEntity.getPos().x);
		list.set(1, centerEntity.getPos().y);
		list.set(2, centerEntity.getPos().z);
		list.set(3, centerEntity.getRotation().x);
		list.set(4, centerEntity.getRotation().y);
		list.set(5, centerEntity.getRotation().z);
		list.set(6, centerEntity.getV().x);
		list.set(7, centerEntity.getV().y);
		list.set(8, centerEntity.getV().z);
		list.set(9, centerEntity.getW().x);
		list.set(10, centerEntity.getW().y);
		list.set(11, centerEntity.getW().z);
		list.set(12, spherEntity1.getPos().y < 1 ? 1.0f : 0.0f);
		list.set(13, spherEntity2.getPos().y < 1 ? 1.0f : 0.0f);
		list.set(14, spherEntity3.getPos().y < 1 ? 1.0f : 0.0f);
		list.set(15, spherEntity4.getPos().y < 1 ? 1.0f : 0.0f);
		list.set(16, calculateReward(engineFired));
		list.set(17, isDone());
		return list;
	}
	public float calculateReward(boolean engineFired) {
		float reward = 0;
		if(isDone() > 0.5 && spherEntity1.getPos().length() < 15
				&& spherEntity2.getPos().length() < 15
				&& spherEntity3.getPos().length() < 15
				&& spherEntity4.getPos().length() < 15)
			reward += 100;
		if(Math.abs(centerEntity.getPos().x) >= 50 || 
				Math.abs(centerEntity.getPos().y) >= 50 ||
				Math.abs(centerEntity.getPos().z) >= 50) 
			reward -= 100;
		if(Math.abs(centerEntity.getRotation().x) >= 40 || 
				Math.abs(centerEntity.getRotation().y) >= 40 ||
				Math.abs(centerEntity.getRotation().z) >= 40) 
			reward -= 100;
		if(centerEntity.getRotation().length() > prevDist) {
			reward -= 5;
			prevDist = centerEntity.getRotation().length();
		}
		if(spherEntity1.getPos().y < 1 && spherEntity1.getPos().length() < 15) 
			reward += 15;
		if(spherEntity2.getPos().y < 1 && spherEntity2.getPos().length() < 15) 
			reward += 15;
		if(spherEntity3.getPos().y < 1 && spherEntity3.getPos().length() < 15) 
			reward += 15;
		if(spherEntity4.getPos().y < 1 && spherEntity4.getPos().length() < 15) 
			reward += 15;
		if(engineFired)
			reward -= 0.03f;
		return reward;
	}
	public float isDone() {
		if(spherEntity1.getPos().y < 1 &&
				spherEntity2.getPos().y < 1 &&
				spherEntity3.getPos().y < 1 &&
				spherEntity4.getPos().y < 1) {
				return 1.0f;
			}
		else return 0;
	}

	public void reset() {
		centerEntity.setPos(20,40,20);
		centerEntity.setRotation(0,0,0);
		centerEntity.setV(0,0,0);
		centerEntity.setW(0,0,0);
		centerEntity.setAlpha(0,0,0);
		centerEntity.setA(0,0,0);
	}
	
	@Override
	public void input() {
		cameraInc.set(0,0,0);
		if(window.isKeyPressed(GLFW.GLFW_KEY_W))
			cameraInc.z = -1;
		if(window.isKeyPressed(GLFW.GLFW_KEY_S))
			cameraInc.z = 1;
		
		if(window.isKeyPressed(GLFW.GLFW_KEY_A))
			cameraInc.x = -1;
		if(window.isKeyPressed(GLFW.GLFW_KEY_D))
			cameraInc.x = 1;
		
		if(window.isKeyPressed(GLFW.GLFW_KEY_LEFT_CONTROL))
			cameraInc.y = -1;
		if(window.isKeyPressed(GLFW.GLFW_KEY_SPACE))
			cameraInc.y = 1;
		
		if(window.isKeyPressed(GLFW.GLFW_KEY_0))	engine0Requested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_1))	engine1Requested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_2))	engine2Requested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_3))	engine3Requested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_4))	engine4Requested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_5))	engine5Requested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_6))	engine6Requested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_7))	engine7Requested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_8))	engine8Requested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_9))	engine9Requested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_E))	engineERequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_R))	engineRRequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_T))	engineTRequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_Y))	engineYRequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_U))	engineURequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_I))	engineIRequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_O))	engineORequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_P))	enginePRequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_F))	engineFRequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_G))	engineGRequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_H))	engineHRequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_J))	engineJRequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_K))	engineKRequested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_L))	engineLRequested = true;
	}
	public void setPosSpheres() {
		spherEntity1.getPos().x  = centerEntity.getPos().x + centerEntity.getDir1().x;
		spherEntity1.getPos().y  = centerEntity.getPos().y + centerEntity.getDir1().y;
		spherEntity1.getPos().z  = centerEntity.getPos().z + centerEntity.getDir1().z;
		
		spherEntity2.getPos().x  = centerEntity.getPos().x + centerEntity.getDir2().x;
		spherEntity2.getPos().y  = centerEntity.getPos().y + centerEntity.getDir2().y;
		spherEntity2.getPos().z  = centerEntity.getPos().z + centerEntity.getDir2().z;
		
		spherEntity3.getPos().x  = centerEntity.getPos().x + centerEntity.getDir3().x;
		spherEntity3.getPos().y  = centerEntity.getPos().y + centerEntity.getDir3().y;
		spherEntity3.getPos().z  = centerEntity.getPos().z + centerEntity.getDir3().z;
		
		spherEntity4.getPos().x  = centerEntity.getPos().x + centerEntity.getDir4().x;
		spherEntity4.getPos().y  = centerEntity.getPos().y + centerEntity.getDir4().y;
		spherEntity4.getPos().z  = centerEntity.getPos().z + centerEntity.getDir4().z;
	}
	public void thrust() {
		ArrayList<Integer> controlList = new ArrayList<>();
		if(engine0Requested) {  controlList.add(0);	engine0Requested = false;}
		if(engine1Requested) {	controlList.add(1); engine1Requested = false;}
		if(engine2Requested) {  controlList.add(2); engine2Requested = false;}
		if(engine3Requested) {  controlList.add(3); engine3Requested = false;}
		if(engine4Requested) {  controlList.add(4);	engine4Requested = false;}
		if(engine5Requested) {  controlList.add(5); engine5Requested = false;}
		if(engine6Requested) {	controlList.add(6); engine6Requested = false;}
		if(engine7Requested) {	controlList.add(7);	engine7Requested = false;}
		if(engine8Requested) {	controlList.add(8); engine8Requested = false;}
		if(engine9Requested) {	controlList.add(9);	engine9Requested = false;}
		if(engineERequested) {	controlList.add(10);engineERequested = false;}
		if(engineRRequested) {	controlList.add(11);engineRRequested = false;}
		if(engineTRequested) {	controlList.add(12);engineTRequested = false;}
		if(engineYRequested) {	controlList.add(13);engineYRequested = false;}
		if(engineURequested) {	controlList.add(14);engineURequested = false;}
		if(engineIRequested) {	controlList.add(15);engineIRequested = false;}
		if(engineORequested) {	controlList.add(16);engineORequested = false;}
		if(enginePRequested) {	controlList.add(17);enginePRequested = false;}
		if(engineFRequested) {	controlList.add(18);engineFRequested = false;}
		if(engineGRequested) {	controlList.add(19);engineGRequested = false;}
		if(engineHRequested) {	controlList.add(20);engineHRequested = false;}
		if(engineJRequested) {	controlList.add(21);engineJRequested = false;}
		if(engineKRequested) {	controlList.add(22);engineKRequested = false;}
		if(engineLRequested) {	controlList.add(23);engineLRequested = false;}
		
		if(controlList.size() == 0) {
			centerEntity.setA(0,0,0);
			centerEntity.setAlpha(0,0,0);
		}
		else { 
			centerEntity.angleTransformer(controlList, centerEntity.getRotation());
			centerEntity.setAlpha(centerEntity.getCalculatedAlpha());
			centerEntity.setA(centerEntity.getCalculatedA());
			controlList.clear();
		}
	}
	@Override
	public void update(float interval, MouseInput mouseInput) {
		thrust();
		centerEntity.transformation();
		physEngine.simulate(interval);
		setPosSpheres();
		
		if(spherEntity1.getPos().y < 1 &&
			spherEntity2.getPos().y < 1 &&
			spherEntity3.getPos().y < 1 &&
			spherEntity4.getPos().y < 1) {
			try {
				landModel.setTexture(new Texture(loader.loadTexture("./textures/blue.jpg")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		camera.movePosition(
				cameraInc.x * CAMERA_MOVEMENT_SPEED, 
				cameraInc.y * CAMERA_MOVEMENT_SPEED,
				cameraInc.z * CAMERA_MOVEMENT_SPEED);
		
		if(mouseInput.isRightButtonPress()) {
			Vector2f rotVector = mouseInput.getDisplVec();
			camera.moveRotation(rotVector.x * Consts.MOUSE_SENSITIVITY, 
								rotVector.y * Consts.MOUSE_SENSITIVITY, 
								0);
			 mouseInput.setDisplVec(0f, 0f);
 		}
		
		for(Entity entity: entities)	renderer.processEntities(entity);
	}
	
	@Override
	public void render() {
		if(window.isResize()) {
			GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
			window.setResize(true);
		}
		
		window.setClearColour(0.0f, 0.0f, 0.0f, 0.0f);
		renderer.render(camera);
	}

	@Override
	public void cleanUp() {
		renderer.cleanUp();
		loader.cleanup();
	}



}
