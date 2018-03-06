package someRPG;

import java.awt.Color;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import someRPG.Skill.Type;

public class Player extends GameObject implements Killable{
	
	Random r = new Random();
	Handler handler;
	static int score;
	static int health;
	static int Playerlives;
	private Textures textures;
	private BufferedImage leftSprite, rightSprite;
	private Animation moveLeft, moveRight, moveUp, moveDown;
	private LinkedList<GameObject> inventory = new LinkedList<GameObject>();
	private LinkedList<GameObject> threats;
	private LinkedList<Skill> skills = new LinkedList<Skill>();;
//	private Ball ball;
	private boolean alive, colliding;
	private long timeOfDeath;
	private SoundPlayer soundPlayer;
	private Collision collision;
	private long attackTimer;
	private int destX, destY, targetIndex, xp;
	private boolean moving = false;
	public GameObject target, selectedTarget;
	private String name;

	public Player(float x, float y, ID id, Handler handler, Textures textures) {
		super(x, y, id);
		this.handler = handler;
		this.textures = textures;
//		leftSprite = textures.player[0];
		rightSprite = textures.player[0];
//		playerDead = new Animation(10, textures.player[6], textures.player[7],textures.player[8], textures.player[9]);
		moveLeft = new Animation(5, textures.player[3], textures.player[4], textures.player[5]);
		moveRight = new Animation(5, textures.player[6], textures.player[7], textures.player[8]);
		moveDown = new Animation(5, textures.player[0], textures.player[1], textures.player[2]);
		moveUp = new Animation(5, textures.player[9], textures.player[10], textures.player[11]);
		health = 100;
		score = 0;
		Playerlives = 3;
		alive = true;
		collision = new Collision();
		Skill defaultAttack = new Skill(Type.SPELL, "Melee", "Basic Melee", 10, 0, 0);
		Skill Fireball = new Skill(Type.SPELL, "Fireball", "Ranged Attack", 50, 0, 0);
		skills.add(defaultAttack);
		skills.add(Fireball);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 48, 48);
	}
	
	public Ellipse2D getStopBounds() {
		return new Ellipse2D.Double(x - 5, y - 5, 2.0 * 10, 2.0 * 10);
	}
	
	@SuppressWarnings("static-access")
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public void reset() {
		Playerlives = 3;
		score = 0;
	}
	
	public void resetXY() {
		x = (Game.WIDTH / 2);
		y = Game.HEIGHT - 32;
	}
	
	public void stop(int mouseX, int mouseY) {
		Shape pointBounds = new Ellipse2D.Double(destX - 10, destY - 10, 2.0 * 10, 2.0 * 10);
		float diffX = getX() - mouseX;
		float diffY = getY() - mouseY;
		float distance = (float) Math.sqrt( (getX() - mouseX) * (getX() - mouseX) + (getY() - mouseY) * (getY() - mouseY));
		Rectangle stopBounds = new Rectangle(mouseX-5, mouseY-5, 10, 10);
		if(pointBounds.intersects(getBounds())) {
//		if(getBounds().intersects(stopBounds)) {
			moving = false;
			velX = 0;
			velY = 0;
		}		
		
	}
	
	public void moveTo(int mouseX, int mouseY) {
		float diffX = getX() - mouseX;
		float diffY = getY() - mouseY;
		destX = mouseX;
		destY = mouseY;
		float distance = (float) Math.sqrt( (getX() - mouseX) * (getX() - mouseX) + (getY() - mouseY) * (getY() - mouseY));
		velX = 3*((-1/distance) * diffX);
		velY = 3*((-1/distance) * diffY);
		moving = true;
//		stop(mouseX, mouseY);
	}

	public void tick() {
//		oopsCheck();
//		if(alive) {
		if(selectedTarget == null) {
			this.target = getClosestThreat(getThreats());
		}
//			x += velX;
//			y += velY;
			x = Game.clamp((int)x, 0 + 24, Game.WIDTH - 30);
			y = Game.clamp((int)y, Game.HEIGHT - 1024, Game.HEIGHT -256);
			moveLeft.runAnimation();
			moveRight.runAnimation();
			moveDown.runAnimation();
			moveUp.runAnimation();
			setColliding(false);
//			collision();
//			if(moving) {
//				stop(destX, destY);
//				}
	}

	
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		if(alive) {
			if(velX < 0) {
				moveLeft.drawAnimation(g, x, y, 0);
//				g.drawImage(leftSprite, x-32, y, null);
				}else if(velX > 0) {
					moveRight.drawAnimation(g, x, y, 0);
//					g.drawImage(rightSprite, x, y, null);
				}else if(velY > 0) {
					moveDown.drawAnimation(g, x, y, 0);
//					g.drawImage(rightSprite, x, y, null);
				}else if(velY < 0) {
					moveUp.drawAnimation(g, x, y, 0);
//					g.drawImage(rightSprite, x, y, null);
				}else {
//					g.drawImage(leftSprite, x-32, y, null);
					g.drawImage(rightSprite, (int)x, (int)y, null);
				}
		}else {
//			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.white);
			g2d.fillRect((int)x, (int)y, 48, 48);
//			playerDead.drawAnimation(g, x, y, 0);
		}
		g2d.setColor(Color.red);
		g2d.draw(getBounds());
//		Shape theCircle = new Ellipse2D.Double(destX - 10, destY - 10, 2.0 * 10, 2.0 * 10);
//		Shape pointBounds = new Ellipse2D.Double(x+5, y+10, 2.0 * 20, 2.0 * 20);
//		if(selectedTarget != null) {
//			Rectangle targetOutline = new Rectangle((int)selectedTarget.getX(), (int)selectedTarget.getY(), 42, 42);
//			g2d.draw(targetOutline);
//			
//		}else if(target != null){
//			Rectangle targetOutline = new Rectangle((int)target.getX(), (int)target.getY(), 42, 42);
//			g2d.draw(targetOutline);
//		}
//		Rectangle targetOutline = new Rectangle()
//	    g2d.draw(theCircle);
//	    g2d.draw(pointBounds);
		
//		
//		g2d.setColor(Color.gray);
//		g2d.fillRect(x+1, y+1, 62, 14);
	}
	
//	public Ellipse2D getCollisionBounds() {
//		return new Ellipse2D.Double(x+5+Game.camera.offsetX, y+10+Game.camera.offsetY, 2.0 * 20, 2.0 * 20);
//	}
	
	public Rectangle getCollisionBounds() {
		return new Rectangle((int)(x + Game.camera.offsetX), (int)(y + Game.camera.offsetY), 48, 48);
	}

	public int getHealth() {
		return health;
	}
	
	public void setHealth(int delta) {
		health += delta;
	}
	
	public int getLives() {
		return Playerlives;
	}
	
	public void setLives(int lives) {
		Playerlives = lives;
	}
	
	private void pickupItem(GameObject item) {
		this.inventory.add(item);
		handler.removeObject(item);
	}
	
	public int getInvCount() {
		return this.inventory.size();
	}
	
	public void attack(Killable target, Skill skill) {
		target.decHealth(skill.getDamage());
		attackTimer = System.currentTimeMillis();
		
	}
	
	private boolean checkCooldown(int secs){
		if(System.currentTimeMillis() - attackTimer < (secs * 1000) ) {
			return false;
		}else {
			return true;
		}
		
	}
	
	private void loot(Monster monster) {
		LinkedList<Loot> loots = monster.loots;
		if(loots.size() > 0) {
		inventory.add((GameObject) loots.get(0));
		monster.loots.remove(loots.get(0));
		}
	}
	
	public void cycleTarget() {
		targetIndex++;
		if(targetIndex > threats.size()-1) {
			targetIndex = 0;
		}
		selectedTarget = threats.get(targetIndex);
		
	}
	
	public float getDistance(GameObject subject, GameObject threat) {
		float diffX = subject.getX() - threat.getX();
		float diffY = subject.getY() - threat.getY();
		float distance = (float) Math.sqrt( (subject.getX() - threat.getX()) * (subject.getX() - threat.getX()) + (subject.getY() - threat.getY()) * (subject.getY() - threat.getY()));
		return distance;
	}
	
	public LinkedList<GameObject> getThreats() {
		threats = new LinkedList<GameObject>();
//		if(this.type == ID.Fauna) {
		for(int i = 0; i < handler.objects.size(); i++) {
			if(handler.objects.get(i).getId() == ID.Monster || handler.objects.get(i).getId() == ID.Fauna) {
				GameObject threat = handler.objects.get(i);
				threats.add(threat);
			}
		}		
		return threats;
	}
	
	public GameObject getClosestThreat(LinkedList<GameObject> threats) {
		float distToThreat;
		float distToCurrentThreat;
		GameObject closestThreat;
		if(threats.size() > 0) {
			distToThreat = getDistance(this, threats.get(0));
			closestThreat =  threats.get(0);
		for(int i = 0; i < threats.size(); i++) {
			
			distToCurrentThreat = getDistance(this, threats.get(i));
			
			if(distToCurrentThreat < distToThreat) {
				closestThreat =  threats.get(i);
				targetIndex = i;
				distToThreat = getDistance(this, threats.get(i));
				}
			}
		return closestThreat;
		}else {
			return null;
		}
		
	}
	
	private void collision() {
		for (int i = 0; i < handler.objects.size(); i++) {
			if(handler.objects.get(i).getId() == ID.Potion) {
			GameObject tempObject = handler.objects.get(i);
			if(this.collision.getCollision(this, tempObject)) {
				pickupItem(tempObject);
				handler.removeObject(tempObject);
				
				}
			}else 
			if(handler.objects.get(i).getId() == ID.Monster) {
				Monster monster = (Monster)handler.objects.get(i);
				
				if(this.collision.getCollision(this, monster)) {
					if (monster.isDead()) {
						loot(monster);
					}else {
					if(checkCooldown(1)) {
//					attack(monster);
					}
					}
					}
				}
			

		}
	}

	@Override
	public void decHealth(int value) {
		health -= value;
		
	}

	@Override
	public void incHealth(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void meleeAttack(Killable target, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void specialAttack(Killable target, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return this.name;
		
	}

	@Override
	public boolean deadCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getBaseDamage() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public LinkedList<Skill> getSkills(){
		return this.skills;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}
	
	public Killable getSelectedTarget() {
		return (Killable) this.selectedTarget;
	}

	public boolean isColliding() {
		return colliding;
	}

	public void setColliding(boolean colliding) {
		this.colliding = colliding;
	}

		
}
