package com.khamekaze.testgame.event;

public abstract class Event {
	
	public static final int COMBAT_EVENT = 0, BOSS_EVENT = 1337, LOOT_EVENT = 1;
	
	private int eventLocation = 0;
	
	protected int eventType;
	
	public Event(int eventType) {
		this.eventType = eventType;
	}
	
	public Event() {
		
	}
	
	public void setEventLocation(int eventLocation) {
		this.eventLocation = eventLocation;
	}
	
	public int getEventLocation() {
		return eventLocation;
	}
	
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	
	public int getEventType() {
		return eventType;
	}
	
}
