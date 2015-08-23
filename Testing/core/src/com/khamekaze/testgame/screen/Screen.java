package com.khamekaze.testgame.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.testgame.camera.OrthoCamera;
import com.khamekaze.testgame.input.InputManager;

public abstract class Screen {
	
	public static OrthoCamera camera = new OrthoCamera();
	public static InputManager inputManager = new InputManager(camera);

	public abstract void create();
	
	public abstract void update();
	
	public abstract void render(SpriteBatch sb);
	
	public abstract void resize(int width, int height);
	
	public abstract void dispose();
	
	public abstract void pause();
	
	public abstract void resume();
	
}
