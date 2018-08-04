package snake.game;

import java.awt.*;
import java.io.*;
import snake.util.Setting;

public class SnakeCave implements Serializable{
    public static int CAVE_NUMBER = 4;
    public static int CAVE_KIND = 1;
    public int x;
    public int y;
    public SnakeCave(int x, int y) {
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
            case 5:
            	return getSnakeCaveFive();
        }
        return null;
    }
    public static SnakeCave[] getSnakeCaveOne() {
        SnakeCave[] SnakeCaves = new SnakeCave[]{
            new SnakeCave(3*Setting.unit, 14*Setting.unit),
            new SnakeCave(36*Setting.unit, 14*Setting.unit),
            new SnakeCave(20*Setting.unit, 14*Setting.unit)
        };
        CAVE_NUMBER = 3;
        CAVE_KIND = 1;
        return SnakeCaves;
    }
    public static SnakeCave[] getSnakeCaveTwo() {
        SnakeCave[] SnakeCaves = new SnakeCave[]{
            new SnakeCave(32*Setting.unit, 10*Setting.unit),
            new SnakeCave(6*Setting.unit, 9*Setting.unit),
            new SnakeCave(25*Setting.unit, 20*Setting.unit)
        };
        CAVE_NUMBER = 3;
        CAVE_KIND = 2;
        return SnakeCaves;
    }
    public static SnakeCave[] getSnakeCaveThree() {
        SnakeCave[] SnakeCaves = new SnakeCave[]{
            new SnakeCave(5*Setting.unit, 7*Setting.unit),
            new SnakeCave(5*Setting.unit, 22*Setting.unit),
            new SnakeCave(34*Setting.unit, 7*Setting.unit),
            new SnakeCave(34*Setting.unit, 22*Setting.unit),
            new SnakeCave(20*Setting.unit, 15*Setting.unit)
        };
        CAVE_NUMBER = 5;
        CAVE_KIND = 3;
        return SnakeCaves;
    }
    public static SnakeCave[] getSnakeCaveFour() {
        SnakeCave[] SnakeCaves = new SnakeCave[]{
            new SnakeCave(19*Setting.unit, 12*Setting.unit),
            new SnakeCave(4*Setting.unit, 9*Setting.unit),
            new SnakeCave(34*Setting.unit, 9*Setting.unit)
        };
        CAVE_NUMBER = 3;
        CAVE_KIND = 4;
        return SnakeCaves;
    }
    public static SnakeCave[] getSnakeCaveFive() {
        SnakeCave[] SnakeCaves = new SnakeCave[]{
            new SnakeCave(16*Setting.unit, 10*Setting.unit),
            new SnakeCave(24*Setting.unit, 18*Setting.unit)
        };
        CAVE_NUMBER = 2;
        CAVE_KIND = 5;
        return SnakeCaves;
    }
}