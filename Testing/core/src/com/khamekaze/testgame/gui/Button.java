package com.khamekaze.testgame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
	
	private Rectangle button;
	private Texture texture;
	private float x, y, width, height;
	private String name, textureName;
	
	public Button(int x, int y, String textureName, String name) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.textureName = textureName;
		texture = new Texture(Gdx.files.internal(textureName + ".png"));
		width = texture.getWidth();
		height = texture.getHeight();
		button = new Rectangle(x, y, width, height);
	}
	
	public Button(int x, int y, Texture buttonTexture, String name) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.texture = buttonTexture;
		width = texture.getWidth();
		height = texture.getHeight();
		button = new Rectangle(x, y, width, height);
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(texture, x, y, width, height);
	}
	
	public Rectangle getHitbox() {
		return button;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public String getName() {
		return name;
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
}
