package someRPG;

public class Collision {
	
	public Collision() {
		
	}
	
	public boolean getCollision(GameObject object1, GameObject object2) {
		if(object1.getBounds().intersects(object2.getBounds())) {
			return true;
		}else{
			return false;
		}
	}
	
	public boolean getAggro(Monster object1, GameObject object2) {
		if(object1.getAggro().intersects(object2.getBounds())) {
			return true;
		}else{
			return false;
		}
	}
	


}
