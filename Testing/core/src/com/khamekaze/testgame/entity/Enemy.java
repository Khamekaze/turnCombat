package com.khamekaze.testgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.khamekaze.testgame.TextureManager;

public class Enemy extends Entity {
	
	private TextureManager textureManager;
	private Texture loader, readyBar;

	public Enemy(Vector2 pos, int hp, int attack, int waitTime) {
		super(TextureManager.ENEMY, pos, hp, attack, waitTime);
		textureManager = new TextureManager();
		readyBar = textureManager.READYBAR;
		loader = textureManager.READYLOADER;
	}

	@Override
	public void update() {
		if(passedTime >= waitTime) {
			passedTime = waitTime;
		}
		
		if(passedTime < waitTime) {
			passedTime++;
		}
		
		percentReady = (passedTime * 100) / waitTime;
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y);
		sb.draw(loader, pos.x + 300, pos.y - 5, percentReady, 10);
		sb.draw(readyBar, pos.x + 300, pos.y - 5);
	}

}
