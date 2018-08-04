package snake.game;

import snake.util.*;
import java.io.*;

public class Wall implements Serializable {
    public static int WALL_NUMBER = 4;
    public static int WALL_KIND = 1;
    public int x;
    public int y;
    public int width;
    public int height;
    public Wall() {
        this.x = 200 + 20 * (int)(30*Math.random());
        this.y = 200 + 20 * (int)(20*Math.random());
        this.width = 40 + (int)(40*Math.random());
        this.height = 20;
    }
    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public static Wall[] getWalls() {
        int choice = (int)(5*Math.random());
        switch(choice) {
            case 0:
                return getWallOne();
            case 1:
                return getWallTwo();
            case 2:
                return getWallThree();
            case 3:
                return getWallFour();
            case 4:
            	return getWallFive();
        }
        return null;
        //return getWallFive();
    }
    public static Wall[] getWallOne() {
        Wall[] walls = new Wall[]{
            //new Wall(8*Setting.unit, 10*Setting.unit, 1, 10),
            //new Wall(8*Setting.unit, 20*Setting.unit, 8, 1),
            new Wall(2*Setting.unit, 2*Setting.unit, 36, 1),
            new Wall(2*Setting.unit, 27*Setting.unit, 36, 1),
            new Wall(2*Setting.unit, 2*Setting.unit, 1, 12),
            new Wall(2*Setting.unit, 15*Setting.unit, 1, 12),
            new Wall(37*Setting.unit, 2*Setting.unit, 1, 12),
            new Wall(37*Setting.unit, 15*Setting.unit, 1, 12)
        };
        WALL_NUMBER = 6;
        WALL_KIND = 1;
        return walls;
    }
    public static Wall[] getWallTwo() {
        Wall[] walls = new Wall[]{
            new Wall(4*Setting.unit, 2*Setting.unit, 1, 6),
            new Wall(4*Setting.unit, 14*Setting.unit, 1, 6),
            new Wall(4*Setting.unit, 24*Setting.unit, 1, 4),
            //
            new Wall(8*Setting.unit, 24*Setting.unit, 1, 4),
            new Wall(9*Setting.unit, 24*Setting.unit, 5, 1),
            //
            new Wall(21*Setting.unit, 25*Setting.unit, 1, 3),
            new Wall(25*Setting.unit, 3*Setting.unit, 5, 1),
            new Wall(27*Setting.unit, 9*Setting.unit, 5, 1),
            new Wall(27*Setting.unit, 25*Setting.unit, 5, 1),
            new Wall(31*Setting.unit, 13*Setting.unit, 1, 6),
            new Wall(21*Setting.unit, 13*Setting.unit, 10, 1),
            new Wall(21*Setting.unit, 2*Setting.unit, 1, 8),
            new Wall(11*Setting.unit, 10*Setting.unit, 1, 8),
            new Wall(11*Setting.unit, 18*Setting.unit, 6, 1),
            new Wall(11*Setting.unit, 2*Setting.unit, 1, 3),
            new Wall(16*Setting.unit, 5*Setting.unit, 1, 8),
            //
            new Wall(36*Setting.unit, 2*Setting.unit, 1, 6),
            new Wall(36*Setting.unit, 14*Setting.unit, 1, 6),
            new Wall(36*Setting.unit, 24*Setting.unit, 1, 4)
        };
        WALL_NUMBER = 19;
        WALL_KIND = 2;
        return walls;
    }
    public static Wall[] getWallThree() {
        Wall[] walls = new Wall[]{
            new Wall(6*Setting.unit, 6*Setting.unit, 28, 1),
            new Wall(6*Setting.unit, 23*Setting.unit, 28, 1),
            new Wall(6*Setting.unit, 8*Setting.unit, 4, 1),
            new Wall(6*Setting.unit, 14*Setting.unit, 4, 1),
            new Wall(6*Setting.unit, 16*Setting.unit, 4, 1),
            new Wall(6*Setting.unit, 21*Setting.unit, 4, 1),
            new Wall(30*Setting.unit, 8*Setting.unit, 4, 1),
            new Wall(30*Setting.unit, 14*Setting.unit, 4, 1),
            new Wall(30*Setting.unit, 16*Setting.unit, 4, 1),
            new Wall(30*Setting.unit, 21*Setting.unit, 4, 1),
            new Wall(10*Setting.unit, 8*Setting.unit, 1, 7),
            new Wall(10*Setting.unit, 16*Setting.unit, 1, 6),
            new Wall(29*Setting.unit, 8*Setting.unit, 1, 7),
            new Wall(29*Setting.unit, 16*Setting.unit, 1, 6)
        };
        WALL_NUMBER = 14;
        WALL_KIND = 3;
        return walls;
    }
    public static Wall[] getWallFour() {
        Wall[] walls = new Wall[]{
            new Wall(5*Setting.unit, 8*Setting.unit, 6, 1),
            new Wall(5*Setting.unit, 10*Setting.unit, 4, 1),
            new Wall(8*Setting.unit, 11*Setting.unit, 1, 12),
            new Wall(10*Setting.unit, 9*Setting.unit, 1, 12),
            new Wall(8*Setting.unit, 23*Setting.unit, 23, 1),
            new Wall(10*Setting.unit, 21*Setting.unit, 19, 1),
            new Wall(28*Setting.unit, 8*Setting.unit, 6, 1),
            new Wall(30*Setting.unit, 10*Setting.unit, 4, 1),
            new Wall(30*Setting.unit, 11*Setting.unit, 1, 12),
            new Wall(28*Setting.unit, 9*Setting.unit, 1, 12),
            new Wall(15*Setting.unit, 8*Setting.unit, 1, 4),
            new Wall(15*Setting.unit, 14*Setting.unit, 1, 4),
            new Wall(23*Setting.unit, 8*Setting.unit, 1, 4),
            new Wall(23*Setting.unit, 14*Setting.unit, 1, 4)
        };
        WALL_NUMBER = 14;
        WALL_KIND = 4;
        return walls;
    }
    public static Wall[] getWallFive() {
        Wall[] walls = new Wall[]{
            new Wall(5*Setting.unit, 8*Setting.unit, 6, 1),
            new Wall(5*Setting.unit, 10*Setting.unit, 4, 1),
            new Wall(8*Setting.unit, 11*Setting.unit, 1, 12),
            new Wall(10*Setting.unit, 9*Setting.unit, 1, 12),
            new Wall(8*Setting.unit, 23*Setting.unit, 23, 1),
            new Wall(10*Setting.unit, 21*Setting.unit, 19, 1),
            new Wall(28*Setting.unit, 8*Setting.unit, 6, 1),
            new Wall(30*Setting.unit, 10*Setting.unit, 4, 1),
            new Wall(30*Setting.unit, 11*Setting.unit, 1, 12),
            new Wall(28*Setting.unit, 9*Setting.unit, 1, 12),
            new Wall(15*Setting.unit, 8*Setting.unit, 1, 4),
            new Wall(15*Setting.unit, 14*Setting.unit, 1, 4),
            new Wall(23*Setting.unit, 8*Setting.unit, 1, 4),
            new Wall(23*Setting.unit, 14*Setting.unit, 1, 4)
        };
        WALL_NUMBER = 0;
        WALL_KIND = 5;
        return walls;
    }
}