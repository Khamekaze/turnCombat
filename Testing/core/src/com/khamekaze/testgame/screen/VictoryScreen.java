package com.khamekaze.testgame.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.testgame.MainGame;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.location.DestinationLocation;
import com.khamekaze.testgame.location.OriginLocation;

public class VictoryScreen extends Screen {
	
	private BitmapFont font;
	SpriteBatch batch;
	
	private Player player;
	private String stats;
	
	
	public VictoryScreen(Entity player) {
		this.player = (Player) player;
		stats = "HP: " + Integer.toString(player.getHp()) + " LEVEL: " + Integer.toString(player.getCurrentLevel()) + " ATTACK: " + Integer.toString(player.getAttackDamage()) + 
				" XP: " + Integer.toString(player.getXp()) + " XP TO NEXT LEVEL: " + Integer.toString(player.getXpLeftToLevel()); 
		
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		System.out.println("HELLO");
		ScreenManager.setScreen(new TravelScreen((Player) player, new OriginLocation("FROM", 0, 0), new DestinationLocation("TO", 1, 1500)));
		System.out.println("HELLO AGAIN");
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		font.draw(sb, stats, MainGame.WIDTH / 2 - 300, MainGame.HEIGHT / 2);
		sb.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

}
