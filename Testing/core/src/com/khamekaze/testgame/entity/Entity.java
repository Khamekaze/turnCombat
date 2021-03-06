package com.khamekaze.testgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public abstract class Entity {
	
	protected Texture texture;
	protected Vector2 pos;
	protected BitmapFont font;
	protected int hp, attack, maxHp, level, xp, xpToNextLevel, xpLeftToNextLevel, xpReceived, enemiesKilled, combinedEnemeyLevels,
				  specialAttack, specialAttackCharge;
	protected Rectangle hitBox;
	protected float waitTime, passedTime = 0, percentReady = 0;
	protected boolean atbFull = false, waitingForAction = false, hasAttacked = false, recievedXp = false;
	protected Sprite sprite;
	protected int coins;
	
	public Entity(Texture texture, Vector2 pos, int hp, int attack, int waitTime, int level) {
		this.texture = texture;
		this.pos = pos;
		this.hp = hp;
		this.attack = attack;
		this.waitTime = waitTime;
		this.level = level;
		maxHp = hp;
		specialAttack = attack + (level * 8);
		specialAttackCharge = 5;
		hitBox = new Rectangle(pos.x, pos.y, texture.getWidth(), texture.getHeight());
		font = new BitmapFont();
		sprite = new Sprite(texture);
		sprite.flip(true, false);
		coins = 0;
	}
	
	public void update() {
		hitBox.setPosition(pos);
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y);
	}
	
	public void getXpReceived(Array<Enemy> entities) {
		enemiesKilled = 0;
		combinedEnemeyLevels = 0;
		
		for(Entity e : entities) {
			enemiesKilled++;
			combinedEnemeyLevels += e.level;
		}
		
		xpReceived = combinedEnemeyLevels * 5 + (enemiesKilled * 5);
		xp += xpReceived;
		
		calculateXpToLevel();
	}
	
	public void levelUp() {
		maxHp += (level * 3);
		hp = maxHp;
		attack += (level * 2);
		specialAttack = attack + (level * 8);
	}
	
	public void calculateXpToLevel() {
		xpToNextLevel = (level * 10);
		xpLeftToNextLevel = xpToNextLevel - xp;
		if(xpLeftToNextLevel <= 0) {
			
			xp = xp - xpToNextLevel;
			level++;
			levelUp();
			xpToNextLevel = (level * 10);
			xpLeftToNextLevel = xpToNextLevel - xp;
			System.out.println(xpToNextLevel);

		}
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
	
	public void updateSpecialAttackCharge() {
		if(specialAttackCharge > 0) {
			specialAttackCharge--;
		}
	}
	
	public void resetSpecialAttackCharge() {
		specialAttackCharge = 5;
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
	
	public boolean hasRecievedXp() {
		return recievedXp;
	}
	
	public void setRecievedXp(boolean recievedXp) {
		this.recievedXp = recievedXp;
	}
	
	public int getCurrentLevel() {
		return level;
	}
	
	public int getAttackDamage() {
		return attack;
	}
	
	public int getXp() {
		return xp;
	}
	
	public int getXpToNextLevel() {
		return xpToNextLevel;
	}
	
	public int getXpLeftToLevel() {
		return xpLeftToNextLevel;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public void removeCoins(int amount) {
		coins -= amount;
	}
	
	public void addCoins(int amount) {
		coins += amount;
	}
	
	public int getSpecialAttack() {
		return specialAttack;
	}
	
	public int getSpecialAttackCharge() {
		return specialAttackCharge;
	}
	
	public void setSpecialAttackCharge(int charge) {
		this.specialAttackCharge = charge;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
}
