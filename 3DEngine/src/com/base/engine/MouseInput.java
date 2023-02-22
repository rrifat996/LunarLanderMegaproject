package com.base.engine;

import org.joml.Vector2d;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.base.game.Main;

public class MouseInput {
	private final Vector2d previousPos, currentPos;
	private final Vector2f displVec;
	
	private boolean inWindow = false, leftButtonPress = false, rightButtonPress = false;
	public MouseInput() {
		previousPos = new Vector2d(-1, -1);
		currentPos = new Vector2d(0,0);
		displVec = new Vector2f();
	}
	
	public void init() {
		GLFW.glfwSetCursorPosCallback(Main.getWindow().getWindow(), (window, xpos, ypos) 
				-> {
					currentPos.x = xpos;
					currentPos.y = ypos;
				});
		GLFW.glfwSetCursorEnterCallback(Main.getWindow().getWindow(), (window, entered) 
				-> {
					inWindow = entered;
				});
		GLFW.glfwSetMouseButtonCallback(Main.getWindow().getWindow(), (window, button, action, mods)
				-> {
					leftButtonPress = button == GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS;
					rightButtonPress = button == GLFW.GLFW_MOUSE_BUTTON_2 && action == GLFW.GLFW_PRESS;
				});
	}
	public void input() {
		if(previousPos.x > 0 && previousPos.y > 0 && inWindow) {
			double deltaX = currentPos.x - previousPos.x;
			double deltaY = currentPos.y - previousPos.y;
			boolean rotateX = deltaX != 0;
			boolean rotateY = deltaY != 0; 
			if(rotateX)
				displVec.y = (float) deltaX;
			if(rotateY)
				displVec.x = (float) deltaY;
		}
		previousPos.x = currentPos.x;
		previousPos.y = currentPos.y;
	}

	public Vector2f getDisplVec() {
		return displVec;
	}

	public boolean isLeftButtonPress() {
		return leftButtonPress;
	}

	public boolean isRightButtonPress() {
		return rightButtonPress;
	}
	public void setDisplVec(float x, float y) {
	    this.displVec.x = x;
	    this.displVec.y = y;
	}
}
