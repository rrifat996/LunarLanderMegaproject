package com.base.game;

import py4j.GatewayServer;

import java.util.List;
import com.base.engine.EngineManager;
import com.base.engine.WindowManager;
import com.base.engine.utils.Consts;

public class Main {
	
	private static WindowManager window;
	private static TestGame game;
	private static int number;
	private static EngineManager engine;
	private static List<Float> latestInfo;
	private static int latestThrust = 0;
	
	public static void main(String[] args) throws InterruptedException {
		Main main = new Main();
		
		GatewayServer server = new GatewayServer(main);
		server.start();
		
		window = new WindowManager(Consts.TITLE, 1600, 900, false);
		game = new TestGame();
		engine  = new EngineManager();
		
		try {
			engine.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void reset() {		    	
		game.reset();
	}
	public static void step(int thrust) throws InterruptedException {
		setLatestThrust(thrust);
		engine.step();
	}
	public static List<Float> getLatestInfo() {
		
		List<Float> info = game.getInfo(latestThrust < 36);
		System.out.println(info.toString());
		return info;
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
	
	public static void setLatestInfo(List<Float> latestInfo) {
		Main.latestInfo = latestInfo;
	}
	public static int getLatestThrust() {
		return latestThrust;
	}
	public static void setLatestThrust(int latestThrust) {
		Main.latestThrust = latestThrust;
	}
	
}
