package com.khamekaze.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
	
	//Entities
	public static Texture PLAYER = new Texture(Gdx.files.internal("character1.png"));
	public static Texture ENEMY = new Texture(Gdx.files.internal("creature1.png"));
	
	//ActionBar loader
	public Texture READYBAR = new Texture(Gdx.files.internal("readyBar.png"));
	public Texture READYLOADER = new Texture(Gdx.files.internal("readyLoader.png"));
	
	//ActionMenu
	public static Texture OPEN = new Texture(Gdx.files.internal("open.png"));
	public Texture MENU_BUTTON = new Texture(Gdx.files.internal("menuButton.png"));

}
