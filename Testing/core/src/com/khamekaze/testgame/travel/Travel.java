package com.khamekaze.testgame.travel;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.testgame.location.Location;

public class Travel {
	
	private int stepsTaken, stepsToDestination, stepsLeft;
	private float delta = 0, second = 0;
	private Random rand;
	private Location fromLocation, toLocation;
	
	public Travel(Location fromLocation, Location toLocation) {
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		rand = new Random();
		stepsTaken = 0;
		stepsToDestination = fromLocation.calculateDistanceFromLocation(toLocation);
	}
	
	public void update() {
		delta = Gdx.graphics.getDeltaTime();
		if(second <= 1)
			second += delta;
		else if(second >= 1)
			second = 0;
		
		if(second > 1)
			stepsTaken++;
		
	}
	
	public void render(SpriteBatch sb) {

	}

}
