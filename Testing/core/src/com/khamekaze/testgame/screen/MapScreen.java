package com.khamekaze.testgame.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.testgame.location.Map;

public class MapScreen extends Screen {
	
	private Map map;

	@Override
	public void create() {
		map = new Map();
	}

	@Override
	public void update() {
		camera.update();
		map.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		
		sb.begin();
		map.render(sb);
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
