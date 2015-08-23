package com.khamekaze.testgame.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.MainGame;
import com.khamekaze.testgame.TextureManager;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.entity.Item;
import com.khamekaze.testgame.input.InputManager;
import com.khamekaze.testgame.location.Location;
import com.khamekaze.testgame.loot.Equipment;
import com.khamekaze.testgame.loot.Loot;
import com.khamekaze.testgame.loot.LootChest;
import com.khamekaze.testgame.screen.Screen;
import com.khamekaze.testgame.travel.Travel;

public class LootEvent extends Event {
	
	private Loot loot;
	private Entity player;
	private Array<Equipment> equipment;
	private Array<Item> items;
	private Location fromLocation, toLocation;
	private Travel travel;
	private InputManager input;
	private LootChest lootChest;
	private boolean opened = false;

	public LootEvent(Entity player, int stepsTaken, Location fromLocation, Location toLocation) {
		this.player = player;
		loot = new Loot(player);
		equipment = loot.getLoot();
		items = loot.getItems();
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		eventType = Event.LOOT_EVENT;
		input = Screen.inputManager;
		lootChest = new LootChest();
		lootChest.setPos(MainGame.WIDTH / 2 - lootChest.getTexture().getWidth() / 2, MainGame.HEIGHT / 2 - lootChest.getTexture().getHeight() / 2);
	}
	
	public LootEvent(Entity player, Travel travel) {
		this.player = player;
		loot = new Loot(player);
		equipment = loot.getLoot();
		items = loot.getItems();
		this.travel = travel;
		eventType = Event.LOOT_EVENT;
		input = Screen.inputManager;
	}
	
	public void update() {
		if(input.getMouseHitbox().overlaps(lootChest.getHitBox()) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !opened) {
			lootChest.setTexture(TextureManager.CHEST_OPEN);
			opened = true;
		}
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(lootChest.getTexture(), lootChest.getX(), lootChest.getY());
	}
	
	public Rectangle chestHitbox() {
		return lootChest.getHitBox();
	}
	
	public Location getFromLocation() {
		return fromLocation;
	}
	
	public Location getToLocation() {
		return toLocation;
	}
	
}
