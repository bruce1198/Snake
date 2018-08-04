package snake.game;

import java.io.Serializable;

public class UIData extends DynamicUIData implements Serializable{
	
	public Wall[] walls;
	public Point[] points;
	public SnakeCave[] snakeCaves;
	public UIData() {
		super();
	}
	
}
