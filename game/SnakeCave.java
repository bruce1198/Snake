package game;

import java.awt.*;

public class SnakeCave {
    public static int CAVE_NUMBER = 4;
    public static int CAVE_KIND = 1;
    int x;
    int y;
    SnakeCave(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    public static SnakeCave[] getSnakeCaves() {
        switch(Wall.WALL_KIND) {
            case 1:
                return getSnakeCaveOne();
            case 2:
                return getSnakeCaveTwo();
            case 3:
                return getSnakeCaveThree();
            case 4:
                return getSnakeCaveFour();
        }
        return null;
    }
    public static SnakeCave[] getSnakeCaveOne() {
        SnakeCave[] SnakeCaves = new SnakeCave[]{
            new SnakeCave(60, 160),
            new SnakeCave(160, 500),
            new SnakeCave(680, 100),
            new SnakeCave(480, 440)
        };
        CAVE_NUMBER = 4;
        CAVE_KIND = 1;
        return SnakeCaves;
    }
    public static SnakeCave[] getSnakeCaveTwo() {
        SnakeCave[] SnakeCaves = new SnakeCave[]{
            new SnakeCave(540, 120),
            new SnakeCave(240, 580),
            new SnakeCave(500, 400)
        };
        CAVE_NUMBER = 3;
        CAVE_KIND = 2;
        return SnakeCaves;
    }
    public static SnakeCave[] getSnakeCaveThree() {
        SnakeCave[] SnakeCaves = new SnakeCave[]{
            new SnakeCave(60, 200),
            new SnakeCave(60, 400),
            new SnakeCave(720, 200),
            new SnakeCave(720, 400),
            new SnakeCave(400, 200),
            new SnakeCave(400, 400)
        };
        CAVE_NUMBER = 6;
        CAVE_KIND = 3;
        return SnakeCaves;
    }
    public static SnakeCave[] getSnakeCaveFour() {
        SnakeCave[] SnakeCaves = new SnakeCave[]{
            new SnakeCave(400, 160),
            new SnakeCave(400, 400),
            new SnakeCave(40, 400),
            new SnakeCave(740, 400)
        };
        CAVE_NUMBER = 4;
        CAVE_KIND = 4;
        return SnakeCaves;
    }
}