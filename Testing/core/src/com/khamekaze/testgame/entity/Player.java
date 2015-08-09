package com.khamekaze.testgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.khamekaze.testgame.TextureManager;

public class Player extends Entity {
	
	private TextureManager textureManager;
	private Texture loader, readyBar;

	public Player(Vector2 pos, int hp, int attack, int waitTime) {
		super(TextureManager.PLAYER, pos, hp, attack, waitTime);
		textureManager = new TextureManager();
		loader = textureManager.READYLOADER;
		readyBar = textureManager.READYBAR;
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
		sb.draw(loader, pos.x, pos.y - 20, percentReady, 10);
		sb.draw(readyBar, pos.x, pos.y - 20);
	}

}
