package com.khamekaze.testgame.loot;

public class Gear extends Equipment{

	public Gear(String name, int level, int type) {
		super(name, level);
		texture = textureManager.GEAR_ICON;
	}
	
}
