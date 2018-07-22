package game;

import java.awt.*;

public class SnakeCave {
    //public static int caveLeft = 2;
    //public static int put = 10;
    public static Color color = Color.BLACK;
    int x;
    int y;
    public static SnakeCave[] all = new SnakeCave[4];
    public static int number = 0;
    SnakeCave() {
        int x, y;
        while(true) {
            x = 100 + 20 * (int)(30*Math.random());
            y = 100 + 20 * (int)(20*Math.random());
            if(isValid(x, y)) {
                SnakeCave.all[SnakeCave.number] = new SnakeCave(x, y);
                SnakeCave.number++;
                break;
            }
        }
        System.out.println("Hi");
        System.out.println(SnakeCave.number+": "+x+", "+y);
        this.x = x;
        this.y = y;
    }
    SnakeCave(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    private boolean isValid(int x, int y) {
        Wall[] walls = GameWindow.walls;
        /*for(int i=0; i<Wall.WALL_NUMBER; i++) {
            for(int row=0; row<walls[i].height; row++) {
                for(int col=0; col<walls[i].width; col++) {
                    if(x>=walls[i].x+20*col-60 && x<=walls[i].x+20*col+60 ||
                        y>=walls[i].y+20*row-60 && y<=walls[i].y+20*row+60)
                        return false;
                }
            }
        }
        for(int i=0; i<SnakeCave.number; i++) {
            if(x>=SnakeCave.all[i].x-60 && x<=SnakeCave.all[i].x+60 ||
                y>=SnakeCave.all[i].y-60 && y<=SnakeCave.all[i].y+60)
                return false;
        }*/
        return true;
    }
}