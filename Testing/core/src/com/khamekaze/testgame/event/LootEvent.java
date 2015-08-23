package com.khamekaze.testgame.event;

import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.entity.Item;
import com.khamekaze.testgame.loot.Equipment;
import com.khamekaze.testgame.loot.Loot;

public class LootEvent extends Event {
	
	private Loot loot;
	private Entity player;
	private Array<Equipment> equipment;
	private Array<Item> items;

	public LootEvent(Entity player) {
		this.player = player;
		loot = new Loot(player);
		equipment = loot.getLoot();
		items = loot.getItems();
		eventType = Event.LOOT_EVENT;
	}
	
}
