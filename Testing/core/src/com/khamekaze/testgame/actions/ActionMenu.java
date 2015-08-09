package com.khamekaze.testgame.actions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.entity.EntityManager;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.gui.Button;

public class ActionMenu {
	
	public static final int CLOSED = 80085, BASE_MENU = 0, ATTACK_MENU = 1, MAGIC_MENU = 2, ITEM_MENU = 3, NORMAL_ATTACK = 4,
							SPECIAL_ATTACK = 5, USE_SPELL = 6, USE_ITEM = 7;
	private int currentMenu;
	private float x, y, width, height, leftX, leftY, topX, topY, rightX, rightY;
	private Array<Button> buttons;
	private ItemMenu itemMenu;
	private MagicMenu magicMenu;
	private EntityManager entityManager;
	
	private Button attackButton, itemButton, magicButton, normalAttackButton, specialAttackButton;

	public ActionMenu(EntityManager entityManager) {
		currentMenu = CLOSED;
		this.entityManager = entityManager;
		itemMenu = new ItemMenu(entityManager.getPlayer());
		magicMenu = new MagicMenu(entityManager.getPlayer());
		loadMenuButtons();
	}
	
	public void getBaseMenu(SpriteBatch sb) {
		currentMenu = BASE_MENU;
	}
	
	public void getAttackMenu(SpriteBatch sb) {
		currentMenu = ATTACK_MENU;
	}
	
	public void loadMenuButtons() {
		//Base menu
		attackButton = new Button(540, 90, 60, 60, "AttackButton");
		itemButton = new Button(614, 237, 60, 60, "ItemButton");
		magicButton = new Button(688, 90, 60, 60, "MagicButton");
		
		//Attack menu
		normalAttackButton = new Button(540, 90, 60, 60, "NormalAttackButton");
		specialAttackButton = new Button(540, 90 + 60 + 10, 60, 60, "SpecialAttackButton");
		
		//Array of buttons
		buttons = new Array<Button>();
		buttons.add(attackButton);
		buttons.add(itemButton);
		buttons.add(magicButton);
		buttons.add(normalAttackButton);
		buttons.add(specialAttackButton);
	}
	
	public void renderHitBoxes(SpriteBatch sb) {
		switch(currentMenu) {
		
		case CLOSED:
			break;
		
		case BASE_MENU:
			attackButton.render(sb);
			itemButton.render(sb);
			magicButton.render(sb);
			break;
			
		case ATTACK_MENU:
			normalAttackButton.render(sb);
			specialAttackButton.render(sb);
			break;
			
		case NORMAL_ATTACK:
			System.out.println("Normal Attack");
			break;
			
		case SPECIAL_ATTACK:
			System.out.println("Special Attack");
			break;
			
		case MAGIC_MENU:
			magicMenu.render(sb);
			break;
			
		case ITEM_MENU:
			itemMenu.render(sb);
			break;
			
		case USE_SPELL:
			break;
			
		case USE_ITEM:
			break;
			
		default:
			break;
		}
	}
	
	public int getCurrentMenu() {
		return currentMenu;
	}
	
	public void setCurrentMenu(int currentMenu) {
		this.currentMenu = currentMenu;
	}
	
	public Array<Button> getButtons() {
		return buttons;
	}
	
	public ItemMenu getItemMenu() {
		return itemMenu;
	}
	
	public MagicMenu getMagicMenu() {
		return magicMenu;
	}
	
}
