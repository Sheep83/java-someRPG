package someRPG;

import java.util.ArrayList;

public class Level {
	
	private ArrayList<Integer> arr = new ArrayList<Integer>();
	
	public Level(Textures textures) {
//		super(x, y, id);
//		this.handler = handler;
//		this.velX = (r.nextInt(4) + 1);
//		this.velY = -4;
	}
	
	public void addTile(int amount) {
		for(int i = 0; i < amount; i++) {
			this.arr.add(1);
			}
		
	}
	
	public void addSolidTile(int amount) {
		for(int i = 0; i < amount; i++) {
			this.arr.add(2);
			}
		
	}
	
	public void clearLevel() {
		for(int i = 0; i < arr.size(); i++) {
			arr.remove(i);
			}
	}
	
	public void build(Handler handler, int level) {
		
		int tileX = 0;
		int tileY = 0;
		this.arr = new ArrayList<Integer>();
		
		if (level == 1) {
			addSolidTile(20);
			for(int i = 0; i < 10; i++) {
				addSolidTile(1);
				addTile(18);
				addSolidTile(1);
			}
			addSolidTile(20);
			for(int i = 0; i < 10; i++) {
				addSolidTile(1);
				addTile(18);
				addSolidTile(1);
			}
			addSolidTile(20);
		}
//		}else if (level == 2) {
//			addTile(16, 2);
//			addTile(16, 1);
//			addTile(16, 2);
//			addTile(16, 1);
//		}else if (level == 3) {
//			addTile(16, 2);
//			addTile(16, 3);
//			addTile(16, 2);
//			addTile(16, 1);		
//		}else if (level == 99) {
//			addTile(16, 2);
//			addTile(16, 3);
//			addTile(16, 2);
//			addTile(16, 1);		
//		}
		
		for(int i = 0; i < arr.size(); i++) {
				if(arr.get(i) == 1) {
				handler.addTile(new GameTile(tileX, tileY, ID.Tile, true, handler));
				tileX += 64;
				if((i + 1) % 20 == 0) {
				tileX = 0;
				tileY += 64;
				}
			}else if(arr.get(i) == 2) {
				handler.addTile(new GameTile(tileX, tileY, ID.Tile, false, handler));
				tileX += 64;
				if((i + 1) % 20 == 0) {
				tileX = 0;
				tileY += 64;
				}
			}else if(arr.get(i) == 3) {
//				handler.addObject(new Brick(brickX, brickY, ID.Brick, Bricktype.Metal));
				tileX += 64;
				if((i + 1) % 16 == 0) {
				tileX = 64;
				tileY += 32;
				}
			}else if(arr.get(i) == 0) {
//				handler.addObject(new Brick(brickX, brickY, ID.Brick, Bricktype.Metal, handler));
				tileX += 72;
				if((i + 1) % 16 == 0) {
				tileX = 64;
				tileY += 48;
				}
			}
		
	}
	
	}
	

}

