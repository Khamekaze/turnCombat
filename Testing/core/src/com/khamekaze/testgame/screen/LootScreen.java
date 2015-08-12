package com.khamekaze.testgame.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.event.LootEvent;

public class LootScreen extends Screen {
	
	private Entity player;
	private LootEvent lootEvent;
	
	public LootScreen(Entity player) {
		this.player = player;
		lootEvent = new LootEvent(player);
	}

	@Override
	public void create() {

	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch sb) {
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}
	
}
