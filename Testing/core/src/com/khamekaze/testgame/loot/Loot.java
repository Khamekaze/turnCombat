package com.khamekaze.testgame.loot;

import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.entity.Entity;

public class Loot {
	
	private Array<Equipment> loot;
	private Entity player;
	private Random rand = new Random();
	
	public Loot(Entity player) {
		this.player = player;
		loot = new Array<Equipment>();
		generateLoot(player.getCurrentLevel());
		for(Equipment e : loot) {
			System.out.println(e.name + " " + e.level);
		}
	}
	
	public void generateLoot(int level) {
		int amountOfLoot = rand.nextInt(10);
		for(int i = 0; i < amountOfLoot; i++) {
			Equipment eq = null;
			int type = rand.nextInt(amountOfLoot);
			if(i == type) {
				eq = new Weapon("Sword", player.getCurrentLevel(), 10 + (player.getCurrentLevel() * i));
			} else {
				eq = new Gear("Item", player.getCurrentLevel());
			}
			loot.add(eq);
		}
	}
	
	public Array<Equipment> getLoot() {
		return loot;
	}

}
