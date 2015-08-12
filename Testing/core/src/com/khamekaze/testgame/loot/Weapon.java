package com.khamekaze.testgame.loot;

public class Weapon extends Equipment {
	
	private int damage;

	public Weapon(String name, int level, int damage) {
		super(name, level);
		this.damage = damage;
	}
	
	public int getWeaponDamage() {
		return damage;
	}

}