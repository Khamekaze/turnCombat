package com.khamekaze.testgame.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.MainGame;
import com.khamekaze.testgame.screen.CombatOver;

public class EntityManager {
	
	private final Array<Entity> entities = new Array<Entity>();
	private Array<Enemy> enemiesArr = new Array<Enemy>();
	private final Player player;
	private Enemy enemy;
	private CombatOver over;
	private boolean fightOver = false;

	public EntityManager(int enemies) {
		player = new Player(new Vector2(MainGame.WIDTH - 200, MainGame.HEIGHT / 2 - 150), 75, 10, 150, 1);
		for(int i = 0; i < enemies; i++) {
			enemy = new Enemy(new Vector2(-50, MainGame.HEIGHT / 2 - 175), 150, 20, 200, 2);
			entities.add(enemy);
			enemiesArr.add(enemy);
		}
		entities.add(player);
	}
	
	public void update() {
		if(!fightOver) {
			for(Entity e : entities) {
				e.update();
				e.hasAttacked = false;
				if(e.getAtbFull()){
					player.setWaitingForAction(true);
					for(Enemy enemy : enemiesArr) {
						enemy.setWaitingForAction(true);
					}
					if(e instanceof Enemy && !e.hasAttacked) {
						System.out.println("Enemy Attacking");
						e.attack(player);
						player.setWaitingForAction(false);
						player.hasAttacked = false;
						for(Enemy enemy : enemiesArr) {
							enemy.setWaitingForAction(false);
							enemy.resetActionTime();
						}
					}
				} else if(e.hp <= 0) {
					if(e instanceof Enemy) {
						over = new CombatOver(CombatOver.PLAYER_WINS, player);
						fightOver = true;
					} else if(e instanceof Player) {
						over = new CombatOver(CombatOver.ENEMY_WINS, e);
						fightOver = true;
					}
				} else {
					player.setWaitingForAction(false);
					player.hasAttacked = true;
					for(Enemy enemy : enemiesArr) {
						enemy.setWaitingForAction(false);
						enemy.hasAttacked = true;
					}
				}
			}
		}
	}
	
	public void render(SpriteBatch sb) {
		for(Entity e : entities) {
			e.render(sb);
		}
		
		if(over != null) {
			over.render(sb, enemiesArr);
		}
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Array<Entity> getEntities() {
		return entities;
	}
	
	public Array<Enemy> getEnemies() {
		return enemiesArr;
	}
	
	public boolean getFightOver() {
		return fightOver;
	}
	
	public void setFightOver(boolean fightOver) {
		this.fightOver = fightOver;
	}
}
