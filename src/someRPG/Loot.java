package someRPG;

import java.awt.Graphics;
import java.awt.Rectangle;

import someRPG.Game.STATE;

public class Loot extends GameObject{
	
	public static enum RARITY {
		Common,
		Uncommon,
		Rare,
		Epic
	};
	public static enum TYPE {
		Armour,
		Weapon
	};
	public RARITY rarity;
	public TYPE type;
	private Handler handler;
	private Dice dice;
	
	public Loot(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.dice = handler.dice.get(0);
		// TODO Auto-generated constructor stub
	}

	public void rollForRarity() {
		int roll = dice.roll();
		if(roll < 10) {
			this.setRarity(RARITY.Epic);
		}else if(roll >= 10 && roll < 90) {
			this.setRarity(RARITY.Uncommon);
		}else if(roll >= 90 && roll < 100) {
			this.setRarity(RARITY.Rare);	
		}
		else this.setRarity(RARITY.Common);
	}
	
	public void setRarity(RARITY rarity) {
		this.rarity = rarity;
	}
	
	public void setType(TYPE type) {
		this.type = type;
		
	}


	public void rollForType() {
		int roll = dice.roll();
		if(roll < 10) {
			this.setType(TYPE.Weapon);
		}else
			this.setType(TYPE.Armour);	
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}

