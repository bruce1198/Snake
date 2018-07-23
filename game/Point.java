package game;

import java.awt.*;

public class Point {
    public static int eggLeft = 2;
    public static int put = 10;
    public static Color color = Color.YELLOW;
    int x;
    int y;
    Point() {
        int x, y;
        while(true) {
            x = 100 + 20 * (int)(30*Math.random());
            y = 100 + 20 * (int)(20*Math.random());
            if(isValid(x, y))
                break;
        }
        this.x = x;
        this.y = y;
    }
    private boolean isValid(int x, int y) {
        Wall[] walls = GameWindow.walls;
        SnakeCave[] snakeCaves = GameWindow.snakeCaves;
        for(int i=0; i<Wall.WALL_NUMBER; i++) {
            for(int row=0; row<walls[i].height; row++) {
                for(int col=0; col<walls[i].width; col++) {
                    if(x==walls[i].x+20*col && y==walls[i].y+20*row)
                        return false;
                }
            }
        }
        for(int i=0; i<SnakeCave.CAVE_NUMBER; i++) {
            if(x>=snakeCaves[i].x-20 && x<=snakeCaves[i].x+20 ||
                y>=snakeCaves[i].y-60 && y<=snakeCaves[i].y)
                return false;
        }
        return true;
    }
}