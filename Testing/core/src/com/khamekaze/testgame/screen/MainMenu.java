package com.khamekaze.testgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.khamekaze.testgame.MainGame;
import com.khamekaze.testgame.TextureManager;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.gui.Button;
import com.khamekaze.testgame.input.InputManager;
import com.khamekaze.testgame.location.DestinationLocation;
import com.khamekaze.testgame.location.OriginLocation;

public class MainMenu {
	
	private Button play;
	private InputManager inputManager;
	private Sprite sprite;
	private float centerX, centerY;
//	private float scaleWidth = TextureManager.TITLE_CARD.getWidth() / MainGame.GAME_WORLD_WIDTH, 
//				  scaleHeight = TextureManager.TITLE_CARD.getHeight() / MainGame.GAME_WORLD_HEIGHT;

	public MainMenu() {
		centerX = Screen.camera.getVirtualViewport().getWidth() / 2;
		centerY = Screen.camera.getVirtualViewport().getHeight() / 2;
		
		play = new Button((int) centerX - TextureManager.PLAY_BUTTON.getWidth() / 2,
						  (int) centerY - 200, TextureManager.PLAY_BUTTON, "PlayButton");
		sprite = new Sprite(TextureManager.TITLE_CARD);
		sprite.getTexture().setFilter(TextureFilter.Linear,
                TextureFilter.Linear);
		sprite.setSize(Screen.camera.getVirtualViewport().getWidth(), Screen.camera.getVirtualViewport().getHeight());
		inputManager = Screen.inputManager;
	}
	
	public void update() {
		handleInput();
		updatePositions();
	}
	
	public void render(SpriteBatch sb) {
		sprite.draw(sb);
		play.render(sb);
	}
	
	public void updatePositions() {
		sprite.setSize(Screen.camera.getVirtualViewport().getWidth(), Screen.camera.getVirtualViewport().getHeight());
		centerX = Screen.camera.getVirtualViewport().getWidth() / 2;
		centerY = Screen.camera.getVirtualViewport().getHeight() / 2;
		play.setX(centerX - play.getTexture().getWidth() / 2);
		play.setY(centerY - 200);
	}
	
	public void handleInput() {
		if(inputManager.getMouseHitbox().overlaps(play.getHitbox()) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			Player player = new Player(new Vector2(MainGame.WIDTH - 200, MainGame.HEIGHT / 2 - 150), 75, 10, 150, 1);
			ScreenManager.setScreen(new TravelScreen(player, new OriginLocation("FROM", 0, 0), new DestinationLocation("TO", 1, 1500), 0));
		}
	}
	
}
