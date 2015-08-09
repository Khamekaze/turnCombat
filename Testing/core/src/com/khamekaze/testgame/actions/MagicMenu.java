package com.khamekaze.testgame.actions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.testgame.TextureManager;
import com.khamekaze.testgame.entity.Player;
import com.khamekaze.testgame.entity.Spell;

public class MagicMenu {
	
	private Array<Spell> spells;
	private Player player;
	private Texture magicMenuTexture;
	private BitmapFont font;
	private boolean hasUsedSpell = false;
	
	public MagicMenu(Player player) {
		this.player = player;
		spells = player.getSpells();
		font = new BitmapFont();
		magicMenuTexture = TextureManager.COMBAT_MENU_TEXTURE;
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(magicMenuTexture, 75, player.getHitbox().getY());
		renderSpells(sb);
	}
	
	public void renderSpells(SpriteBatch sb) {
		for(int i = 0; i < spells.size; i++) {
			if(i <= 2) {
				sb.draw(spells.get(i).getTexture(), 85 + (i * 210),
						player.getHitbox().getY() + magicMenuTexture.getHeight() - spells.get(i).getTexture().getHeight() - 10);
				
				spells.get(i).getHitbox().set(85 + (i * 210), player.getHitbox().getY() + magicMenuTexture.getHeight()
						- spells.get(i).getTexture().getHeight() - 10, 50, 50);
				
				font.draw(sb, spells.get(i).getName(), spells.get(i).getHitbox().getX() + 60, spells.get(i).getHitbox().getY() + 30);
			}
		}
	}
	
	public Array<Spell> getSpells() {
		return spells;
	}
	
}
