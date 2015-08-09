package com.khamekaze.testgame.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.TextureManager;
import com.khamekaze.testgame.entity.Enemy;
import com.khamekaze.testgame.entity.Entity;
import com.khamekaze.testgame.entity.Item;
import com.khamekaze.testgame.entity.Player;

public class ItemMenu {
	
	private Array<Item> items;
	private Player player;
	private Texture itemMenuTexture;
	private BitmapFont font;
	private boolean hasUsedItem = false;

	public ItemMenu(Player player) {
		this.player = player;
		items = player.getItems();
		font = new BitmapFont();
		itemMenuTexture = TextureManager.COMBAT_MENU_TEXTURE;
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(itemMenuTexture, 75, player.getHitbox().getY());
		renderItems(sb);
	}
	
	public void useItem(Item item, Entity target) {
		if(item.getType() == Item.OFFENSIVE && target instanceof Enemy) {
			target.takeDamage(item.getAmount());
		} else if(item.getType() == Item.DEFENSIVE && target instanceof Player) {
			target.restoreHp(item.getAmount());
		}
	}
	
	public Item getSelectedItem() {
		Item selected = null;
		for(Item item : items) {
			if(item.getIsSelected())
				selected = item;
		}
		return selected;
	}
	
	public Array<Item> getItems() {
		return items;
	}
	
	public void renderItems(SpriteBatch sb) {
		for(int i = 0; i < items.size; i++) {
			if(i <= 2) {
				sb.draw(items.get(i).getTexture(), 85 + (i * 210),
						player.getHitbox().getY() + itemMenuTexture.getHeight() - items.get(i).getTexture().getHeight() - 10);
				
				items.get(i).getHitbox().set(85 + (i * 210), player.getHitbox().getY() + itemMenuTexture.getHeight()
						- items.get(i).getTexture().getHeight() - 10, 50, 50);
				
				font.draw(sb, items.get(i).getName(), items.get(i).getHitbox().getX() + 60, items.get(i).getHitbox().getY() + 30);
			}
		}
	}
	
	public void setHasUsedItem(boolean hasUsed) {
		this.hasUsedItem = hasUsed;
	}
	
	public boolean hasUsedItem() {
		return hasUsedItem;
	}
}
