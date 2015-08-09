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
	
	//ItemMenu / MagicMenu
	public static Texture COMBAT_MENU_TEXTURE = new Texture(Gdx.files.internal("itemMenuTexture.png"));
	
	//Items
	public Texture ITEM_POTION = new Texture(Gdx.files.internal("itemPotion.png"));
	public Texture ITEM_BOMB = new Texture(Gdx.files.internal("itemBomb.png"));
	
	//Spells
	public Texture SPELL_HEAL = new Texture(Gdx.files.internal("spellHeal.png"));
	public Texture SPELL_FIRE = new Texture(Gdx.files.internal("spellFire.png"));

}
