package com.base.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

import com.base.engine.utils.Consts;
import com.base.game.Main;

public class EngineManager {
	public static final long NANOSECOND = 1000000000L;
	public static final long FRAMERATE = 60;
	
	private static int fps;
	public static float frametime = 1.0f / FRAMERATE;
	
	private boolean isRunning;
	
	private ILogic gameLogic;
	
	private WindowManager window;
	private MouseInput mouseInput;
	private GLFWErrorCallback errorCallback;
	private float interval  = 0.01f ; //delta
	
	public int step = 0;
	
	private void init() throws Exception {
		GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
		window = Main.getWindow();
		gameLogic = Main.getGame();
		mouseInput = new MouseInput();
		window.init();
		gameLogic.init();
		mouseInput.init();
		
	}
	public void start() throws Exception {
		init();
		if(isRunning)
			return;
		run();
	}
	public void run() {
		isRunning = true;
		int frames = 0;
		long frameCounter = 0;
		long lastTime = System.nanoTime();
		double unprocessedTime = 0;
		
		while(isRunning) {
			boolean render = false;
			long startTime = System.nanoTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double)NANOSECOND;
			frameCounter += passedTime; // counts the time
			
			input();
			
			while(unprocessedTime > frametime) {
				render = true;
				unprocessedTime -= frametime;
				
				if(window.windowShouldClose())
					stop();
				if(frameCounter >= NANOSECOND) {
					setFps(frames);
					window.setTitle(Consts.TITLE + " FPS : " + getFps());
					frames = 0;
					frameCounter = 0;
				}
			}
			
			if(render) {
				update(this.interval);
				render();
				frames++;
			}
		}
		cleanUp();
	}
	private void stop() {
		if(!isRunning)
			return;
		isRunning = false;
	}
	private void input() {
		mouseInput.input();
		gameLogic.input();
	}
	private void render() {
		gameLogic.render();
		window.update();	
	}
	private void update(float interval) {
		if(step > 0) {
			gameLogic.update(interval, mouseInput);
			step--;
		}
	}
	private void cleanUp() {
		window.cleanUp();
		gameLogic.cleanUp();
		errorCallback.free();
		GLFW.glfwTerminate();
	}
	
	
	public static int getFps() {
		return fps;
	}
	public static void setFps(int fps) {
		EngineManager.fps = fps;
	}
	public void step() {
		this.step = 10;
	}
	
}
