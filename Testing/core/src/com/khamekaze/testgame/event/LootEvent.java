package com.khamekaze.testgame.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.MainGame;
import com.khamekaze.testgame.TextureManager;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.gui.Button;
import com.khamekaze.testgame.input.InputManager;
import com.khamekaze.testgame.location.Location;
import com.khamekaze.testgame.loot.Equipment;
import com.khamekaze.testgame.loot.Item;
import com.khamekaze.testgame.loot.Loot;
import com.khamekaze.testgame.loot.LootChest;
import com.khamekaze.testgame.screen.Screen;
import com.khamekaze.testgame.screen.ScreenManager;
import com.khamekaze.testgame.screen.TravelScreen;
import com.khamekaze.testgame.travel.Travel;

public class LootEvent extends Event {
	
	private Loot loot;
	private Player player;
	private Array<Equipment> equipment;
	private Array<Item> items;
	private Location fromLocation, toLocation;
	private Travel travel;
	private InputManager input;
	private LootChest lootChest;
	private boolean opened = false, collected = false;
	private int waitTime = 30;
	private Button collectButton;
	private int stepsTaken;

	public LootEvent(Player player, Location fromLocation, Location toLocation, Travel travel) {
		this.player = player;
		loot = new Loot(player);
		this.travel = travel;
		equipment = loot.getLoot();
		items = loot.getItems();
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		eventType = Event.LOOT_EVENT;
		input = Screen.inputManager;
		lootChest = new LootChest();
		lootChest.setPos(MainGame.WIDTH / 2 - lootChest.getTexture().getWidth() / 2, MainGame.HEIGHT / 2 - lootChest.getTexture().getHeight() / 2);
		
		collectButton = new Button(MainGame.WIDTH / 2 - TextureManager.COLLECT_LOOT.getWidth() / 2,
				MainGame.HEIGHT / 2 + TextureManager.COLLECT_LOOT.getHeight() - 315,
				TextureManager.COLLECT_LOOT, "CollectLootButton");
		
		System.out.println(items.size);
		System.out.println(equipment.size);
	}
	
//	public LootEvent(Player player, Travel travel) {
//		this.player = player;
//		loot = new Loot(player);
//		equipment = loot.getLoot();
//		items = loot.getItems();
//		this.travel = travel;
//		eventType = Event.LOOT_EVENT;
//		input = Screen.inputManager;
//	}
	
	public void update() {
		if(waitTime > 0) {
			waitTime--;
		} else if(waitTime < 0) {
			waitTime = 0;
		}
		
		if(input.getMouseHitbox().overlaps(lootChest.getHitBox()) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !opened && waitTime == 0) {
			lootChest.setTexture(TextureManager.CHEST_OPEN);
			opened = true;
			waitTime = 30;
		}
		
		if(opened && input.getMouseHitbox().overlaps(collectButton.getHitbox()) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) &&
				!collected && waitTime == 0) {
			getLoot();
			collected = true;
		}
		
		if(collected) {
			ScreenManager.setScreen(new TravelScreen(player, fromLocation, toLocation, stepsTaken));
			collected = false;
		}
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(lootChest.getTexture(), lootChest.getX(), lootChest.getY());
		if(opened) {
			showLoot(sb);
		}
	}
	
	public void showLoot(SpriteBatch sb) {
		
		for(int i = 0; i < equipment.size; i++) {
			
		}
		
		for(int j = 0; j < items.size; j++) {
			sb.draw(items.get(j).getTexture(), MainGame.WIDTH / 2 - 200 + (j * 100), MainGame.HEIGHT / 2);
		}
		
		sb.draw(collectButton.getTexture(), collectButton.getX(), collectButton.getY());
	}
	
	public void getLoot() {
		player.getInventory().addLoot(items, equipment);
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
	
	public void setStepsTaken(int steps) {
		this.stepsTaken = steps;
	}
	
}
