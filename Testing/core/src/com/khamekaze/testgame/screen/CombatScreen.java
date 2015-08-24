package com.khamekaze.testgame.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.khamekaze.testgame.actions.ActionMenuManager;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.entity.EntityManager;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.location.Location;
import com.khamekaze.testgame.travel.Travel;

public class CombatScreen extends Screen {
	
	private EntityManager entityManager;
	private ActionMenuManager actionMenuManager;
	private ShapeRenderer shapeRenderer;
	
	private Player player;
	private Location fromLocation, toLocation;
	private Travel travel;
	
	private int enemies, stepsTaken = 0;
	
	public CombatScreen(int enemies) {
		this.enemies = enemies;
	}
	
	public CombatScreen(Player player, Location fromLocation, Location toLocation, Travel travel, int enemies, int stepsTaken) {
		this.player = player;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.travel = travel;
		this.enemies = enemies;
		this.stepsTaken = stepsTaken;
	}

	@Override
	public void create() {
		if(enemies != 0 && player == null) {
			entityManager = new EntityManager(enemies);
			player = entityManager.getPlayer();
		} else if(enemies == 0 && player == null) {
			entityManager = new EntityManager(1);
			player = entityManager.getPlayer();
		} else if(player != null && enemies != 0) {
			entityManager = new EntityManager(player, enemies);
		} else if(player != null && enemies == 0) {
			entityManager = new EntityManager(player, 1);
		}
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
		
		if(entityManager.getFightOver() && entityManager.getPlayerWins()) {
			ScreenManager.setScreen(new VictoryScreen(player, fromLocation, toLocation, travel, entityManager.getEnemies(), stepsTaken));
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
	
	
	public void setStepsTaken(int steps) {
		this.stepsTaken = steps;
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
