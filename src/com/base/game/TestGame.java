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

public class TestGame implements ILogic{
	
	private static final float CAMERA_MOVEMENT_SPEED = 0.2f;
	
	private final RenderManager renderer;
	private final ObjectLoader loader;
	private final WindowManager window;
	private final PhysicsEngine physEngine;
	
	private List<Entity> entities;
	private List<Terrain> terrains;
	private Camera camera;
	
	private Vector3f cameraInc;
	
	
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
		
		Model landerModel = loader.loadOBJModel("./src/models/lunar_lander.obj");
		landerModel.setTexture(new Texture(loader.loadTexture("./textures/grid.jpg")));
		
		Model cubeModel = loader.loadOBJModel("./src/models/cube.txt");
		cubeModel.setTexture(new Texture(loader.loadTexture("./textures/gray.jpg")));
		
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
		
		Entity platform = new Platform(cubeModel, new Vector3f(0,-63,-5), new Vector3f(0,0,0), -63f,50);
		entities.add(platform);
		
		Entity lunarLander = new LunarLander(landerModel, new Vector3f(0,0,0),new Vector3f(270,0,0), 1);
		entities.add(lunarLander);
		
		for (PhysicsObject obj : lunarLander.getHitpoints()) {
			System.out.println(obj.getmVelocity().length());
			physEngine.addObject(obj);
		}
		physEngine.addObject(platform.getHitpoints().get(0));
		System.out.println(platform.getHitpoints().get(0).getmVelocity().length());
		
		
		
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
	}

	@Override
	public void update(float interval, MouseInput mouseInput) {
		physEngine.simulate(interval);
		physEngine.handleCollisions();
		
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
