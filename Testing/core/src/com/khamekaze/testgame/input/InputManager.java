package com.khamekaze.testgame.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.khamekaze.testgame.camera.OrthoCamera;

public class InputManager {
	
	private Rectangle mouseHitbox;
	private OrthoCamera camera;
	private Vector3 input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
	
	public InputManager(OrthoCamera camera) {
		this.camera = camera;
		mouseHitbox = new Rectangle(Gdx.input.getX(), Gdx.input.getY(), 10, 10);
	}
	
	public void update() {
		input.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(input);
		mouseHitbox.setX(input.x - 5);
		mouseHitbox.setY(input.y - 5);
	}
	
	public boolean getIntersecting(Rectangle rect) {
		return mouseHitbox.overlaps(rect);
	}
	
	public Rectangle getMouseHitbox() {
		return mouseHitbox;
	}
}
