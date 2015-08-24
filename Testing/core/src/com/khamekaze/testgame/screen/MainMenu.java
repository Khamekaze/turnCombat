package com.khamekaze.testgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	private float scaleWidth = TextureManager.TITLE_CARD.getWidth() / MainGame.WIDTH, scaleHeight = TextureManager.TITLE_CARD.getHeight() / MainGame.HEIGHT;

	public MainMenu() {
		play = new Button(MainGame.WIDTH / 2 - TextureManager.PLAY_BUTTON.getWidth() / 2,
						  MainGame.HEIGHT / 2 - 200, TextureManager.PLAY_BUTTON, "PlayButton");
		sprite = new Sprite(TextureManager.TITLE_CARD);
		sprite.setSize(MainGame.WIDTH, MainGame.HEIGHT);
		inputManager = Screen.inputManager;
	}
	
	public void update() {
		handleInput();
	}
	
	public void render(SpriteBatch sb) {
		sprite.draw(sb);
		sb.draw(play.getTexture(), play.getX(), play.getY());
	}
	
	public void handleInput() {
		if(inputManager.getMouseHitbox().overlaps(play.getHitbox()) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			Player player = new Player(new Vector2(MainGame.WIDTH - 200, MainGame.HEIGHT / 2 - 150), 75, 10, 150, 1);
			ScreenManager.setScreen(new TravelScreen(player, new OriginLocation("FROM", 0, 0), new DestinationLocation("TO", 1, 1500), 0));
		}
	}
	
}
