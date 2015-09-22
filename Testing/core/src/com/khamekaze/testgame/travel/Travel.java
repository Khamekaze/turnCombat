package com.khamekaze.testgame.travel;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.MainGame;
import com.khamekaze.testgame.TextureManager;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.event.CombatEvent;
import com.khamekaze.testgame.event.Event;
import com.khamekaze.testgame.event.LootEvent;
import com.khamekaze.testgame.gui.Button;
import com.khamekaze.testgame.input.InputManager;
import com.khamekaze.testgame.location.Location;
import com.khamekaze.testgame.screen.CombatScreen;
import com.khamekaze.testgame.screen.LootScreen;
import com.khamekaze.testgame.screen.Screen;
import com.khamekaze.testgame.screen.ScreenManager;

public class Travel {
	
	private int stepsTaken, stepsToDestination, stepsLeft, locationOfEvent, distanceToEvent;
	private float delta = 0, second = 0, centerX = Screen.camera.getVirtualViewport().getWidth() / 2,
				   centerY = Screen.camera.getVirtualViewport().getHeight() / 2;
	private Random rand;
	private Location fromLocation, toLocation;
	private Entity player;
	private boolean eventInitiated = false, flipped;
	private BitmapFont font = new BitmapFont();
	private Event currentEvent;
	private Button getLootButton, leaveLootButton, engageButton, fleeButton;
	private InputManager inputManager;
	private Array<Button> buttons = new Array<Button>();
	private Array<Texture> bushes = new Array<Texture>();
	private Texture bush = new Texture(Gdx.files.internal("bush.png"));
	private Rectangle bushBox = new Rectangle();
	
	
	public Travel(Location fromLocation, Location toLocation, Entity player) {
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.player = player;
		distanceToEvent = 0;
		inputManager = Screen.inputManager;
		rand = new Random();
		stepsTaken = 0;
		stepsToDestination = fromLocation.calculateDistanceFromLocation(toLocation);
		locationOfEvent = stepsToDestination;
		currentEvent = null;
		loadButtons();
		
		this.player.getSprite().setCenter(MainGame.WIDTH / 2, MainGame.HEIGHT / 2 - 100);
		
		generateEvent();
		
		System.out.println("AFTER");
	}
	
	public Travel(Location fromLocation, Location toLocation, Entity player, int stepsTaken) {
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.player = player;
		this.stepsTaken = stepsTaken;
		inputManager = Screen.inputManager;
		stepsToDestination = fromLocation.calculateDistanceFromLocation(toLocation) - stepsTaken;
		currentEvent = null;
		rand = new Random();
		distanceToEvent = 0;
		
		bushBox.set(MainGame.WIDTH, 100, bush.getWidth(), bush.getHeight());
		
		
		player.getSprite().setCenter(centerX, centerY - 100);
		
		loadButtons();
		
		generateEvent();
		
		System.out.println("COINS: " + player.getCoins());
	}
	
	public void update() {
		
		delta = Gdx.graphics.getDeltaTime();
		
		handleInput();
		
		updatePositions();
		
		if(currentEvent != null && !eventInitiated) {
			if(second <= 1)
				second += delta;
			else if(second > 1)
				second = 1;
			
			if(second == 1 && distanceToEvent > 0) {
				stepsTaken++;
				stepsToDestination--;
				distanceToEvent--;
				second = 0;
				System.out.println(stepsToDestination);
			}
			
			if(distanceToEvent == 0) {
				eventInitiated = true;
			}
		
		} else if(eventInitiated) {
			second += delta;
			
			if(second >= 60) {
				generateEvent();
				eventInitiated = false;
			}
		}
		
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(TextureManager.TRAVEL_BACKGROUND, 0, 0);
		updateAndRenderBushes(sb);
		player.getSprite().draw(sb);
		
		font.draw(sb, String.valueOf(stepsTaken), MainGame.WIDTH / 2 - 50, 400);
		
		if(eventInitiated) {
			if(currentEvent instanceof CombatEvent) {
				sb.draw(TextureManager.COMBAT_BANNER, centerX - TextureManager.COMBAT_BANNER.getWidth() / 2,
						centerY + 100);
				engageButton.render(sb);
				fleeButton.render(sb);
			} else if(currentEvent instanceof LootEvent) {
				sb.draw(TextureManager.LOOT_BANNER, centerX - TextureManager.LOOT_BANNER.getWidth() / 2,
						centerY + (100));
				getLootButton.render(sb);
				leaveLootButton.render(sb);
			}
		} else {
			
		}
		
		
	}
	
	public void updateAndRenderBushes(SpriteBatch sb) {
		bushBox.setX(bushBox.getX() - 1f);
		sb.draw(bush, bushBox.x, bushBox.y);
		
		if(bushBox.getX() < 0 - bushBox.getWidth())
			bushBox.setX(MainGame.WIDTH);
	}
	
	public void loadButtons() {
		getLootButton = new Button(MainGame.WIDTH / 2 - 47 - TextureManager.GET_LOOT_BUTTON.getWidth(), MainGame.HEIGHT / 2 + TextureManager.GET_LOOT_BUTTON.getHeight() - 115,
				TextureManager.GET_LOOT_BUTTON, "GetLootButton");
		leaveLootButton = new Button(MainGame.WIDTH / 2 + 47, MainGame.HEIGHT / 2 + TextureManager.LEAVE_LOOT_BUTTON.getHeight() - 115,
				TextureManager.LEAVE_LOOT_BUTTON, "LeaveLootButton");


		engageButton = new Button(MainGame.WIDTH / 2 - 47 - TextureManager.ENGAGE_BUTTON.getWidth(), MainGame.HEIGHT / 2 + TextureManager.ENGAGE_BUTTON.getHeight() - 115,
				TextureManager.ENGAGE_BUTTON, "EngageButton");
		fleeButton = new Button(MainGame.WIDTH / 2 + 47, MainGame.HEIGHT / 2 + TextureManager.FLEE_BUTTON.getHeight() - 115,
				TextureManager.FLEE_BUTTON, "FleeButton");
		
		buttons.add(getLootButton);
		buttons.add(leaveLootButton);
		buttons.add(engageButton);
		buttons.add(fleeButton);
	}
	
	public void generateEvent() {
		eventInitiated = false;
		currentEvent = null;
		int typeOfEvent = rand.nextInt(2);
//		int typeOfEvent = 1;
		distanceToEvent = rand.nextInt(100);
//		distanceToEvent = 1;
		Event event = null;
		if(typeOfEvent == Event.COMBAT_EVENT) {
			event = new CombatEvent();
		} else if(typeOfEvent == Event.LOOT_EVENT) {
			event = new LootEvent((Player) player, fromLocation, toLocation, this);
		}
		currentEvent = event;
		locationOfEvent = stepsToDestination - distanceToEvent;
		currentEvent.setEventLocation(locationOfEvent);
		event = null;
		System.out.println("EVENT CREATED!");
		System.out.println("ID: " + currentEvent.getEventType());
		System.out.println("LOCATION: " + currentEvent.getEventLocation());
	}
	
	public void updatePositions() {
		player.getSprite().setCenter(centerX, centerY - 100);
		centerX = Screen.camera.getVirtualViewport().getWidth() / 2;
		centerY = Screen.camera.getVirtualViewport().getHeight() / 2;
		getLootButton.setX(centerX - getLootButton.getHitbox().getWidth() - 48);
		getLootButton.setY(centerY + 22);
		leaveLootButton.setX(centerX + 48);
		leaveLootButton.setY(centerY + 22);
		
		engageButton.setX(centerX - engageButton.getHitbox().getWidth() - 48);
		engageButton.setY(centerY + 22);
		fleeButton.setX(centerX + 48);
		fleeButton.setY(centerY + 22);
	}
	
	public void handleInput() {
		for(Button b : buttons) {
			if(currentEvent instanceof LootEvent && eventInitiated) {
				if(inputManager.getMouseHitbox().overlaps(b.getHitbox()) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					if(b.getName() == "GetLootButton") {
						LootEvent lootEvent = (LootEvent) currentEvent;
						currentEvent = null;
						lootEvent.setStepsTaken(stepsTaken);
						ScreenManager.setScreen(new LootScreen(player, lootEvent));
					} else if(b.getName() == "LeaveLootButton") {
						generateEvent();
					}
				}
			} else if(currentEvent instanceof CombatEvent && eventInitiated) {
				if(inputManager.getMouseHitbox().overlaps(b.getHitbox()) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					if(b.getName() == "EngageButton") {
						ScreenManager.setScreen(new CombatScreen((Player) player, fromLocation, toLocation, this, 1, stepsTaken));
					} else if(b.getName() == "FleeButton") {
						generateEvent();
						eventInitiated = false;
					}
				}
			}
		}
	}
	
	public boolean getEventInitiated() {
		return eventInitiated;
	}
	
	public void setEventInitiated(boolean initiated) {
		this.eventInitiated = initiated;
	}

}
