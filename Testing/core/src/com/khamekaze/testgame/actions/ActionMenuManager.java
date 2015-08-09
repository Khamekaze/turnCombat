package com.khamekaze.testgame.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.entity.EntityManager;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.gui.Button;
import com.khamekaze.testgame.input.InputManager;

public class ActionMenuManager {
	
	private boolean actionMenuOpen = false, attackMenuOpen = false;
	private int waiting = 0;
	private ActionMenu actionMenu;
	private ShapeRenderer shapeRenderer;
	
	private InputManager inputManager;
	private EntityManager entityManager;

	public ActionMenuManager(InputManager inputManager, EntityManager entityManager) {
		this.inputManager = inputManager;
		this.entityManager = entityManager;
		actionMenu = new ActionMenu();
		shapeRenderer = new ShapeRenderer();
	}
	
	public void update(Entity e) {
		actionMenu.update();
		openActionMenu(e);
		selectAction();
		if(waiting < 50)
			waiting++;
		if(waiting >= 50)
			waiting = 50;
	}
	
	public void render(SpriteBatch sb) {
		actionMenu.renderHitBoxes(sb);
	}
	
	public void openActionMenu(Entity e) {
		if(inputManager.getMouseHitbox().overlaps(e.getHitbox()) && e instanceof Player) {
			if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && entityManager.getPlayer().getPercentReady() >= 100.0f) {
				if(actionMenu.getCurrentMenu() == ActionMenu.CLOSED && waiting == 50) {
					actionMenu.setCurrentMenu(ActionMenu.BASE_MENU);
					waiting = 0;
				} else if(actionMenu.getCurrentMenu() == ActionMenu.BASE_MENU && waiting == 50) {
					actionMenu.setCurrentMenu(ActionMenu.CLOSED);
					waiting = 0;
				}
			}
		}
	}
	
	public void selectAction() {
		if(actionMenu.getCurrentMenu() == ActionMenu.BASE_MENU) {
			for(Button b : actionMenu.getButtons()) {
				if(inputManager.getMouseHitbox().overlaps(b.getHitbox()) && b.getName() == "AttackButton" &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					actionMenu.setCurrentMenu(ActionMenu.ATTACK_MENU);
					waiting = 0;
				}
			}
		} else if(actionMenu.getCurrentMenu() == ActionMenu.ATTACK_MENU) {
			for(Button b : actionMenu.getButtons()) {
				if(inputManager.getMouseHitbox().overlaps(b.getHitbox()) && b.getName() == "NormalAttackButton" &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
					actionMenu.setCurrentMenu(ActionMenu.NORMAL_ATTACK);
					waiting = 0;
				} else if(inputManager.getMouseHitbox().overlaps(b.getHitbox()) && b.getName() == "SpecialAttackButton" &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
					actionMenu.setCurrentMenu(ActionMenu.SPECIAL_ATTACK);
					waiting = 0;
				}
			}
			
			if(actionMenu.getCurrentMenu() == ActionMenu.ATTACK_MENU &&
					inputManager.getMouseHitbox().overlaps(entityManager.getPlayer().getHitbox()) &&
					Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
				actionMenu.setCurrentMenu(ActionMenu.BASE_MENU);
				waiting = 0;
			}
		} else if(actionMenu.getCurrentMenu() == ActionMenu.NORMAL_ATTACK &&
				inputManager.getMouseHitbox().overlaps(entityManager.getPlayer().getHitbox()) &&
				Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			actionMenu.setCurrentMenu(ActionMenu.ATTACK_MENU);
			waiting = 0;
		} else if(actionMenu.getCurrentMenu() == ActionMenu.SPECIAL_ATTACK &&
				inputManager.getMouseHitbox().overlaps(entityManager.getPlayer().getHitbox()) &&
				Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			actionMenu.setCurrentMenu(ActionMenu.ATTACK_MENU);
			waiting = 0;
		}
	}
}
