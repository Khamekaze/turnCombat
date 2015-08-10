package com.khamekaze.testgame.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.MainGame;
import com.khamekaze.testgame.entity.Enemy;
import com.khamekaze.testgame.entity.Entity;

public class CombatOver {
	
	public static final int PLAYER_WINS = 0, ENEMY_WINS = 1;
	
	private BitmapFont font;
	private Entity entity;
	
	private int winner;
	
	public CombatOver(int winner, Entity entity) {
		this.winner = winner;
		this.entity = entity;
		font = new BitmapFont();
	}
	
	public void render(SpriteBatch sb, Array<Enemy> entities) {
		switch(winner) {
		
		case PLAYER_WINS:
			font.draw(sb, "VICTORY!", MainGame.WIDTH / 2, MainGame.HEIGHT / 2);
			entity.getXpReceived(entities);
			entity.calculateXpToLevel();
			entity.adjustStats();
			ScreenManager.setScreen(new VictoryScreen(entity));
			break;
			
		case ENEMY_WINS:
			font.draw(sb, "DEFEAT!", MainGame.WIDTH / 2, MainGame.HEIGHT / 2);
			break;
			
		default:
			break;
			
		}
	}
	
}
