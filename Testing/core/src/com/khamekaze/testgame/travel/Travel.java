package com.khamekaze.testgame.travel;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.MainGame;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.event.CombatEvent;
import com.khamekaze.testgame.event.Event;
import com.khamekaze.testgame.event.LootEvent;
import com.khamekaze.testgame.location.Location;

public class Travel {
	
	private int stepsTaken, stepsToDestination, stepsLeft, amountOfEvents, locationOfEvent;
	private float delta = 0, second = 0;
	private Random rand;
	private Location fromLocation, toLocation;
	private Array<Event> events;
	private Entity player;
	private boolean eventInitiated = false;
	private BitmapFont font = new BitmapFont();
	private Event currentEvent = null;
	
	public Travel(Location fromLocation, Location toLocation, Entity player) {
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.player = player;
		events = new Array<Event>();
		rand = new Random();
		amountOfEvents = rand.nextInt(15) + 1;
		stepsTaken = 0;
		stepsToDestination = fromLocation.calculateDistanceFromLocation(toLocation);
		locationOfEvent = stepsToDestination;
		populateEvents();
	}
	
	public void update() {
		delta = Gdx.graphics.getDeltaTime();
		
		if(!eventInitiated) {
			if(second <= 1)
				second += delta;
			else if(second >= 1)
				second = 0;
			
			if(second > 1) {
				stepsTaken++;
				stepsToDestination--;
				System.out.println(stepsToDestination);
			}

			checkForEvent();
		
		} else {
				second += delta;
				if(second >= 60) {
					second = 0;
					stepsTaken++;
					stepsToDestination--;
					eventInitiated = false;
				}
		}
		
	}
	
	public void render(SpriteBatch sb) {
		if(!eventInitiated) {
			
		} else {
			sb.begin();
			font.draw(sb, String.valueOf(currentEvent.getEventType()), MainGame.WIDTH / 2, MainGame.HEIGHT / 2);
			sb.end();
		}
	}
	
	public void populateEvents() {
		for(int i = 0; i < amountOfEvents; i++) {
			int typeOfEvent = rand.nextInt(2);
			int distanceOfLocation = rand.nextInt(100);
			locationOfEvent = locationOfEvent - distanceOfLocation;
			Event event = null;
			if(typeOfEvent == Event.COMBAT_EVENT) {
				event = new CombatEvent();
				event.setEventLocation(locationOfEvent);
				events.add(event);
				System.out.println("COMBAT EVENT CREATED!");
				System.out.println("LOCATION: " + event.getEventLocation());
			} else if(typeOfEvent == Event.LOOT_EVENT) {
				event = new LootEvent(player);
				event.setEventLocation(locationOfEvent);
				events.add(event);
				System.out.println("LOOT EVENT CREATED!");
				System.out.println("LOCATION: " + event.getEventLocation());
			}
		}
	}
	
	public void checkForEvent() {
		for(Event e : events) {
			if(e.getEventLocation() == stepsToDestination) {
				System.out.println("NEW EVENT! EVENT IS: " + e.getEventType());
				currentEvent = e;
				eventInitiated = true;
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
