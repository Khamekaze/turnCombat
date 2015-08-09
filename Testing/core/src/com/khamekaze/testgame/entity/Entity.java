package com.khamekaze.testgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	protected Texture texture;
	protected Vector2 pos;
	protected BitmapFont font;
	protected int hp, attack, maxHp;
	protected Rectangle hitBox;
	protected float waitTime, passedTime = 0, percentReady = 0;
	protected boolean atbFull = false, waitingForAction = false, hasAttacked = false;
	
	public Entity(Texture texture, Vector2 pos, int hp, int attack, int waitTime) {
		this.texture = texture;
		this.pos = pos;
		this.hp = hp;
		this.attack = attack;
		this.waitTime = waitTime;
		maxHp = hp;
		hitBox = new Rectangle(pos.x, pos.y, texture.getWidth(), texture.getHeight());
		font = new BitmapFont();
	}
	
	public void update() {
		hitBox.setPosition(pos);
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y);
	}
	
	public void attack(Entity e) {
		e.takeDamage(attack);
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
	
	public int getHp() {
		return hp;
	}
	
	public void takeDamage(int amount) {
		hp = hp - amount;
	}
	
	public void restoreHp(int amount) {
		hp = hp + amount;
		if(hp > maxHp)
			hp = maxHp;
	}
	
	public float getPassedTime() {
		return passedTime;
	}
	
	public void resetActionTime() {
		percentReady = 0;
		passedTime = 0;
		hasAttacked = true;
	}
	
	public boolean getAtbFull() {
		return atbFull;
	}
	
	public void setAtbFull(boolean atbFull) {
		this.atbFull = atbFull;
	}
	
	public boolean getWaitingForAction() {
		return waitingForAction;
	}
	
	public void setWaitingForAction(boolean waitingForAction) {
		this.waitingForAction = waitingForAction;
	}
	
}
