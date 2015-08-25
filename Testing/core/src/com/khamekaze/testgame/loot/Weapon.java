package com.khamekaze.testgame.loot;

public class Weapon extends Equipment {
	
	private int damage;

	public Weapon(String name, int level, int damage, int type) {
		super(name, level);
		this.damage = damage;
		texture = textureManager.WEAPON_ICON;
	}
	
	public int getWeaponDamage() {
		return damage;
	}
	
}