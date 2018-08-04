package snake.game;

import java.io.Serializable;

public class DynamicUIData implements Serializable{
	
	public Player p1;
	public Player p2;
	public Snake s1;
	public Snake s2;
	public Point[] points;
	public int numberOfPoint;
	public int nextDir1;
	public int nextDir2;
	public DynamicUIData() {
		p1 = new Player(5);
		p2 = new Player(5, 2);
		try {
			s1 = p1.getSnake();
			s2 = p2.getSnake();
		} catch (Exception e) {
			
        }
        points = new Point[100];
		numberOfPoint = 0;
		nextDir1 = 0;
		nextDir2 = 1;
	}
	
}
