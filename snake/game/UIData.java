package snake.game;

import java.io.Serializable;

public class UIData extends DynamicUIData implements Serializable{
	
	public Wall[] walls;
	public Point[] points;
	public SnakeCave[] snakeCaves;
	public int numberOfWall;
	public int numberOfCave;
	public UIData() {
		super();
		walls = Wall.getWalls();
		numberOfWall = Wall.WALL_NUMBER;
		snakeCaves = SnakeCave.getSnakeCaves();
		numberOfCave = SnakeCave.CAVE_NUMBER;
	}
	
}
