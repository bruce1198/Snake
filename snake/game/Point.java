package snake.game;

import java.awt.*;
import java.io.Serializable;

public class Point implements Serializable{
    public static int eggLeft = 2;
    public static int put = 10;
    public static Color color = Color.YELLOW;
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}