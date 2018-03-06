package someRPG;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;



public class Monster extends GameObject implements Killable{
	
	private Handler handler;
	private Textures textures;
	private AI ai;
	private Collision collision;
	private boolean spooked;
	private boolean angry;
	private boolean dead;
	int health = 100;
	private long attackTimer, timeOfDeath;
	public LinkedList<Loot> loots;
	private Animation moveLeft, moveRight, moveUp, moveDown;
	private BufferedImage leftSprite, rightSprite;
	private String name;


	public Monster(int x, int y, ID id, Handler handler, Textures textures, String name, int patternID) {
		super(x, y, id);
		this.handler = handler;
		this.textures = textures;
		this.name = name;
		Pattern pattern = new Pattern(patternID);
		ai = new AI(this, handler, pattern);
		collision = new Collision();
		dead = false;
		if(this.id == ID.Fauna) {
			moveDown = new Animation(5, textures.fauna[0], textures.fauna[1], textures.fauna[2]);
			moveLeft = new Animation(5, textures.fauna[3], textures.fauna[4], textures.fauna[5]);
			moveRight = new Animation(5, textures.fauna[6], textures.fauna[7], textures.fauna[8]);
			moveUp = new Animation(5, textures.fauna[9], textures.fauna[10], textures.fauna[11]);
			rightSprite = textures.fauna[0];
		}else {
			moveDown = new Animation(5, textures.wolf[0], textures.wolf[1], textures.wolf[2]);
			moveLeft = new Animation(5, textures.wolf[3], textures.wolf[4], textures.wolf[5]);
			moveRight = new Animation(5, textures.wolf[6], textures.wolf[7], textures.wolf[8]);
			moveUp = new Animation(5, textures.wolf[9], textures.wolf[10], textures.wolf[11]);
			rightSprite = textures.wolf[0];
		}
	}

	@Override
	public void tick() {
		
		if(!dead) {
		if(this.getHealth() <= 0) {
			this.setHealth(0);
			dead = true;
			timeOfDeath = System.currentTimeMillis();
			loots = new LinkedList<Loot>();
			Loot loot = new Loot(0 ,0 , ID.Loot, handler);
			loot.rollForRarity();
			loot.rollForType();
			loots.add(loot);
		}
		}
		
		if(!dead) {
			
				moveLeft.runAnimation();
				moveRight.runAnimation();
				moveDown.runAnimation();
				moveUp.runAnimation();

			x += velX;
			y += velY;
//			LinkedList<GameObject> threats = ai.getThreats();
			ai.react(ai.getThreats());
			x = Game.clamp((int)x, 64, Game.WIDTH - 64);
			y = Game.clamp((int)y, 64, Game.HEIGHT -320);
//			GameObject threat = (GameObject) threats.get(0);
//			if(collision.getCollision(this, threat)) {
//				velX = 0;
//				velY = 0;
//				if(checkCooldown(1)) {
//				attack((Player)threat);
//				}			
//			}else 
//			if(collision.getAggro(this, threat)) {
//				spooked = true;
//				moveTo(threat);
//			}else {
//				spooked = false;
//				velX = 0;
//				velY = 0;
//			}
			
		}else if(dead) {
			if (despawnTimer()) {
				handler.removeObject(this);
		}
		}
	}
	
	

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
//		if(this.id == ID.Fauna) {
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
			
			
//		}
//	else {
//			if(spooked) {
//			g2d.setColor(Color.red);
//			g2d.fillRect((int)x, (int)y, 32, 32);
//			Shape theCircle = new Ellipse2D.Double(x - 84, y - 84, 2.0 * 100, 2.0 * 100);
//		    g2d.draw(theCircle);
//			
//			}else if(angry){
//				g2d.setColor(Color.yellow);
//				g2d.fillRect((int)x, (int)y, 32, 32);
//			}else {
//				g2d.setColor(Color.blue);
//				g2d.fillRect((int)x, (int)y, 32, 32);
//			}
//		}
		if (dead) {
			g2d.setColor(Color.gray);
			g2d.fillRect((int)x, (int)y, 32, 32);
			
		}
		Shape theCircle = new Ellipse2D.Double(x - 84, y - 84, 2.0 * 100, 2.0 * 100);
	    g2d.draw(theCircle);
		
	}
	
//	public ID getAIType(GameObject monster) {
//		return this.ai.type;
//	}
	

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public Ellipse2D getAggro() {
		return new Ellipse2D.Float(x - 100, y - 100, 232, 232);
//		return new Rectangle((int)x - 100, (int)y - 100, 232, 232);

	}
	
	public Point getMidPoint() {
		return new Point((int)x + 16, (int)y + 16);
	}
	
//	private void moveTo(GameObject threat) {
//		float diffX = x - threat.getX();
//		float diffY = y - threat.getY();
//		float distance = (float) Math.sqrt( (x - threat.getX()) * (x - threat.getX()) + (y - threat.getY()) * (y - threat.getY()));
//		velX = ((-1/distance) * diffX);
//		velY = ((-1/distance) * diffY);
//	}
	
	public boolean checkCooldown(int secs){
		if(System.currentTimeMillis() - attackTimer < (secs * 1000) ) {
			return false;
		}else {
			return true;
		}
		
	}
	
	public boolean isSpooked() {
		return this.spooked;
	}
	
	public void setSpooked(boolean spooked) {
		this.spooked = spooked;
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	private boolean despawnTimer() {
		if(System.currentTimeMillis() - timeOfDeath < 2000 ) {
			return false;
		}else {
			return true;
		}
		
	}
	
	public void attack(GameObject target) {
		if(target.getId() == ID.Player) {
			Player player = (Player)target;
			player.setHealth(-10);
		}else if(target.getId() == ID.Fauna) {
			Monster monster = (Monster)target;
			monster.decHealth(10);
		}
		
		attackTimer = System.currentTimeMillis();
	}
	
	
	public void decHealth(int delta) {
		this.health = this.health - delta;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public String getName() {
		return this.name;
	}

	// Interface Methods
	@Override
	public void incHealth(int value) {
		health = health + value;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void meleeAttack(Killable target, int value) {
		// TODO Auto-generated method stub
		target.decHealth(value);
		
	}

	@Override
	public void specialAttack(Killable target, int value) {
		// TODO Auto-generated method stub
		target.decHealth(value);
		
	}

	@Override
	public boolean deadCheck() {
		if(health < 0) {
			return true;
		}else
		
		return false;
	}

	@Override
	public int getBaseDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void attack(Killable target, Skill skill) {
		target.decHealth(skill.getDamage());
		attackTimer = System.currentTimeMillis();
		
	}

}
