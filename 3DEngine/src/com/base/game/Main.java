package com.base.game;

import py4j.GatewayServer;
import com.base.engine.EngineManager;
import com.base.engine.WindowManager;
import com.base.engine.utils.Consts;

public class Main {
	
	private static WindowManager window;
	private static TestGame game;
	private static int number;
	
	public static int testMethod() {
		number = 7;
		return 5;
	}
	public int getNumber() {
		return number;
	}
	public static void main(String[] args) {
		Main main = new Main();
		GatewayServer server = new GatewayServer(main);
		server.start();
		
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
