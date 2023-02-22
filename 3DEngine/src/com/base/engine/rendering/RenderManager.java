package com.base.engine.rendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.base.engine.Camera;
import com.base.engine.Entity;
import com.base.engine.ShaderManager;
import com.base.engine.WindowManager;
import com.base.engine.entity.Model;
import com.base.engine.entity.terrain.Terrain;
import com.base.engine.utils.Transformation;
import com.base.engine.utils.Utils;
import com.base.game.Main;

public class RenderManager {
	private final WindowManager window;
	private EntityRender entityRenderer;
	private TerrainRenderer terrainRenderer;
	
	public RenderManager() {
		window = Main.getWindow();
	}
	public void init() throws Exception{
		entityRenderer = new EntityRender();
		terrainRenderer = new TerrainRenderer();
		entityRenderer.init(); 
		terrainRenderer.init();
		
	}
	public void render(Camera camera) {
		clear();
		
		entityRenderer.render(camera);
		terrainRenderer.render(camera);
	}
	public void processEntities(Entity entity) {
		List<Entity> entityList = entityRenderer.getEntities().get(entity.getModel());
		if(entityList != null) {
			entityList.add(entity);
		}
		else {
			List<Entity> newEntityList = new ArrayList<>();
			newEntityList.add(entity);
			entityRenderer.getEntities().put(entity.getModel(), newEntityList);
		}
	}
	public void processTerrain(Terrain terrain) {
		terrainRenderer.getTerrain().add(terrain);
	}
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
	}
	public void cleanUp() {
		entityRenderer.cleanup();
		terrainRenderer.cleanup();
	}
}
