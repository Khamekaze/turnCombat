package com.khamekaze.testgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.testgame.screen.MainMenuScreen;
import com.khamekaze.testgame.screen.ScreenManager;

public class MainGame extends ApplicationAdapter {
	
	public static int WIDTH = 800, HEIGHT = 480;
	public static int GAME_WORLD_WIDTH = 100, GAME_WORLD_HEIGHT = 50;
	
	private SpriteBatch batch;
	private BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		ScreenManager.setScreen(new MainMenuScreen());
	}
	
	@Override
	public void dispose() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().dispose();
		batch.dispose();
		font.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().render(batch);
		
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().update();
		
	}
	
	@Override
	public void resize(int width, int height) {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resize(width, height);
		
	}
	
	@Override
	public void resume() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resume();

	}
	
	@Override
	public void pause() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().pause();
		
	}
}

