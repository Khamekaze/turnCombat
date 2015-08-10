package com.khamekaze.testgame.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.khamekaze.testgame.TextureManager;

public class Enemy extends Entity {
	
	private TextureManager textureManager;
	private Texture loader, readyBar;

	public Enemy(Vector2 pos, int hp, int attack, int waitTime, int level) {
		super(TextureManager.ENEMY, pos, hp, attack, waitTime, level);
		textureManager = new TextureManager();
		readyBar = textureManager.READYBAR;
		loader = textureManager.READYLOADER;
	}

	@Override
	public void update() {
		if(passedTime >= waitTime) {
			passedTime = waitTime;
		}
		
		if(!getWaitingForAction()) {
			if(passedTime < waitTime) {
				passedTime++;
			}
		}
		
		percentReady = (passedTime * 100) / waitTime;
		
		if(percentReady >= 100) {
			setAtbFull(true);
		} else {
			setAtbFull(false);
		}
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y);
		sb.draw(loader, pos.x + 300, pos.y - 5, percentReady, 10);
		sb.draw(readyBar, pos.x + 300, pos.y - 5);
		font.setColor(Color.BLACK);
		font.draw(sb, Integer.toString(hp) + "/" + Integer.toString(maxHp), pos.x + hitBox.width, pos.y + hitBox.height + 10);
	}

}
