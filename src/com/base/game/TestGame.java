package com.base.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joml.Vector2f;
import org.joml.Vector3f;
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

import physicsObjects.LunarLander;
import physicsObjects.Platform;
import physicsObjects.Star;
import physicsObjects.Test;

public class TestGame implements ILogic{
	private static final float CAMERA_MOVEMENT_SPEED = 0.2f;
	
	private final RenderManager renderer;
	private final ObjectLoader loader;
	private final WindowManager window;
	private final PhysicsEngine physEngine;
	
	private Entity centerEntity ;

	private int updateCounter = 0;
	
	int counter =0 ;
	
	private List<Entity> entities;
	private Camera camera;
	
	private Vector3f cameraInc;
	
	private Entity spherEntity1;
	private Entity spherEntity2;
	private Entity spherEntity3;
	private Entity spherEntity4;
	
	private boolean engine1Requested = false;
	private boolean engine2Requested = false;
	
	
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
		
		Model starModel = loader.loadOBJModel("./src/models/star.obj");
		starModel.setTexture(new Texture(loader.loadTexture("./textures/yellow.jpg")));
		
		Model cubeModel = loader.loadOBJModel("./src/models/cube.txt");
		cubeModel.setTexture(new Texture(loader.loadTexture("./textures/gray.jpg")));
		
		Model landerModel = loader.loadOBJModel("./src/models/lunar_lander.obj");
		landerModel.setTexture(new Texture(loader.loadTexture("./textures/gray.jpg")));
		
		Model cubeModel1 = loader.loadOBJModel("./src/models/cube.txt");
		cubeModel1.setTexture(new Texture(loader.loadTexture("./textures/blue.jpg")));
		
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
			Entity star = new Star(starModel, new Vector3f(x,y,z),
					new Vector3f(rnd.nextFloat() * 180, 
							 rnd.nextFloat() * 180, 0), 0.05f);
			entities.add(star);
		}
		Entity platform = new Platform(cubeModel, new Vector3f(0,-64,0), new Vector3f(0,0,0), -16f,50);
		entities.add(platform);
		
		Entity center = new Test(landerModel, new Vector3f(0,0,0), new Vector3f(0,0,0), 1);
		entities.add(center);
		this.centerEntity = center;
		
		Entity spherEntity1 = new Test(sphereModel1, new Vector3f(5,0,5), new Vector3f(0,0,0), 1);
		entities.add(spherEntity1);
		Entity spherEntity2 = new Test(sphereModel2, new Vector3f(5,0,-5), new Vector3f(0,0,0), 1);
		entities.add(spherEntity2);
		Entity spherEntity3 = new Test(sphereModel3, new Vector3f(-5,0,-5), new Vector3f(0,0,0), 1);
		entities.add(spherEntity3);
		Entity spherEntity4 = new Test(sphereModel4, new Vector3f(-5,0,5), new Vector3f(0,0,0), 1);
		entities.add(spherEntity4);
		
		this.spherEntity1 = spherEntity1;
		this.spherEntity2 = spherEntity2;
		this.spherEntity3 = spherEntity3;
		this.spherEntity4 = spherEntity4;
		
		//physEngine.addObject(platform.getHitpoints().get(0));
		
		centerEntity.setV(new Vector3f(0,0,0));
		centerEntity.setW(new Vector3f(0,0,0));
		
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
		
		if(window.isKeyPressed(GLFW.GLFW_KEY_1))	engine1Requested = true;
		if(window.isKeyPressed(GLFW.GLFW_KEY_2))	engine2Requested = true;
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
		if(engine1Requested) {	controlList.add(1);	engine1Requested = false;}
		if(engine2Requested) {	controlList.add(2); engine2Requested = false;}
		
		if(controlList.size() == 0) {
			centerEntity.setA(0,0,0);
			centerEntity.setAlpha(0,0,0);
		}
		else {
			centerEntity.angleTransformer(controlList, centerEntity.getRotation());
			centerEntity.setAlpha(centerEntity.getCalculatedAlpha());
			controlList.clear();
		}
	}
	@Override
	public void update(float interval, MouseInput mouseInput) {
		//System.out.println(centerEntity.getDir1());
		centerEntity.transformation();
		setPosSpheres();
		thrust();
		
		
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
		
		for(Entity entity: entities) {
			renderer.processEntities(entity);
		}
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
