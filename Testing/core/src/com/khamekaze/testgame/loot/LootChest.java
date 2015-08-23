package com.khamekaze.testgame.loot;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.khamekaze.testgame.TextureManager;

public class LootChest {

	private Rectangle hitBox;
	private float x, y, width, height;
	private Texture texture;
	
	public LootChest() {
		texture = TextureManager.CHEST_CLOSED;
		width = texture.getWidth();
		height = texture.getHeight();
	}
	
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
		hitBox = new Rectangle(x, y, width, height);
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
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

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
}
