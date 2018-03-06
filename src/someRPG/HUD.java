package someRPG;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class HUD {
	
	public static int health = 100;
	public static int targetHealth;
	private int greenValue = 0;
	private int targetGreenValue = 0;
	private int score = 0;
	private int level;
	private int lives;
	private int objects;
	private int inventory;
	private Player player;
	private Handler handler;
	private Monster target;
	private String targetName, skillOne, skillTwo;
	private Rectangle skill1, skill2, skill3, skill4, skill5;
	private BufferedImage butt1, butt2;
	private Textures textures;
	
	public HUD(Handler handler, Textures textures) {
		this.handler = handler;
		this.player = (Player)handler.getPlayer();
		this.skill1 = new Rectangle(528, 784, 32, 32);
		this.skill2 = new Rectangle(576, 784, 32, 32);
		this.skill3 = new Rectangle(624, 784, 32, 32);
		this.skill4 = new Rectangle(672, 784, 32, 32);
		this.skill5 = new Rectangle(720, 784, 32, 32);
		this.textures = textures;
		this.butt1 = textures.icons[0];
		this.butt2 = textures.icons[1];
		
	}
	

	public void tick() {
		
		health = Game.clamp(health, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = health *2;
		score = player.getScore();
		health = Game.clamp(health, 0, 100);
		health = player.getHealth();
		lives = player.getLives();
		level = Game.getLevel();
		inventory = player.getInvCount();
		objects = handler.getObjectCount();
		if(player.getThreats().size() > 0) {
		this.target = (Monster)player.getClosestThreat(player.getThreats());
		if(player.selectedTarget != null) {
			this.target = (Monster) player.selectedTarget;
		}
		targetHealth = this.target.getHealth();
		targetGreenValue = targetHealth *2;
		targetGreenValue = Game.clamp(targetGreenValue, 0, 255);
		targetName = target.getName();
		}
		skillOne = player.getSkills().get(0).getName();		
		skillTwo = player.getSkills().get(1).getName();
	}
	
	public void setScore(int score) {
		this.score = player.getScore();
	}
	
	public int getScore() {
		return score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public Rectangle getPlayBoxBounds() {
		return new Rectangle(0, 0, 1280, 768);
	
	}
	
	public Rectangle getSkillBoxBounds(int skillNo) {
		if(skillNo == 1) {
			return skill1.getBounds();
		}else if
			(skillNo == 2) {
			return skill2.getBounds();
		}else if
			(skillNo == 3) {
			return skill3.getBounds();
		}else if
			(skillNo == 4) {
			return skill4.getBounds();
		}else if
			(skillNo == 5) {
			return skill5.getBounds();
		}else {
			return null;
		}
		
		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.white);
		//HUD Window
		g.drawRect(0, 768, 1280, 256);
		
		
		//Left Window
		g.drawRect(0, 832, 512, 192);
		g.drawString("Score: " + score, 10, 850);
		g.drawString("Level: " + level, 10, 880);
		g.drawString("Lives: " + lives, 10, 910);
		g.drawString("" + targetName, 544, 920);
		
		//Right Window
		g.drawRect(768, 832, 512, 192);
		g.drawString("Handler Objects: " + objects, 778, 850);
		g.drawString("Inventory Objects: " + inventory, 778, 880);
		g.drawString("Skill One: " + skillOne, 778, 910);
		g.drawString("Skill Two: " + skillTwo, 778, 930);
		
		//Skill Bar
		g.drawRect(0, 768, 1280, 64);
		g.drawRect(512, 768, 256, 64);
		
		//528, 576, 624, 672, 720
		g2d.draw(skill1);
		g.drawImage(butt1, 532, 790, null);
		g2d.draw(skill2);
		g.drawImage(butt2, 580, 789, null);
		g2d.draw(skill3);
		g2d.draw(skill4);
		g2d.draw(skill5);
//		int skillTextX = 528;
//		int skillTextY = 784;
//		for(int i = 0; i < player.getSkills().size(); i++) {
//			Skill skill = player.getSkills().get(i);
//			g.drawString("" + skill.getName(), skillTextX, skillTextY);
//			skillTextX += 48;
//		}
		
		
		//draw health bar
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(540, 864, health * 2, 32);
		g.drawRect(540, 864, 200, 32);
		
		//draw target health bar
		g.setColor(new Color(75, targetGreenValue, 0));
		g.fillRect(540, 928, targetHealth * 2, 32);
		g.drawRect(540, 928, 200, 32);

	}


}

