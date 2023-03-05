package com.base.game;

import py4j.GatewayServer;

import java.util.ArrayList;
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
	private static ArrayList<Integer> latestThrusts;
	
	public static void main(String[] args) throws InterruptedException {
		Main main = new Main();
		
		latestThrusts = new ArrayList<>();
		
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
	public static void step(int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8
			, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) throws InterruptedException {
		ArrayList<Integer> actions = new ArrayList<>();
		actions.add(i1);
		actions.add(i2);
		actions.add(i3);
		actions.add(i4);
		actions.add(i5);
		actions.add(i6);
		actions.add(i7);
		actions.add(i8);
		actions.add(i9);
		actions.add(i10);
		actions.add(i11);
		actions.add(i12);
		actions.add(i13);
		actions.add(i14);
		actions.add(i15);
		actions.add(i16);
		latestThrusts = actions;
		engine.step();
	}
	public static List<Float> getLatestInfo() {
		
		List<Float> info = game.getInfo(latestThrusts);
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
	public static ArrayList<Integer> getLatestThrusts() {
		return latestThrusts;
	}
}
