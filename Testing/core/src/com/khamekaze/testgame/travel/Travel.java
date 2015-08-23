package com.khamekaze.testgame.travel;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.MainGame;
import com.khamekaze.testgame.TextureManager;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.event.CombatEvent;
import com.khamekaze.testgame.event.Event;
import com.khamekaze.testgame.event.LootEvent;
import com.khamekaze.testgame.location.Location;

public class Travel {
	
	private int stepsTaken, stepsToDestination, stepsLeft, amountOfEvents, locationOfEvent, distanceToEvent = 0;;
	private float delta = 0, second = 0;
	private Random rand;
	private Location fromLocation, toLocation;
	private Entity player;
	private boolean eventInitiated = false;
	private BitmapFont font = new BitmapFont();
	private Event currentEvent;
	
	public Travel(Location fromLocation, Location toLocation, Entity player) {
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.player = player;
		rand = new Random();
		amountOfEvents = rand.nextInt(15) + 1;
		stepsTaken = 0;
		stepsToDestination = fromLocation.calculateDistanceFromLocation(toLocation);
		locationOfEvent = stepsToDestination;
		currentEvent = null;
		generateEvent();
	}
	
	public Travel(Location fromLocation, Location toLocation, Entity player, int stepsTaken) {
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.player = player;
		this.stepsTaken = stepsTaken;
		stepsToDestination = fromLocation.calculateDistanceFromLocation(toLocation) - stepsTaken;
		currentEvent = null;
	}
	
	public void update() {
		delta = Gdx.graphics.getDeltaTime();
		
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
		
		} else {
			second += delta;
			if(second >= 60) {
				second = 0;
				stepsTaken++;
				stepsToDestination--;
				generateEvent();
				eventInitiated = false;
				
			}
		}
		
	}
	
	public void render(SpriteBatch sb) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		sb.begin();
		if(!eventInitiated) {
			
		} else {
			
			if(currentEvent instanceof CombatEvent) {
				sb.draw(TextureManager.COMBAT_BANNER, MainGame.WIDTH / 2 - TextureManager.COMBAT_BANNER.getWidth() / 2,
						MainGame.HEIGHT / 2 + TextureManager.COMBAT_BANNER.getHeight() / 2);
				sb.draw(TextureManager.ENGAGE_BUTTON, MainGame.WIDTH / 2 - TextureManager.ENGAGE_BUTTON.getWidth() - 47,
						MainGame.HEIGHT / 2 + TextureManager.ENGAGE_BUTTON.getHeight() - 115);
				sb.draw(TextureManager.FLEE_BUTTON, MainGame.WIDTH / 2 + 47,
						MainGame.HEIGHT / 2 + TextureManager.FLEE_BUTTON.getHeight() - 115);
			} else if(currentEvent instanceof LootEvent) {
				sb.draw(TextureManager.LOOT_BANNER, MainGame.WIDTH / 2 - TextureManager.LOOT_BANNER.getWidth() / 2,
						MainGame.HEIGHT / 2 + TextureManager.LOOT_BANNER.getHeight() / 2);
				sb.draw(TextureManager.GET_LOOT_BUTTON, MainGame.WIDTH / 2 - TextureManager.GET_LOOT_BUTTON.getWidth() - 47,
						MainGame.HEIGHT / 2 + TextureManager.GET_LOOT_BUTTON.getHeight() - 115);
				sb.draw(TextureManager.LEAVE_LOOT_BUTTON, MainGame.WIDTH / 2 + 47,
						MainGame.HEIGHT / 2 + TextureManager.LEAVE_LOOT_BUTTON.getHeight() - 115);
			}
		}
		
		sb.end();
	}
	
	public void generateEvent() {
		eventInitiated = false;
		currentEvent = null;
		int typeOfEvent = rand.nextInt(2);
		distanceToEvent = rand.nextInt(100);
		Event event = null;
		if(typeOfEvent == Event.COMBAT_EVENT) {
			event = new CombatEvent();
		} else if(typeOfEvent == Event.LOOT_EVENT) {
			event = new LootEvent(player);
		}
		currentEvent = event;
		locationOfEvent = stepsToDestination - distanceToEvent;
		currentEvent.setEventLocation(locationOfEvent);
		event = null;
		System.out.println("EVENT CREATED!");
		System.out.println("ID: " + currentEvent.getEventType());
		System.out.println("LOCATION: " + currentEvent.getEventLocation());
	}
	
	public boolean getEventInitiated() {
		return eventInitiated;
	}
	
	public void setEventInitiated(boolean initiated) {
		this.eventInitiated = initiated;
	}

}
