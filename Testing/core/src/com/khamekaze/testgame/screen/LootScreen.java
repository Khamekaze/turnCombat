package com.khamekaze.testgame.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.testgame.MainGame;
import com.khamekaze.testgame.TextureManager;
import com.khamekaze.testgame.camera.OrthoCamera;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.event.LootEvent;
import com.khamekaze.testgame.input.InputManager;

public class LootScreen extends Screen {
	
	private Entity player;
	private LootEvent lootEvent;
	private InputManager inputManager;
	private OrthoCamera camera;
	private Texture chest;
	
	public LootScreen(Entity player, LootEvent lootEvent) {
		this.player = player;
		this.lootEvent = lootEvent;
		chest = TextureManager.CHEST;
	}

	@Override
	public void create() {
		camera = new OrthoCamera();
		inputManager = new InputManager(camera);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(chest, MainGame.WIDTH / 2 - chest.getWidth() / 2, MainGame.HEIGHT / 2 - chest.getHeight() / 2);
		sb.end();
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
