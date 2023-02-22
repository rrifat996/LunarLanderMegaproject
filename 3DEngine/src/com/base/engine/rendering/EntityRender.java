package com.base.engine.rendering;

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
import com.base.engine.utils.Transformation;
import com.base.engine.utils.Utils;
import com.base.game.Main;

public class EntityRender implements IRenderer{
	
	ShaderManager shader;
	private Map<Model, List<Entity>> entities;
	
	public EntityRender() throws Exception{
		entities = new HashMap<>();
		shader = new ShaderManager();
	}

	@Override
	public void init() throws Exception {
		shader.createVertexShader(Utils.loadResource("/shaders/vertex.vert"));
		shader.createFragmentShader(Utils.loadResource("/shaders/fragment.frag"));
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
		for(Model model : entities.keySet()) {
			bind(model);
			List<Entity> entityList = entities.get(model);  
			for(Entity entity : entityList) {
				prepare(entity, camera);
				GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			unbind();
		}
		entities.clear();
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
	public void prepare(Object entity, Camera camera) {
		shader.setUniform("textureSampler", 0);
		shader.setUniform("transformationMatrix", Transformation.createTransformationMatrix((Entity)entity));
		shader.setUniform("viewMatrix", Transformation.getViewMatrix(camera));
		
	}

	@Override
	public void cleanup() {
		shader.cleanup();
		
	}

	public Map<Model, List<Entity>> getEntities() {
		return entities;
	}

	 
}
