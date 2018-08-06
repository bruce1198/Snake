package snake.game;

import java.io.Serializable;

public class DynamicUIData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public int window;
	public Player p1;
	public Player p2;
	public Snake s1;
	public Snake s2;
	public Point[] points;
	public int numberOfApple;
	public int nextDir1;
	public int nextDir2;
	public boolean[] valid;
	public boolean GAMEOVER;
	public boolean PAUSE1;
	public boolean PAUSE2;
	public boolean inGame1;
	public boolean inGame2;
	public boolean waitOtherPlayer;
	public int speed;
	public DynamicUIData() {
		window = 0;
		p1 = new Player(5);
		p2 = new Player(5, 2);
		try {
			s1 = p1.getSnake();
			s2 = p2.getSnake();
		} catch (Exception e) {
			
        }
		valid = new boolean[2];
        points = new Point[100];
		numberOfApple = 0;
		nextDir1 = 0;
		nextDir2 = 1;
		GAMEOVER = false;
		PAUSE1 = false;
		PAUSE2 = false;
		inGame1 = false;
		inGame2 = false;
		waitOtherPlayer = true;
		speed = 120;
	}
	
}
