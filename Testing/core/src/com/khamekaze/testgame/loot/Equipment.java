package com.khamekaze.testgame.loot;

public abstract class Equipment {
	
	public static final int TYPE_WEAPON = 0, TYPE_GEAR = 1, TYPE_ITEM = 2;

	protected String name;
	protected int level;
	
	public Equipment(String name, int level) {
		this.name = name;
		this.level = level;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLevel() {
		return level;
	}
	
}
