package com.khamekaze.testgame.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.event.LootEvent;

public class LootScreen extends Screen {
	
	private Entity player;
	private LootEvent lootEvent;
	private ShapeRenderer shapeRenderer;
	
	
	public LootScreen(Entity player, LootEvent lootEvent) {
		this.player = player;
		this.lootEvent = lootEvent;
	}

	@Override
	public void create() {
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void update() {
		camera.update();
		inputManager.update();
		lootEvent.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		
		sb.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		sb.begin();
		lootEvent.render(sb);
		sb.end();
		
		shapeRenderer.setColor(0, 0, 0, 1);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.rect(inputManager.getMouseHitbox().getX(), inputManager.getMouseHitbox().getY(), 10, 10);
		shapeRenderer.rect(lootEvent.chestHitbox().getX(), lootEvent.chestHitbox().getY(), lootEvent.chestHitbox().width, lootEvent.chestHitbox().height);
		shapeRenderer.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.resize();
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
