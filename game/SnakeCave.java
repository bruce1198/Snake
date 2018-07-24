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
            new SnakeCave(3*GameWindow.unit, 14*GameWindow.unit),
            new SnakeCave(36*GameWindow.unit, 14*GameWindow.unit),
            new SnakeCave(20*GameWindow.unit, 14*GameWindow.unit)
        };
        CAVE_NUMBER = 3;
        CAVE_KIND = 1;
        return SnakeCaves;
    }
    public static SnakeCave[] getSnakeCaveTwo() {
        SnakeCave[] SnakeCaves = new SnakeCave[]{
            new SnakeCave(27*GameWindow.unit, 6*GameWindow.unit),
            new SnakeCave(8*GameWindow.unit, 7*GameWindow.unit),
            new SnakeCave(25*GameWindow.unit, 18*GameWindow.unit)
        };
        CAVE_NUMBER = 3;
        CAVE_KIND = 2;
        return SnakeCaves;
    }
    public static SnakeCave[] getSnakeCaveThree() {
        SnakeCave[] SnakeCaves = new SnakeCave[]{
            new SnakeCave(5*GameWindow.unit, 7*GameWindow.unit),
            new SnakeCave(5*GameWindow.unit, 22*GameWindow.unit),
            new SnakeCave(34*GameWindow.unit, 7*GameWindow.unit),
            new SnakeCave(34*GameWindow.unit, 22*GameWindow.unit),
            new SnakeCave(20*GameWindow.unit, 15*GameWindow.unit)
        };
        CAVE_NUMBER = 5;
        CAVE_KIND = 3;
        return SnakeCaves;
    }
    public static SnakeCave[] getSnakeCaveFour() {
        SnakeCave[] SnakeCaves = new SnakeCave[]{
            new SnakeCave(19*GameWindow.unit, 12*GameWindow.unit),
            new SnakeCave(4*GameWindow.unit, 9*GameWindow.unit),
            new SnakeCave(34*GameWindow.unit, 9*GameWindow.unit)
        };
        CAVE_NUMBER = 3;
        CAVE_KIND = 4;
        return SnakeCaves;
    }
}