package someRPG;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameTile extends GameObject{
	
	public boolean passable, selected;
	private Handler handler;
	private Rectangle topBounds, bottomBounds, leftBounds, rightBounds;

	public GameTile(float x, float y, ID id, boolean passable, Handler handler) {
		super(x, y, id);
		this.passable = passable;
		this.handler = handler;
//		this.topBounds = new Rectangle((int)(x + Game.camera.offsetX), (int)(y + Game.camera.offsetY), 64, 2);
//		this.bottomBounds = new Rectangle((int)(x + Game.camera.offsetX), (int)(y + Game.camera.offsetY)+64, 64, 2);
//		this.leftBounds = new Rectangle((int)(x + Game.camera.offsetX), (int)(y + Game.camera.offsetY), 2, 64);
//		this.rightBounds = new Rectangle((int)(x + Game.camera.offsetX)+64, (int)(y + Game.camera.offsetY), 2, 64);
		
	}

	@Override
	public void tick() {
		
		if(!passable) {
			Player player = (Player)handler.getPlayer();
			
			
			if(player.getBounds().intersects(this.getTopBounds())) {
				player.setColliding(true);
//				Game.camera.offsetY = 0;
//				player.setVelX(0);
//				player.setVelY(0);
				player.setY((int)player.getY() +  - 5);
			}else
				if(player.getBounds().intersects(this.getBottomBounds())) {
					Game.camera.offsetY = 0;
//					player.setVelX(0);
//					player.setVelY(0);
//					player.setY((int)player.getY() + 5);
				}else
					if(player.getBounds().intersects(this.getLeftBounds())) {
//						player.setVelX(0);
//						player.setVelY(0);
//						player.setX((int)player.getX() - 10);
						Game.camera.offsetX = 0;
					}else
						if(player.getBounds().intersects(this.getRightBounds())) {
							player.setVelX(0);
							player.setVelY(0);
							player.setX((int)player.getX() + 10);
						}
				
			
			
			
			
			
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(this.passable) {
			g2d.setColor(Color.black);
			g2d.fillRect((int)(x + Game.camera.offsetX), (int)(y + Game.camera.offsetY), 64, 64);
			if(this.selected) {
				g2d.setColor(Color.red);
				g2d.fillRect((int)(x + Game.camera.offsetX) +1, (int)(y + Game.camera.offsetY) +1, 62, 62);
			}else {
			g2d.setColor(Color.green);
			g2d.fillRect((int)(x + Game.camera.offsetX) +1, (int)(y + Game.camera.offsetY) +1, 62, 62);
			}
		}else {
			g2d.setColor(Color.black);
			g2d.fillRect((int)(x + Game.camera.offsetX), (int)(y + Game.camera.offsetY), 64, 64);
			g2d.setColor(Color.gray);
			g2d.fillRect((int)(x + Game.camera.offsetX) +1, (int)(y + Game.camera.offsetY) +1, 62, 62);
//			g2d.setColor(Color.red);
//			g2d.draw(getTopBounds());
		}
		
//		g2d.setColor(Color.red);
//		g2d.draw(topBounds);
//		g2d.draw(bottomBounds);
//		g2d.draw(leftBounds);
//		g2d.draw(rightBounds);
		
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)(x + Game.camera.offsetX), (int)(y + Game.camera.offsetY), 64, 64);
	}
	
	public Rectangle getTopBounds() {
		return new Rectangle((int)(x + Game.camera.offsetX), (int)(y + Game.camera.offsetY), 64, 2);
	}
	
	public Rectangle getBottomBounds() {
		return new Rectangle((int)(x + Game.camera.offsetX), (int)(y + Game.camera.offsetY)+64, 64, 2);
	}
	
	public Rectangle getLeftBounds() {
		return new Rectangle((int)(x + Game.camera.offsetX), (int)(y + Game.camera.offsetY), 2, 64);
	}
	
	public Rectangle getRightBounds() {
		return new Rectangle((int)(x + Game.camera.offsetX)+64, (int)(y + Game.camera.offsetY), 2, 64);
	}

}
