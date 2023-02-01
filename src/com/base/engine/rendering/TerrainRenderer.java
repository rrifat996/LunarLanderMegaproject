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
import com.base.engine.entity.Model;
import com.base.engine.entity.terrain.Terrain;
import com.base.engine.utils.Transformation;
import com.base.engine.utils.Utils;
import com.base.game.Main;

public class TerrainRenderer implements IRenderer{
	
	ShaderManager shader;
	private List<Terrain> terrains;
	
	public TerrainRenderer() throws Exception{
		terrains = new ArrayList<>();
		shader = new ShaderManager();
	}

	@Override
	public void init() throws Exception {
		shader.createVertexShader(Utils.loadResource("/shaders/terrain_vertex.vert"));
		shader.createFragmentShader(Utils.loadResource("/shaders/terrain_fragment.frag"));
		shader.link();
		shader.createUniform("textureSampler");
		shader.createUniform("transformationMatrix");
		shader.createUniform("projectionMatrix");
		shader.createUniform("viewMatrix");
		
	}

	@Override
	public void render(Camera camera) {
		shader.bind();
		shader.setUniform("projectionMatrix", Main.getWindow().updateProjectionMatrix());
		for(Terrain terrain : terrains) {
			bind(terrain.getModel());
			prepare(terrain, camera);
			GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			unbind();
		}
		terrains.clear();
		shader.unbind();
		
	}

	@Override
	public void bind(Model model) {
		GL30.glBindVertexArray(model.getId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL30.glActiveTexture(GL30.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getId());
		
	}

	@Override
	public void unbind() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
		
	}

	@Override
	public void prepare(Object terrain, Camera camera) {
		shader.setUniform("textureSampler", 0);
		shader.setUniform("transformationMatrix", Transformation.createTransformationMatrix((Terrain)terrain));
		shader.setUniform("viewMatrix", Transformation.getViewMatrix(camera));
		
	}

	@Override
	public void cleanup() {
		shader.cleanup();
		
	}

	public List<Terrain> getTerrain() {
		return terrains;
	}

	 
}
