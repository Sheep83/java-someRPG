package someRPG;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import someRPG.Game.STATE;
// public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 50, 800, 100, 50);
public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	public MouseInput(Handler handler) {
		this.handler = handler;
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int mouseX = e.getX();
		int mouseY = e.getY();
		Point point = new Point(mouseX, mouseY);
		if(Game.gameState == STATE.Menu) {
		if (mouseX >= Game.WIDTH / 2 - 50 && mouseX <= Game.WIDTH/2 + 50) {
			if(mouseY >= 600 && mouseY <= 650) {
				Game.setState(STATE.Game);
			}
		}
		}else if(Game.gameState == STATE.Game) {
			HUD hud = Game.getHUD();
			Player player = (Player)handler.getPlayer();
			//contains( mouseX, mouseY, rec.getMinX(), rect.getMinY(), rec.getWidth(), rec.getHeight() )
			for(int i = 0; i < player.getSkills().size(); i++) {
				if(hud.getSkillBoxBounds(i + 1).contains(point)){
					player.attack(player.getSelectedTarget(), player.getSkills().get(i));
					System.out.println( i + 1 + "clicked");
				}
			}
			if(hud.getPlayBoxBounds().contains(point)) {
//			Player player = (Player)handler.getPlayer();
//			player.moveTo(mouseX, mouseY);
			for (int i=0; i < handler.tiles.size(); i++) {
//				if(handler.objects.get(i).getId() == ID.Tile) {
					GameTile tile = (GameTile)handler.tiles.get(i);
					if(tile.getBounds().contains(point)) {
						tile.selected = true;
					}
				
			}
			}
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		// TODO Auto-generated method stub
		
	}
	
	

}

