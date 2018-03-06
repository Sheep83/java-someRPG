package someRPG;

import java.awt.Point;
import java.util.LinkedList;

public class AI {
	
	public ID type;
	private Handler handler;
	private LinkedList<GameObject> threats;
	private Monster subject;
	private Pattern pattern;
	private int currentPoint;
	
	public AI( Monster subject, Handler handler, Pattern pattern) {
		
		this.subject = subject;
		this.handler = handler;
		this.threats =  new LinkedList<GameObject>();
		this.type = subject.getId();
		this.pattern = pattern;
//		this.patrolPoints = this.pattern.arr.size() / 2;
		this.currentPoint = 0;
	}
	

	public LinkedList<GameObject> getThreats() {
		threats = new LinkedList<GameObject>();
		if(this.type == ID.Fauna) {
		for(int i = 0; i < handler.objects.size(); i++) {
			if(handler.objects.get(i).getId() == ID.Player || handler.objects.get(i).getId() == ID.Monster) {
				GameObject threat = handler.objects.get(i);
				threats.add(threat);
			}
		}
		}else
			if(this.type == ID.Monster) {
				for(int i = 0; i < handler.objects.size(); i++) {
					if(handler.objects.get(i).getId() == ID.Player || handler.objects.get(i).getId() == ID.Fauna) {
						GameObject threat = handler.objects.get(i);
						threats.add(threat);
						}else if(handler.objects.get(i).getId() == ID.Player) {
							GameObject threat = handler.objects.get(i);
							threats.add(threat);
						}	
					}
				}			
		return threats;
	}
	
	public float getDistance(GameObject subject, GameObject threat) {
//		float diffX = subject.getX() - threat.getX();
//		float diffY = subject.getY() - threat.getY();
		float distance = (float) Math.sqrt( (subject.getX() - threat.getX()) * (subject.getX() - threat.getX()) + (subject.getY() - threat.getY()) * (subject.getY() - threat.getY()));
		return distance;
	}
	
	public GameObject getClosestThreat(LinkedList<GameObject> threats) {
		float distToThreat;
		float distToCurrentThreat;
		GameObject closestThreat;
		if(threats.size() > 0) {
			distToThreat = getDistance(subject, threats.get(0));
			closestThreat =  threats.get(0);
		for(int i = 0; i < threats.size(); i++) {
			
			distToCurrentThreat = getDistance(subject, threats.get(i));
			
			if(distToCurrentThreat < distToThreat) {
				closestThreat =  threats.get(i);
				distToThreat = getDistance(subject, threats.get(i));
				}
			}
		return closestThreat;
		}else {
			return null;
		}
		
	}
	
	public void react(LinkedList<GameObject> threats) {
		GameObject closestThreat = getClosestThreat(threats);
		
		/////Fauna AI
		if(this.type == ID.Fauna) {

			if(subject.getAggro().intersects(closestThreat.getBounds())) {
				run(closestThreat);
				
			}else {
				subject.setSpooked(false);
				subject.setVelX(0);
				subject.setVelY(0);	
			}
		}
		
		
		/////Monster AI
		else if(this.type == ID.Monster) {
			if(closestThreat.getId() == ID.Player) {
				if(subject.getAggro().intersects(closestThreat.getBounds())) {
					if(subject.getBounds().intersects(closestThreat.getBounds())) {
						//subject.attack(closestThreat, selectSkill(closestThreat));
//						subject.setSpooked(true);
						if(subject.checkCooldown(1)){
							subject.setVelX(0);
							subject.setVelY(0);
							subject.attack(closestThreat);
						}	
					}else {
						subject.setSpooked(true);
						moveTo(closestThreat);
					}
					
			}else {
				patrol();
				
//				subject.setSpooked(false);
//				subject.setVelX(0);
//				subject.setVelY(0);
			}
			}else if(closestThreat.getId() == ID.Fauna){
				if(subject.getAggro().intersects(closestThreat.getBounds())) {
					if(subject.getBounds().intersects(closestThreat.getBounds())) {
						subject.setSpooked(true);
						if(subject.checkCooldown(1)){
							subject.setVelX(0);
							subject.setVelY(0);
							subject.attack(closestThreat);
						}	
					}else {
						subject.setSpooked(true);
						moveTo(closestThreat);
					}
					
			}else {
				patrol();
//				moveToPoint(this.pattern.arr.get(0), this.pattern.arr.get(1));
//				subject.setSpooked(false);
//				subject.setVelX(0);
//				subject.setVelY(0);
			}
			}
		}
//		moveToPoint(this.pattern.arr.get(0), this.pattern.arr.get(1));
	
	}
	
	private void patrol() {
		
		if(subject.getBounds().contains(pattern.arr.get(this.currentPoint))) {
			this.currentPoint++;
			if(this.currentPoint > 3) {
				this.currentPoint = 0;
			}	
		}
		moveToPoint(this.pattern.arr.get(currentPoint).x, this.pattern.arr.get(currentPoint).y);
	}


	private void moveTo(GameObject target) {
		int speed = 1;
		if(subject.getId() == ID.Monster) {
			speed = 2;
		}
		float diffX = subject.getX() - target.getX();
		float diffY = subject.getY() - target.getY();
		float distance = (float) Math.sqrt( (subject.getX() - target.getX()) * (subject.getX() - target.getX()) + (subject.getY() - target.getY()) * (subject.getY() - target.getY()));
		subject.velX = speed*((-1/distance) * diffX);
		subject.velY = speed*((-1/distance) * diffY);
	}
	
	private void moveToPoint(int pointX, int pointY) {
		int speed = 1;
		if(subject.getId() == ID.Monster) {
			speed = 2;
		}
		float diffX = subject.getX() - pointX;
		float diffY = subject.getY() - pointY;
		float distance = (float) Math.sqrt( (subject.getX() - pointX) * (subject.getX() - pointX) + (subject.getY() - pointY) * (subject.getY() - pointY));
		subject.velX = speed*((-1/distance) * diffX);
		subject.velY = speed*((-1/distance) * diffY);
	}


	private void run(GameObject target) {
		float diffX = subject.getX() - target.getX();
		float diffY = subject.getY() - target.getY();
		float distance = (float) Math.sqrt( (subject.getX() - target.getX()) * (subject.getX() - target.getX()) + (subject.getY() - target.getY()) * (subject.getY() - target.getY()));
		subject.velX = ((-1/distance) * diffX)* -1;
		subject.velY = ((-1/distance) * diffY) * -1;
	}

}
