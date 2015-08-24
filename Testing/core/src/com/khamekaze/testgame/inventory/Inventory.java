package com.khamekaze.testgame.inventory;

import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.loot.Equipment;
import com.khamekaze.testgame.loot.Gear;
import com.khamekaze.testgame.loot.Item;
import com.khamekaze.testgame.loot.Weapon;

public class Inventory {
	
	private Array<Item> items;
	private Array<Gear> gear;
	private Array<Weapon> weapons;

	public Inventory() {
		items = new Array<Item>();
		gear = new Array<Gear>();
		weapons = new Array<Weapon>();
	}
	
	public void addLoot(Array<Item> itemLoot, Array<Equipment> gearLoot) {
		for(Equipment g : gearLoot) {
			if(g instanceof Gear) {
				gear.add((Gear) g);
			} else if(g instanceof Weapon) {
				weapons.add((Weapon) g);
			}
		}
		
		for(Item i : itemLoot) {
			items.add(i);
		}
	}
	
	public Array<Item> getItems() {
		return items;
	}
	
	public Array<Gear> getGear() {
		return gear;
	}
	
	public Array<Weapon> getWeapons() {
		return weapons;
	}
	
}
