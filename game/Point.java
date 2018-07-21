package game;

import java.awt.*;

public class Point {
    public static int eggLeft = 2;
    public static int put = 10;
    public static Color color = Color.YELLOW;
    int x;
    int y;
    Point() {
        int x = 20 * (int)(30*Math.random());
        int y = 20 * (int)(20*Math.random());
        this.x = 100+x;
        this.y = 100+y;
    }
}