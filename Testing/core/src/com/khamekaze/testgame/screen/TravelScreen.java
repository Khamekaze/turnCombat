package com.khamekaze.testgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.khamekaze.testgame.camera.OrthoCamera;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.input.InputManager;
import com.khamekaze.testgame.location.Location;
import com.khamekaze.testgame.travel.Travel;

public class TravelScreen extends Screen {
	
	private BitmapFont font;
	private Player player;
	private Travel travel;
	private Location fromLocation, toLocation;
	private ShapeRenderer shapeRenderer;
	private int stepsTaken = 0;
	
	public TravelScreen(Player player, Location fromLocation, Location toLocation, int stepsTaken) {
		this.player = player;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.stepsTaken = stepsTaken;
	}
	
	public TravelScreen(Player player, Location fromLocation, Location toLocation, Travel travel, int stepsTaken) {
		this.player = player;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.travel = travel;
		this.stepsTaken = stepsTaken;
	}

	@Override
	public void create() {
		
		shapeRenderer = new ShapeRenderer();
		
		travel = new Travel(fromLocation, toLocation, player, stepsTaken);
			
		font = new BitmapFont();
	}

	@Override
	public void update() {
		camera.update();
		inputManager.update();
		travel.update();
		
//		System.out.println("X: " + inputManager.getMouseHitbox().getX());
	}

	@Override
	public void render(SpriteBatch sb) {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		sb.begin();
		travel.render(sb);
		sb.end();
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.rect(inputManager.getMouseHitbox().getX(), inputManager.getMouseHitbox().getY(), 10, 10);
		shapeRenderer.end();
		
		
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
