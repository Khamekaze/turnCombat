package com.khamekaze.testgame.location;

public abstract class Location {
	
	private int locationIndex, originDistance, locationDistance = 0;
	private String name;
	
	public Location(String name, int locationIndex, int originDistance) {
		this.name = name;
		this.locationIndex = locationIndex;
		this.originDistance = originDistance;
	}
	
	public int calculateDistanceFromLocation(Location location) {
		int newLocationOriginDistance = location.getOriginDistance();
		if(originDistance > newLocationOriginDistance) {
			locationDistance = originDistance - newLocationOriginDistance;
		} else if(newLocationOriginDistance > originDistance) {
			locationDistance = newLocationOriginDistance - originDistance;
		}
		
		return locationDistance;
	}
	
	public String getName() {
		return name;
	}
	
	public int getOriginDistance() {
		return originDistance;
	}
	
	public int getLocationIndex() {
		return locationIndex;
	}

}
