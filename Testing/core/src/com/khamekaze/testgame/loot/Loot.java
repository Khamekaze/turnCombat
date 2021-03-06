package com.khamekaze.testgame.loot;

import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.TextureManager;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.entity.Player;

public class Loot {
	
	private Array<Equipment> loot;
	private Array<Item> items;
	private int amount = 0;
	private Player player;
	private Random rand = new Random();
	private TextureManager textureManager;
	private int amountOfCoins = 0;
	
	public Loot(Player player) {
		textureManager = new TextureManager();
		this.player = player;
		loot = new Array<Equipment>();
		items = new Array<Item>();
		generateLoot(player.getCurrentLevel());
	}
	
	public void generateLoot(int level) {
		int amountOfLoot = rand.nextInt(3);
		for(int i = 0; i < amountOfLoot; i++) {
			Equipment eq = null;
			int type = rand.nextInt(amountOfLoot);
			if(i == type) {
				int weaponType = rand.nextInt(4);
				eq = new Weapon("Sword", player.getCurrentLevel(), 10 + (player.getCurrentLevel() * i), weaponType);
			} else {
				int gearType = rand.nextInt(4);
				eq = new Gear("Gear", player.getCurrentLevel(), gearType);
			}
			loot.add(eq);
		}
		
		int amountOfItems = rand.nextInt(5);
		for(int i = 0; i < amountOfItems; i++) {
			Item item = null;
			int itemType = rand.nextInt(2);
			if(itemType == Item.OFFENSIVE) {
				item = new Item("OFFENSIVE", itemType, determineAmount(), textureManager.ITEM_BOMB);
				items.add(item);
			} else if(itemType == Item.DEFENSIVE) {
				item = new Item("DEFENSIVE", itemType, determineAmount(), textureManager.ITEM_POTION);
				items.add(item);
			}
		}
		
		amountOfCoins = rand.nextInt(100 * player.getCurrentLevel());
	}
	
	public int determineAmount() {
		if(player.getCurrentLevel() < 10) {
			amount = 25;
		} else if(player.getCurrentLevel() >= 10 && player.getCurrentLevel() < 20) {
			amount = 75;
		} else if(player.getCurrentLevel() >= 20 && player.getCurrentLevel() < 30) {
			amount = 150;
		}
		
		return amount;
	}
	
	public Array<Equipment> getLoot() {
		return loot;
	}
	
	public Array<Item> getItems() {
		return items;
	}
	
	public int getAmountOfCoins() {
		return amountOfCoins;
	}

}
