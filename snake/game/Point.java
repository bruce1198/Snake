package snake.game;

import java.io.Serializable;

public class Point implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static int eggLeft = 2;
    public static int put = 10;
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}