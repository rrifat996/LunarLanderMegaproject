package com.base.game;

import com.base.engine.EngineManager;
import com.base.engine.WindowManager;
import com.base.engine.utils.Consts;

public class Main {
	
	private static WindowManager window;
	private static TestGame game;
	
	public static void main(String[] args) {
		window = new WindowManager(Consts.TITLE, 1600, 900, false);
		game = new TestGame();
		EngineManager engine  = new EngineManager();
		
		try {
			engine.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	

	public static WindowManager getWindow() {
		return window;
	}

	public static void setWindow(WindowManager window) {
		Main.window = window;
	}

	public static TestGame getGame() {
		return game;
	}

	public static void setGame(TestGame game) {
		Main.game = game;
	}
	
}
