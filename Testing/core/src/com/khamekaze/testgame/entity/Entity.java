package com.khamekaze.testgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	protected Texture texture;
	protected Vector2 pos;
	protected int hp, attack;
	protected Rectangle hitBox;
	protected float waitTime, passedTime = 0, percentReady = 0;
	
	public Entity(Texture texture, Vector2 pos, int hp, int attack, int waitTime) {
		this.texture = texture;
		this.pos = pos;
		this.hp = hp;
		this.attack = attack;
		this.waitTime = waitTime;
		hitBox = new Rectangle(pos.x, pos.y, texture.getWidth(), texture.getHeight());
	}
	
	public void update() {
		hitBox.setPosition(pos);
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y);
	}

	public Vector2 getPosition() {
		return pos;
	}
	
	public Rectangle getHitbox() {
		return hitBox;
	}
	
	public float getWaitTime() {
		return waitTime;
	}
	
	public float getPercentReady() {
		return percentReady;
	}
	
}
