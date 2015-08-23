package com.khamekaze.testgame.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.khamekaze.testgame.actions.ActionMenuManager;
import com.khamekaze.testgame.camera.OrthoCamera;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.entity.EntityManager;
import com.khamekaze.testgame.input.InputManager;

public class CombatScreen extends Screen {
	
	private EntityManager entityManager;
	private ActionMenuManager actionMenuManager;
	private ShapeRenderer shapeRenderer;
	
	private int enemies;
	
	public CombatScreen(int enemies) {
		this.enemies = enemies;
	}

	@Override
	public void create() {
		if(enemies != 0)
			entityManager = new EntityManager(enemies);
		else 
			entityManager = new EntityManager(1);
		actionMenuManager = new ActionMenuManager(inputManager, entityManager);
		shapeRenderer = new ShapeRenderer();
	}
	
	@Override
	public void update() {
		camera.update();
		entityManager.update();
		inputManager.update();
		for(Entity e : entityManager.getEntities()) {
			inputManager.getIntersecting(e.getHitbox());
			actionMenuManager.update(e);
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		
		sb.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		sb.begin();
		entityManager.render(sb);
		actionMenuManager.render(sb);
		sb.end();
		
		shapeRenderer.setColor(0, 0, 0, 0);
		shapeRenderer.begin(ShapeType.Line);
			for(Entity e : entityManager.getEntities()) {
				shapeRenderer.rect(e.getHitbox().getX(), e.getHitbox().getY(), e.getHitbox().getWidth(), e.getHitbox().getHeight());
			}
		shapeRenderer.rect(inputManager.getMouseHitbox().getX(), inputManager.getMouseHitbox().getY(), 10, 10);
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
