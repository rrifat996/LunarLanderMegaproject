package com.base.engine.rendering;

import com.base.engine.Camera;
import com.base.engine.entity.Model;

public interface IRenderer<T> {
	public void init() throws Exception;
	public void render(Camera camera);
	abstract void bind(Model model);
	public void unbind();
	public void prepare(T t, Camera camera);
	public void cleanup();
}
