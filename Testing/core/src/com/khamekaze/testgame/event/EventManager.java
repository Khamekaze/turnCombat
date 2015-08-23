package com.khamekaze.testgame.event;

import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.screen.CombatScreen;
import com.khamekaze.testgame.screen.LootScreen;
import com.khamekaze.testgame.screen.ScreenManager;

public class EventManager {
	
	private Event event;
	private Player player;
	private int eventType;
	
	public EventManager(Event event) {
		this.event = event;
		eventType = event.getEventType();
	}
	
	public EventManager(Event event, Player player) {
		this.event = event;
		this.player = player;
		eventType = event.getEventType();
	}
	
	public void excecuteEvent() {
		
		switch(eventType) {
		
		case Event.COMBAT_EVENT:
			ScreenManager.setScreen(new CombatScreen(1));
			break;
			
		case Event.BOSS_EVENT:
			ScreenManager.setScreen(new CombatScreen(1));
			break;
			
		case Event.LOOT_EVENT:
			ScreenManager.setScreen(new LootScreen(player, (LootEvent) event));
			break;
			
		default:
			break;
		
		}
		
	}
	
}
