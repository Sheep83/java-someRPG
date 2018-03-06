package someRPG;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

public class Pattern {
	public LinkedList<Point> arr = new LinkedList<Point>();
	
	
	public Pattern(int number) {
		setPattern(number);
		
	}
	
	public void setPattern(int number) {
		if(number == 1) {
			this.arr.add(new Point (100, 100));
			this.arr.add(new Point (100, 700));
			this.arr.add(new Point (1000, 700));
			this.arr.add(new Point (1000, 100));
			
			
		}else
			if(number == 2) {
				this.arr.add(new Point (1000, 700));
				this.arr.add(new Point (100, 100));
				this.arr.add(new Point (100, 700));
				this.arr.add(new Point (1000, 100));
				
				
			}
			else
				if(number == 3) {
					this.arr.add(new Point (100, 100));
					this.arr.add(new Point (1000, 700));
					this.arr.add(new Point (1000, 100));
					this.arr.add(new Point (100, 700));
					
					
				}
		
	}
	
	public Rectangle getBounds(Point point) {
		return new Rectangle(point.x, point.y, 16, 16);
	}

}
