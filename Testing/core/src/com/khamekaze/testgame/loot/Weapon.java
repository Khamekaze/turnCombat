package com.khamekaze.testgame.loot;

public class Weapon extends Equipment {
	
	private int damage;

	public Weapon(String name, int level, int damage, int type) {
		super(name, level);
		this.damage = damage;
	}
	
	public int getWeaponDamage() {
		return damage;
	}
	
	public void determineTexture(int type) {
		if(type == 0) {
			
		} else if(type == 1) {
			
		} else if(type == 2) {
			
		}
	}

}