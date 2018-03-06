package someRPG;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Handler {
	
	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	LinkedList<GameObject> tiles = new LinkedList<GameObject>();
	LinkedList<Dice> dice = new LinkedList<Dice>();
	
	private Random r = new Random();
	private SoundPlayer soundPlayer = new SoundPlayer();
	
	public void tick() {
		for (int i = 0; i < tiles.size(); i++) {
			GameObject tempObject = tiles.get(i);
			tempObject.tick();
		}
		for (int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			tempObject.tick();
		}
		
	}
	
	public void render(Graphics g) {
		
		for (int i = 0; i < tiles.size(); i++) {
			GameObject tempObject = tiles.get(i);
			tempObject.render(g);
		}
		for (int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			tempObject.render(g);
		}
	
	}
	
	public void addPickup(InventoryObject object) {
		this.objects.add(object);
		
	}
		
	public void addObject(GameObject object) {
		this.objects.add(object);
		
	}
	
	public void addTile(GameObject object) {
		this.tiles.add(object);
		
	}
	
	public void addDice(Dice dice) {
		this.dice.add(dice);
		
	}
	
	public void removeObject(GameObject object) {
		this.objects.remove(object);
		
	}
	
	public GameObject getPlayer() {
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getId() == ID.Player) {
				return objects.get(i);
			}
		}
		return null;
	}
	
	public GameObject getBall() {
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getId() == ID.Ball) {
				return objects.get(i);
			}
		}
		return null;
	}
	
	public void playSound(String path) {
		this.soundPlayer = new SoundPlayer();
		soundPlayer.playSound(path);
	}
	
	public int getObjectCount() {
		return this.objects.size();
	}
	
	public void reset() {
		for(int i = 0; i < objects.size(); i++) {
			objects.remove(i);
		}
	}
	
	
	
}
