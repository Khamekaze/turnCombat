package com.khamekaze.testgame.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.testgame.entity.Enemy;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.entity.EntityManager;
import com.khamekaze.testgame.entity.Item;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.entity.Spell;
import com.khamekaze.testgame.gui.Button;
import com.khamekaze.testgame.input.InputManager;

public class ActionMenuManager {
	
	private int waiting = 0;
	private ActionMenu actionMenu;
	private Spell currentSpell = null;
	private Item currentItem = null;
	
	private InputManager inputManager;
	private EntityManager entityManager;

	public ActionMenuManager(InputManager inputManager, EntityManager entityManager) {
		this.inputManager = inputManager;
		this.entityManager = entityManager;
		actionMenu = new ActionMenu(entityManager);
	}
	
	public void update(Entity e) {
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
		} else if(Gdx.input.isKeyPressed(Input.Keys.X)) {
			for(Enemy enemies : entityManager.getEnemies()) {
				enemies.setHp(0);
			}
		}
	}
	
	public void selectAction() {
		
		//BASE MENU, CHOOSE SUB MENU
		if(actionMenu.getCurrentMenu() == ActionMenu.BASE_MENU) {
			for(Button b : actionMenu.getButtons()) {
				if(inputManager.getMouseHitbox().overlaps(b.getHitbox()) && b.getName() == "AttackButton" &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					actionMenu.setCurrentMenu(ActionMenu.ATTACK_MENU);
					waiting = 0;
				} else if(inputManager.getMouseHitbox().overlaps(b.getHitbox()) && b.getName() == "ItemButton" &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					actionMenu.setCurrentMenu(ActionMenu.ITEM_MENU);
					waiting = 0;
				} else if(inputManager.getMouseHitbox().overlaps(b.getHitbox()) && b.getName() == "MagicButton" &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					actionMenu.setCurrentMenu(ActionMenu.MAGIC_MENU);
					waiting = 0;
				}
			}
			
			//CHOOSE NORMAL OR SPECIAL ATTACK
		} else if(actionMenu.getCurrentMenu() == ActionMenu.ATTACK_MENU) {
			Player player = entityManager.getPlayer();
			for(Button b : actionMenu.getButtons()) {
				if(inputManager.getMouseHitbox().overlaps(b.getHitbox()) && b.getName() == "NormalAttackButton" &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
					actionMenu.setCurrentMenu(ActionMenu.NORMAL_ATTACK);
					waiting = 0;
				} else if(inputManager.getMouseHitbox().overlaps(b.getHitbox()) && b.getName() == "SpecialAttackButton" &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50 && player.getSpecialAttackCharge() == 0) {
					actionMenu.setCurrentMenu(ActionMenu.SPECIAL_ATTACK);
					waiting = 0;
				}
			}
			
			//GO BACK TO BASE MENU FROM ATTACK MENU
			if(actionMenu.getCurrentMenu() == ActionMenu.ATTACK_MENU &&
					inputManager.getMouseHitbox().overlaps(entityManager.getPlayer().getHitbox()) &&
					Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
				actionMenu.setCurrentMenu(ActionMenu.BASE_MENU);
				waiting = 0;
			}
			
			//GO BACK TO ATTACK MENU
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
			
			//USE NORMAL ATTACK
		} else if(actionMenu.getCurrentMenu() == ActionMenu.NORMAL_ATTACK) {
			Player player = entityManager.getPlayer();
			for(Enemy enemy : entityManager.getEnemies()) {
				if(inputManager.getMouseHitbox().overlaps(enemy.getHitbox()) &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
					enemy.takeDamage(player.getAttackDamage());
					player.updateSpecialAttackCharge();
					player.resetActionTime();
					actionMenu.setCurrentMenu(ActionMenu.CLOSED);
					waiting = 0;
				}
			}
			
			//USE SPECIAL ATTACK
		} else if(actionMenu.getCurrentMenu() == ActionMenu.SPECIAL_ATTACK) {
			Player player = entityManager.getPlayer();
			for(Enemy enemy : entityManager.getEnemies()) {
				if(inputManager.getMouseHitbox().overlaps(enemy.getHitbox()) &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50 && player.getSpecialAttackCharge() == 0) {
					enemy.takeDamage(player.getSpecialAttack());
					player.resetSpecialAttackCharge();
					player.resetActionTime();
					actionMenu.setCurrentMenu(ActionMenu.CLOSED);
					waiting = 0;
				}
			}
			
			//SELECT ITEM
		} else if(actionMenu.getCurrentMenu() == ActionMenu.ITEM_MENU) {
			for(Item item : actionMenu.getItemMenu().getItems()) {
				if(inputManager.getMouseHitbox().overlaps(item.getHitbox()) &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
					item.selectItem();
					currentItem = item;
					actionMenu.setCurrentMenu(ActionMenu.USE_ITEM);
					waiting = 0;
				}
			}
			
			//BACK TO BASE MENU FROM ITEM MENU
			if(actionMenu.getCurrentMenu() == ActionMenu.ITEM_MENU &&
					inputManager.getMouseHitbox().overlaps(entityManager.getPlayer().getHitbox()) &&
					Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
				for(Item item : actionMenu.getItemMenu().getItems()) {
					item.setIsSelected(false);
					currentItem = null;
				}
				actionMenu.setCurrentMenu(ActionMenu.BASE_MENU);
				waiting = 0;
			}
			
			//MAGIC MENU
		} else if(actionMenu.getCurrentMenu() == ActionMenu.MAGIC_MENU) {
			for(Spell spell : actionMenu.getMagicMenu().getSpells()) {
				if(inputManager.getMouseHitbox().overlaps(spell.getHitbox()) &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
					spell.selectSpell();
					currentSpell = spell;
					waiting = 0;
					actionMenu.setCurrentMenu(ActionMenu.USE_SPELL);
				}
			}
			
			//GO BACK TO BASE MENU FROM MAGIC MENU
			if(actionMenu.getCurrentMenu() == ActionMenu.MAGIC_MENU &&
					inputManager.getMouseHitbox().overlaps(entityManager.getPlayer().getHitbox()) &&
					Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				actionMenu.getMagicMenu().resetSpells();
				currentSpell = null;
				actionMenu.setCurrentMenu(ActionMenu.BASE_MENU);
				waiting = 0;
			}
			
			//USE SPELL
		} else if(actionMenu.getCurrentMenu() == ActionMenu.USE_SPELL) {
			if(currentSpell.getType() == Spell.DEFENSIVE) {
				if(inputManager.getMouseHitbox().overlaps(entityManager.getPlayer().getHitbox()) &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
					entityManager.getPlayer().restoreHp(currentSpell.getAmount());
					entityManager.getPlayer().updateSpecialAttackCharge();
					entityManager.getPlayer().resetActionTime();
					currentSpell = null;
					actionMenu.setCurrentMenu(ActionMenu.CLOSED);
					waiting = 0;
				} else if(!inputManager.getMouseHitbox().overlaps(entityManager.getPlayer().getHitbox()) &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
					actionMenu.getMagicMenu().resetSpells();
					actionMenu.setCurrentMenu(ActionMenu.MAGIC_MENU);
				}
			} else if(currentSpell.getType() == Spell.OFFENSIVE) {
				Player player = entityManager.getPlayer();
				for(Enemy e : entityManager.getEnemies()) {
					if(inputManager.getMouseHitbox().overlaps(e.getHitbox()) &&
							Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
						e.takeDamage(currentSpell.getAmount());
						player.updateSpecialAttackCharge();
						player.resetActionTime();
						currentSpell = null;
						actionMenu.setCurrentMenu(ActionMenu.CLOSED);
						waiting = 0;
					} else if(!inputManager.getMouseHitbox().overlaps(e.getHitbox()) &&
							Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
						System.out.println("Going back to magic menu");
						actionMenu.getMagicMenu().resetSpells();
						actionMenu.setCurrentMenu(ActionMenu.MAGIC_MENU);
					}
				}
			}
			
			//USE ITEM
		} else if(actionMenu.getCurrentMenu() == ActionMenu.USE_ITEM) {
			Player player = entityManager.getPlayer();
			if(currentItem.getType() == Item.DEFENSIVE) {
				if(inputManager.getMouseHitbox().overlaps(player.getHitbox()) &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
					player.restoreHp(currentItem.getAmount());
					player.updateSpecialAttackCharge();
					player.resetActionTime();
					currentItem = null;
					actionMenu.setCurrentMenu(ActionMenu.CLOSED);
					waiting = 0;
				} else if(!inputManager.getMouseHitbox().overlaps(entityManager.getPlayer().getHitbox()) &&
						Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
					actionMenu.getItemMenu().resetItems();
					actionMenu.setCurrentMenu(ActionMenu.ITEM_MENU);
				}
			} else if(currentItem.getType() == Item.OFFENSIVE) {
				for(Enemy e : entityManager.getEnemies()) {
					if(inputManager.getMouseHitbox().overlaps(e.getHitbox()) &&
							Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
						e.takeDamage(currentItem.getAmount());
						player.updateSpecialAttackCharge();
						player.resetActionTime();
						currentItem = null;
						actionMenu.setCurrentMenu(ActionMenu.CLOSED);
						waiting = 0;
					} else if(!inputManager.getMouseHitbox().overlaps(e.getHitbox()) &&
							Gdx.input.isButtonPressed(Input.Buttons.LEFT) && waiting == 50) {
						actionMenu.getItemMenu().resetItems();
						actionMenu.setCurrentMenu(ActionMenu.ITEM_MENU);
					}
				}
			}
		}
	}
	
	public void resetItem() {
		for(Item item : actionMenu.getItemMenu().getItems()) {
			item.setIsSelected(false);
		}
	}
	
	public void resetTurn(Player player) {
		player.resetActionTime();
	}
}
