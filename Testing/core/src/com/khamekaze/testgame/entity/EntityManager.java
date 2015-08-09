package com.khamekaze.testgame.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.MainGame;

public class EntityManager {
	
	private final Array<Entity> entities = new Array<Entity>();
	private final Player player;
	private Enemy enemy;

	public EntityManager(int enemies) {
		player = new Player(new Vector2(MainGame.WIDTH - 200, MainGame.HEIGHT / 2 - 150), 75, 10, 150);
		for(int i = 0; i < enemies; i++) {
			enemy = new Enemy(new Vector2(-50, MainGame.HEIGHT / 2 - 175), 150, 10, 225);
			entities.add(enemy);
		}
		entities.add(player);
	}
	
	public void update() {
		for(Entity e : entities) {
			e.update();
		}
	}
	
	public void render(SpriteBatch sb) {
		for(Entity e : entities) {
			e.render(sb);
		}
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public Entity getPlayer() {
		return player;
	}
	
	public Array<Entity> getEntities() {
		return entities;
	}
}
