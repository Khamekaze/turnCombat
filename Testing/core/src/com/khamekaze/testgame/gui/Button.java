package com.khamekaze.testgame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
	
	private Rectangle button;
	private Texture texture;
	private float x, y, width, height;
	private String name;
	
	public Button(float x, float y, float width, float height, String name) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
		button = new Rectangle(x, y, width, height);
		texture = new Texture(Gdx.files.internal("menuButton.png"));
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(texture, x, y, width, height);
	}
	
	public Rectangle getHitbox() {
		return button;
	}
	
	public String getName() {
		return name;
	}
	
}
