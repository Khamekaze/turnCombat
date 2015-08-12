package com.khamekaze.testgame.event;

public abstract class Event {
	
	public static final int COMBAT_EVENT = 0, BOSS_EVENT = 1, LOOT_EVENT = 2;
	
	private int eventType;
	
	public Event(int eventType) {
		this.eventType = eventType;
	}
	
	public Event() {
		
	}
	
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	
	public int getEventType() {
		return eventType;
	}
	
}
