package com.khamekaze.testgame.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.TextureManager;
import com.khamekaze.testgame.inventory.Inventory;
import com.khamekaze.testgame.loot.Item;

public class Player extends Entity {
	
	private TextureManager textureManager;
	private Texture loader, readyBar;
	private Item lowPotion, lowBomb;
	private Spell lowHeal, lowFire;
	private Array<Spell> spells;
	private Inventory inventory;
	

	public Player(Vector2 pos, int hp, int attack, int waitTime, int level) {
		super(TextureManager.PLAYER, pos, hp, attack, waitTime, level);
		textureManager = new TextureManager();
		loader = textureManager.READYLOADER;
		readyBar = textureManager.READYBAR;
		
		inventory = new Inventory();
		
		//Items
		lowPotion = new Item("Low Potion", Item.DEFENSIVE, 25, textureManager.ITEM_POTION);
		lowBomb = new Item("Low Bomb", Item.OFFENSIVE, 25, textureManager.ITEM_BOMB);
		inventory.getItems().add(lowPotion);
		inventory.getItems().add(lowBomb);
		
		//Equipment
		
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
		
		if(!getWaitingForAction()) {
			if(passedTime < waitTime) {
				passedTime++;
			}
		}
		
		percentReady = (passedTime * 100) / waitTime;
		
		if(percentReady >= 100) {
			setAtbFull(true);
		} else {
			setAtbFull(false);
		}
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y);
		sb.draw(loader, pos.x, pos.y - 20, percentReady, 10);
		sb.draw(readyBar, pos.x, pos.y - 20);
		font.setColor(Color.BLACK);
		font.draw(sb, Integer.toString(hp) + "/" + Integer.toString(maxHp), pos.x + hitBox.width, pos.y + hitBox.height + 10);
	}
	
	public Array<Spell> getSpells() {
		return spells;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

}
