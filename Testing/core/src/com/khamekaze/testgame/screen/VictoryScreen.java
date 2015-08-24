package com.khamekaze.testgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.MainGame;
import com.khamekaze.testgame.TextureManager;
import com.khamekaze.testgame.entity.Enemy;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.gui.Button;
import com.khamekaze.testgame.location.Location;
import com.khamekaze.testgame.loot.Equipment;
import com.khamekaze.testgame.loot.Item;
import com.khamekaze.testgame.loot.Loot;
import com.khamekaze.testgame.travel.Travel;

public class VictoryScreen extends Screen {
	
	private BitmapFont font;
	private ShapeRenderer shapes;
	private int stepsTaken = 0;
	SpriteBatch batch;
	
	private Button collectButton;
	
	private Loot loot;
	private Player player;
	private String stats;
	
	private Location fromLocation, toLocation;
	private Travel travel;
	private Array<Enemy> enemies;
	
	
	public VictoryScreen(Entity player, Loot loot) {
		this.player = (Player) player;
		stats = "HP: " + Integer.toString(player.getHp()) + " LEVEL: " + Integer.toString(player.getCurrentLevel()) + " ATTACK: " + Integer.toString(player.getAttackDamage()) + 
				" XP: " + Integer.toString(player.getXp()) + " XP TO NEXT LEVEL: " + Integer.toString(player.getXpLeftToLevel()); 
		
	}
	
	public VictoryScreen(Player player, Location fromLocation, Location toLocation, Travel travel, Array<Enemy> enemies, int stepsTaken) {
		this.player = player;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.travel = travel;
		this.enemies = enemies;
		this.stepsTaken = stepsTaken;
		loot = new Loot(this.player);
		
		 
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();  
		font.setColor(Color.BLACK);
		shapes = new ShapeRenderer();
		player.getXpReceived(enemies);
		player.resetActionTime();
		player.resetSpecialAttackCharge();
		stats = "HP: " + Integer.toString(this.player.getHp()) + " LEVEL: " + Integer.toString(this.player.getCurrentLevel()) + " ATTACK: " + Integer.toString(this.player.getAttackDamage()) + 
				" XP: " + Integer.toString(this.player.getXp()) + " XP TO NEXT LEVEL: " + Integer.toString(this.player.getXpLeftToLevel()); 
		
		collectButton = new Button(MainGame.WIDTH / 2 - TextureManager.COLLECT_LOOT.getWidth() / 2,
				MainGame.HEIGHT / 2 + TextureManager.COLLECT_LOOT.getHeight() - 315,
				TextureManager.COLLECT_LOOT, "CollectLootButton");
		
	}

	@Override
	public void update() {
		camera.update();
		inputManager.update();
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		
		sb.setProjectionMatrix(camera.combined);
		shapes.setProjectionMatrix(camera.combined);
		
		sb.begin();
		font.draw(sb, stats, MainGame.WIDTH / 2 - 300, MainGame.HEIGHT / 2);
		showAquiredLoot(sb);
		sb.end();
		
		shapes.begin(ShapeType.Line);
		shapes.setColor(0, 0, 0, 1);
		shapes.rect(inputManager.getMouseHitbox().getX(), inputManager.getMouseHitbox().getY(), 10, 10);
		shapes.end();
	}
	
	public void showAquiredLoot(SpriteBatch sb) {
		for(int i = 0; i < loot.getItems().size; i++) {
			sb.draw(loot.getItems().get(i).getTexture(), MainGame.WIDTH / 2 - 200 + (i * 100), MainGame.HEIGHT / 2);
		}
		
		for(int j = 0; j < loot.getLoot().size; j++) {
//			sb.draw(loot.getLoot().get(j).getTexture(), MainGame.WIDTH / 2 - 200 + (j * 100), MainGame.HEIGHT / 2);
		}
		
		sb.draw(collectButton.getTexture(), collectButton.getX(), collectButton.getY());
	}
	
	public void handleInput() {
		if(inputManager.getMouseHitbox().overlaps(collectButton.getHitbox()) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			ScreenManager.setScreen(new TravelScreen(player, fromLocation, toLocation, stepsTaken));
		}
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
