package com.khamekaze.testgame.travel;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	private float delta = 0, second = 0;
	private Random rand;
	private Location fromLocation, toLocation;
	private Entity player;
	private boolean eventInitiated = false, flipped;
	private BitmapFont font = new BitmapFont();
	private Event currentEvent;
	private Button getLootButton, leaveLootButton, engageButton, fleeButton;
	private InputManager inputManager;
	private Array<Button> buttons = new Array<Button>();
	
	
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
		
//		player.getSprite().flip(flipped, false);
		player.getSprite().setCenter(MainGame.WIDTH / 2, MainGame.HEIGHT / 2 - 100);
		
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
		
		
//		player.getSprite().flip(flipped, false);
		player.getSprite().setCenter(MainGame.WIDTH / 2, MainGame.HEIGHT / 2 - 100);
		
		loadButtons();
		
		generateEvent();
		
		
	}
	
	public void update() {
		
		delta = Gdx.graphics.getDeltaTime();
		
		handleInput();
		
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
		player.getSprite().draw(sb);
		
		if(eventInitiated) {
			if(currentEvent instanceof CombatEvent) {
				sb.draw(TextureManager.COMBAT_BANNER, MainGame.WIDTH / 2 - TextureManager.COMBAT_BANNER.getWidth() / 2,
						MainGame.HEIGHT / 2 + TextureManager.COMBAT_BANNER.getHeight() / 2);
				sb.draw(engageButton.getTexture(), engageButton.getX(), engageButton.getY());
				sb.draw(fleeButton.getTexture(), fleeButton.getX(), fleeButton.getY());
			} else if(currentEvent instanceof LootEvent) {
				sb.draw(TextureManager.LOOT_BANNER, MainGame.WIDTH / 2 - TextureManager.LOOT_BANNER.getWidth() / 2,
						MainGame.HEIGHT / 2 + TextureManager.LOOT_BANNER.getHeight() / 2);
				sb.draw(getLootButton.getTexture(), getLootButton.getX(), getLootButton.getY());
				sb.draw(leaveLootButton.getTexture(), leaveLootButton.getX(), leaveLootButton.getY());
			}
		} else {
			
		}
		
		
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
//		int typeOfEvent = rand.nextInt(2);
		int typeOfEvent = 0;
//		distanceToEvent = rand.nextInt(100);
		distanceToEvent = 1;
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
