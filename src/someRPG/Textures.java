package someRPG;

import java.awt.image.BufferedImage;

public class Textures {
	
	public BufferedImage[] player = new BufferedImage[12];
	public BufferedImage[] fauna = new BufferedImage[12];
	public BufferedImage[] wolf = new BufferedImage[12];
	public BufferedImage[] icons = new BufferedImage[32];
	
	private SpriteSheet playerSprites, faunaSprites, wolfSprites, iconSprites;
	
	public Textures(Game game) {
		playerSprites = new SpriteSheet(Game.getPlayerSpriteSheet());
		faunaSprites = new SpriteSheet(Game.getFaunaSpriteSheet());
		wolfSprites = new SpriteSheet(Game.getWolfSpriteSheet());
		iconSprites = new SpriteSheet(Game.getIconSpriteSheet());
		getTextures();
	}
	
	public void getTextures() {
		
		player[0] = playerSprites.grabImage48(1, 1, 48, 48);
		player[1] = playerSprites.grabImage48(2, 1, 48, 48);
		player[2] = playerSprites.grabImage48(3, 1, 48, 48);
		player[3] = playerSprites.grabImage48(1, 2, 48, 48);
		player[4] = playerSprites.grabImage48(2, 2, 48, 48);
		player[5] = playerSprites.grabImage48(3, 2, 48, 48);
		player[6] = playerSprites.grabImage48(1, 3, 48, 48);
		player[7] = playerSprites.grabImage48(2, 3, 48, 48);
		player[8] = playerSprites.grabImage48(3, 3, 48, 48);
		player[9] = playerSprites.grabImage48(1, 4, 48, 48);
		player[10] = playerSprites.grabImage48(2, 4, 48, 48);
		player[11] = playerSprites.grabImage48(3, 4, 48, 48);
		
		fauna[0] = faunaSprites.grabImage32(1, 1, 32, 32);
		fauna[1] = faunaSprites.grabImage32(2, 1, 32, 32);
		fauna[2] = faunaSprites.grabImage32(3, 1, 32, 32);
		fauna[3] = faunaSprites.grabImage32(1, 2, 32, 32);
		fauna[4] = faunaSprites.grabImage32(2, 2, 32, 32);
		fauna[5] = faunaSprites.grabImage32(3, 2, 32, 32);
		fauna[6] = faunaSprites.grabImage32(1, 3, 32, 32);
		fauna[7] = faunaSprites.grabImage32(2, 3, 32, 32);
		fauna[8] = faunaSprites.grabImage32(3, 3, 32, 32);
		fauna[9] = faunaSprites.grabImage32(1, 4, 48, 48);
		fauna[10] = faunaSprites.grabImage32(2, 4, 32, 32);
		fauna[11] = faunaSprites.grabImage32(3, 4, 32, 32);
		
		wolf[0] = wolfSprites.grabImage48(1, 1, 48, 48);
		wolf[1] = wolfSprites.grabImage48(2, 1, 48, 48);
		wolf[2] = wolfSprites.grabImage48(3, 1, 48, 48);
		wolf[3] = wolfSprites.grabImage48(1, 2, 48, 48);
		wolf[4] = wolfSprites.grabImage48(2, 2, 48, 48);
		wolf[5] = wolfSprites.grabImage48(3, 2, 48, 48);
		wolf[6] = wolfSprites.grabImage48(1, 3, 48, 48);
		wolf[7] = wolfSprites.grabImage48(2, 3, 48, 48);
		wolf[8] = wolfSprites.grabImage48(3, 3, 48, 48);
		wolf[9] = wolfSprites.grabImage48(1, 4, 48, 48);
		wolf[10] = wolfSprites.grabImage48(2, 4, 48, 48);
		wolf[11] = wolfSprites.grabImage48(3, 4, 48, 48);
		
		icons[0] = iconSprites.grabAtPoint(336, 602, 24, 24);
		icons[1] = iconSprites.grabAtPoint(192, 146, 24, 24);
		
	}

}
