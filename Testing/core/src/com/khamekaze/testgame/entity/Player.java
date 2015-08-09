package com.khamekaze.testgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.TextureManager;

public class Player extends Entity {
	
	private TextureManager textureManager;
	private Texture loader, readyBar;
	private Item lowPotion, lowBomb;
	private Spell lowHeal, lowFire;
	private Array<Item> items;
	private Array<Spell> spells;

	public Player(Vector2 pos, int hp, int attack, int waitTime) {
		super(TextureManager.PLAYER, pos, hp, attack, waitTime);
		textureManager = new TextureManager();
		loader = textureManager.READYLOADER;
		readyBar = textureManager.READYBAR;
		
		//Items
		lowPotion = new Item("Low Potion", Item.DEFENSIVE, 25, textureManager.ITEM_POTION);
		lowBomb = new Item("Low Bomb", Item.OFFENSIVE, 25, textureManager.ITEM_BOMB);
		items = new Array<Item>();
		items.add(lowPotion);
		items.add(lowBomb);
		
		//Spells
		lowHeal = new Spell("Low Heal", Spell.DEFENSIVE, 25, textureManager.SPELL_HEAL);
		lowFire = new Spell("Low Fire", Spell.OFFENSIVE, 25, textureManager.SPELL_FIRE);
		spells = new Array<Spell>();
		spells.add(lowHeal);
		spells.add(lowFire);
	}

	@Override
	public void update() {
		if(passedTime >= waitTime) {
			passedTime = waitTime;
		}
		
		if(passedTime < waitTime) {
			passedTime++;
		}
		
		percentReady = (passedTime * 100) / waitTime;
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y);
		sb.draw(loader, pos.x, pos.y - 20, percentReady, 10);
		sb.draw(readyBar, pos.x, pos.y - 20);
	}
	
	public Array<Item> getItems() {
		return items;
	}
	
	public Array<Spell> getSpells() {
		return spells;
	}

}
