package someRPG;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class InventoryObject extends GameObject{
	
	private ID id;
	private String name;
	
	public InventoryObject(int x, int y, ID id, String name) {
		super(x, y, id);
		this.id = id;
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.fillRect((int)x, (int)y, 16, 16);
//		g2d.setColor(Color.gray);
//		g2d.fillRect(x+1, y+1, 62, 14);
	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public ID getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	

}
