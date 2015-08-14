package com.khamekaze.testgame.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.MainGame;
import com.khamekaze.testgame.entity.Enemy;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.loot.Loot;

public class CombatOver {
	
	public static final int PLAYER_WINS = 0, ENEMY_WINS = 1;
	
	private BitmapFont font;
	private Player player;
	private Loot loot;
	
	private int winner;
	
	public CombatOver(int winner, Entity entity) {
		this.winner = winner;
		font = new BitmapFont();
		if(entity instanceof Player) {
			this.player = (Player) entity;
			loot = new Loot(player);
			player.addEquipmentToInventory(loot.getLoot());
			player.addItemToInventory(loot.getItems());
		}
	}
	
	public void render(SpriteBatch sb, Array<Enemy> entities) {
		switch(winner) {
		
		case PLAYER_WINS:
			font.draw(sb, "VICTORY!", MainGame.WIDTH / 2, MainGame.HEIGHT / 2);
			player.getXpReceived(entities);
			player.calculateXpToLevel();
			player.adjustStats();
			ScreenManager.setScreen(new VictoryScreen(player));
			break;
			
		case ENEMY_WINS:
			font.draw(sb, "DEFEAT!", MainGame.WIDTH / 2, MainGame.HEIGHT / 2);
			break;
			
		default:
			break;
			
		}
	}
	
}
