package game;

public class Wall {
    public static int WALL_NUMBER = 4;
    public static int WALL_KIND = 1;
    int x;
    int y;
    int width;
    int height;
    Wall() {
        this.x = 200 + 20 * (int)(30*Math.random());
        this.y = 200 + 20 * (int)(20*Math.random());
        this.width = 40 + (int)(40*Math.random());
        this.height = 20;
    }
    Wall(int x, int y, int width, int height) {
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
            //new Wall(8*GameWindow.unit, 10*GameWindow.unit, 1, 10),
            //new Wall(8*GameWindow.unit, 20*GameWindow.unit, 8, 1),
            new Wall(2*GameWindow.unit, 2*GameWindow.unit, 36, 1),
            new Wall(2*GameWindow.unit, 27*GameWindow.unit, 36, 1),
            new Wall(2*GameWindow.unit, 2*GameWindow.unit, 1, 12),
            new Wall(2*GameWindow.unit, 15*GameWindow.unit, 1, 12),
            new Wall(37*GameWindow.unit, 2*GameWindow.unit, 1, 12),
            new Wall(37*GameWindow.unit, 15*GameWindow.unit, 1, 12)
        };
        WALL_NUMBER = 6;
        WALL_KIND = 1;
        return walls;
    }
    public static Wall[] getWallTwo() {
        Wall[] walls = new Wall[]{
            new Wall(4*GameWindow.unit, 2*GameWindow.unit, 1, 6),
            new Wall(4*GameWindow.unit, 14*GameWindow.unit, 1, 6),
            new Wall(4*GameWindow.unit, 24*GameWindow.unit, 1, 4),
            //
            new Wall(8*GameWindow.unit, 24*GameWindow.unit, 1, 4),
            new Wall(9*GameWindow.unit, 24*GameWindow.unit, 5, 1),
            //
            new Wall(21*GameWindow.unit, 25*GameWindow.unit, 1, 3),
            new Wall(25*GameWindow.unit, 3*GameWindow.unit, 5, 1),
            new Wall(27*GameWindow.unit, 9*GameWindow.unit, 5, 1),
            new Wall(27*GameWindow.unit, 25*GameWindow.unit, 5, 1),
            new Wall(31*GameWindow.unit, 13*GameWindow.unit, 1, 6),
            new Wall(21*GameWindow.unit, 13*GameWindow.unit, 10, 1),
            new Wall(21*GameWindow.unit, 2*GameWindow.unit, 1, 8),
            new Wall(11*GameWindow.unit, 10*GameWindow.unit, 1, 8),
            new Wall(11*GameWindow.unit, 18*GameWindow.unit, 6, 1),
            new Wall(11*GameWindow.unit, 2*GameWindow.unit, 1, 3),
            new Wall(16*GameWindow.unit, 5*GameWindow.unit, 1, 8),
            //
            new Wall(36*GameWindow.unit, 2*GameWindow.unit, 1, 6),
            new Wall(36*GameWindow.unit, 14*GameWindow.unit, 1, 6),
            new Wall(36*GameWindow.unit, 24*GameWindow.unit, 1, 4)
        };
        WALL_NUMBER = 19;
        WALL_KIND = 2;
        return walls;
    }
    public static Wall[] getWallThree() {
        Wall[] walls = new Wall[]{
            new Wall(6*GameWindow.unit, 6*GameWindow.unit, 28, 1),
            new Wall(6*GameWindow.unit, 23*GameWindow.unit, 28, 1),
            new Wall(6*GameWindow.unit, 8*GameWindow.unit, 4, 1),
            new Wall(6*GameWindow.unit, 14*GameWindow.unit, 4, 1),
            new Wall(6*GameWindow.unit, 16*GameWindow.unit, 4, 1),
            new Wall(6*GameWindow.unit, 21*GameWindow.unit, 4, 1),
            new Wall(30*GameWindow.unit, 8*GameWindow.unit, 4, 1),
            new Wall(30*GameWindow.unit, 14*GameWindow.unit, 4, 1),
            new Wall(30*GameWindow.unit, 16*GameWindow.unit, 4, 1),
            new Wall(30*GameWindow.unit, 21*GameWindow.unit, 4, 1),
            new Wall(10*GameWindow.unit, 8*GameWindow.unit, 1, 7),
            new Wall(10*GameWindow.unit, 16*GameWindow.unit, 1, 6),
            new Wall(29*GameWindow.unit, 8*GameWindow.unit, 1, 7),
            new Wall(29*GameWindow.unit, 16*GameWindow.unit, 1, 6)
        };
        WALL_NUMBER = 14;
        WALL_KIND = 3;
        return walls;
    }
    public static Wall[] getWallFour() {
        Wall[] walls = new Wall[]{
            new Wall(5*GameWindow.unit, 8*GameWindow.unit, 6, 1),
            new Wall(5*GameWindow.unit, 10*GameWindow.unit, 4, 1),
            new Wall(8*GameWindow.unit, 11*GameWindow.unit, 1, 12),
            new Wall(10*GameWindow.unit, 9*GameWindow.unit, 1, 12),
            new Wall(8*GameWindow.unit, 23*GameWindow.unit, 23, 1),
            new Wall(10*GameWindow.unit, 21*GameWindow.unit, 19, 1),
            new Wall(28*GameWindow.unit, 8*GameWindow.unit, 6, 1),
            new Wall(30*GameWindow.unit, 10*GameWindow.unit, 4, 1),
            new Wall(30*GameWindow.unit, 11*GameWindow.unit, 1, 12),
            new Wall(28*GameWindow.unit, 9*GameWindow.unit, 1, 12),
            new Wall(15*GameWindow.unit, 8*GameWindow.unit, 1, 4),
            new Wall(15*GameWindow.unit, 14*GameWindow.unit, 1, 4),
            new Wall(23*GameWindow.unit, 8*GameWindow.unit, 1, 4),
            new Wall(23*GameWindow.unit, 14*GameWindow.unit, 1, 4)
        };
        WALL_NUMBER = 14;
        WALL_KIND = 4;
        return walls;
    }
    public static Wall[] getWallFive() {
        Wall[] walls = new Wall[]{
            new Wall(5*GameWindow.unit, 8*GameWindow.unit, 6, 1),
            new Wall(5*GameWindow.unit, 10*GameWindow.unit, 4, 1),
            new Wall(8*GameWindow.unit, 11*GameWindow.unit, 1, 12),
            new Wall(10*GameWindow.unit, 9*GameWindow.unit, 1, 12),
            new Wall(8*GameWindow.unit, 23*GameWindow.unit, 23, 1),
            new Wall(10*GameWindow.unit, 21*GameWindow.unit, 19, 1),
            new Wall(28*GameWindow.unit, 8*GameWindow.unit, 6, 1),
            new Wall(30*GameWindow.unit, 10*GameWindow.unit, 4, 1),
            new Wall(30*GameWindow.unit, 11*GameWindow.unit, 1, 12),
            new Wall(28*GameWindow.unit, 9*GameWindow.unit, 1, 12),
            new Wall(15*GameWindow.unit, 8*GameWindow.unit, 1, 4),
            new Wall(15*GameWindow.unit, 14*GameWindow.unit, 1, 4),
            new Wall(23*GameWindow.unit, 8*GameWindow.unit, 1, 4),
            new Wall(23*GameWindow.unit, 14*GameWindow.unit, 1, 4)
        };
        WALL_NUMBER = 0;
        WALL_KIND = 5;
        return walls;
    }
}