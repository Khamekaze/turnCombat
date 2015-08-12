package com.khamekaze.testgame.event;

import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.loot.Equipment;
import com.khamekaze.testgame.loot.Loot;

public class LootEvent {
	
	private Loot loot;
	private Entity player;
	private Array<Equipment> equipment;

	public LootEvent(Entity player) {
		this.player = player;
		loot = new Loot(player);
		equipment = loot.getLoot();
	}
	
}
