package com.khamekaze.testgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Spell {
	
	private String name;
	private int type, amount;
	private Texture texture;
	private Rectangle hitbox;
	private boolean isSelected = false;
	
	public static final int OFFENSIVE = 0, DEFENSIVE = 1;
	
	public Spell(String name, int type, int amount, Texture texture) {
		this.name = name;
		this.type = type;
		this.amount = amount;
		this.texture = texture;
		hitbox = new Rectangle();
	}
	
	public void selectSpell() {
		if(isSelected)
			isSelected = false;
		else if(!isSelected)
			isSelected = true;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public int getType() {
		return type;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public boolean getIsSelected() {
		return isSelected;
	}
	
	public String getName() {
		return name;
	}

}
