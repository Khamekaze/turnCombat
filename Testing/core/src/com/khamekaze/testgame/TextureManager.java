package com.khamekaze.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
	
	//Main menu
	public static Texture PLAY_BUTTON = new Texture(Gdx.files.internal("playButton.png"));
	public static Texture TITLE_CARD = new Texture(Gdx.files.internal("titleCard.png"));
	
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
	
	//Event Textures
	public static Texture COMBAT_BANNER = new Texture(Gdx.files.internal("battleEvent.png"));
	public static Texture ENGAGE_BUTTON = new Texture(Gdx.files.internal("engageButton.png"));
	public static Texture FLEE_BUTTON = new Texture(Gdx.files.internal("fleeButton.png"));
	
	public static Texture LOOT_BANNER = new Texture(Gdx.files.internal("lootEvent.png"));
	public static Texture GET_LOOT_BUTTON = new Texture(Gdx.files.internal("getLootButton.png"));
	public static Texture LEAVE_LOOT_BUTTON = new Texture(Gdx.files.internal("leaveItButton.png"));
	
	public static Texture COLLECT_LOOT = new Texture(Gdx.files.internal("collectButton.png"));
	
	public static Texture CHEST_CLOSED = new Texture(Gdx.files.internal("chest.png"));
	public static Texture CHEST_OPEN = new Texture(Gdx.files.internal("chestOpen.png"));

}
