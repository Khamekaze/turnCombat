package com.khamekaze.testgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Item {
	
	public static final int OFFENSIVE = 0, DEFENSIVE = 1;
	
	private int type;
	private int amount;
	private Texture texture;
	private Rectangle hitbox;
	private String name;
	private boolean isSelected = false;
	
	public Item(String name, int type, int amount, Texture texture) {
		this.name = name;
		this.type = type;
		this.amount = amount;
		this.texture = texture;
		hitbox = new Rectangle();
	}
	
	public void selectItem() {
		if(isSelected)
			isSelected = false;
		else if(!isSelected)
			isSelected = true;
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
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getIsSelected() {
		return isSelected;
	}
	
	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
