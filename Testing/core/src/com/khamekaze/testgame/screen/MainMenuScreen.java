package com.khamekaze.testgame.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen extends Screen {
	
	private MainMenu mainMenu;

	@Override
	public void create() {
		mainMenu = new MainMenu();
	}

	@Override
	public void update() {
		camera.update();
		inputManager.update();
		mainMenu.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		mainMenu.render(sb);
		sb.end();
		
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
