package com.khamekaze.testgame.loot;

public class Gear extends Equipment{

	public Gear(String name, int level, int type) {
		super(name, level);
		determineTexture(type);
	}
	
	public void determineTexture(int type) {
		if(type == 0) {
			
		} else if(type == 1) {
			
		} else if(type == 2) {
			
		}
	}
	
}
