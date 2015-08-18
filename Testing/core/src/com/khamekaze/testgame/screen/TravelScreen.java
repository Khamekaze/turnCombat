package com.khamekaze.testgame.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.testgame.camera.OrthoCamera;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.location.Location;
import com.khamekaze.testgame.travel.Travel;

public class TravelScreen extends Screen {
	
	private OrthoCamera camera;
	private BitmapFont font;
	private Player player;
	private Travel travel;
	private Location fromLocation, toLocation;
	
	public TravelScreen(Player player, Location fromLocation, Location toLocation) {
		this.player = player;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		travel = new Travel(fromLocation, toLocation, player);
	}

	@Override
	public void create() {
		camera = new OrthoCamera();
		font = new BitmapFont();
	}

	@Override
	public void update() {
		travel.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		travel.render(sb);
	}

	@Override
	public void resize(int width, int height) {
		
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
