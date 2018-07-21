package game;

import java.awt.*;

public class SnakeCave {
    public static int caveLeft = 2;
    public static int put = 10;
    public static Color color = Color.BLACK;
    int x;
    int y;
    SnakeCave() {
        this.x = 100 + 20 * (int)(30*Math.random());;
        this.y = 100 + 20 * (int)(20*Math.random());;
    }
}