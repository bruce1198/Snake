package game;

public class Wall {
    public static int WALL_NUMBER = 4;
    public static int WALL_KIND = 1;
    int x;
    int y;
    int width;
    int height;
    Wall() {
        this.x = 200 + 20 * (int)(30*Math.random());;
        this.y = 200 + 20 * (int)(20*Math.random());;
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
        int choice = (int)(4*Math.random());
        switch(choice) {
            case 0:
                return getWallOne();
            case 1:
                return getWallTwo();
            case 2:
                return getWallThree();
            case 3:
                return getWallFour();
        }
        return null;
    }
    public static Wall[] getWallOne() {
        Wall[] walls = new Wall[]{
            new Wall(160, 160, 1, 10),
            new Wall(160, 360, 8, 1),
            new Wall(480, 100, 1, 6),
            new Wall(480, 240, 1, 6),
            new Wall(600, 300, 1, 10),
            new Wall(500, 500, 6, 1)
        };
        WALL_NUMBER = 6;
        WALL_KIND = 1;
        return walls;
    }
    public static Wall[] getWallTwo() {
        Wall[] walls = new Wall[]{
            new Wall(160, 160, 1, 10),
            new Wall(160, 360, 8, 1),
            new Wall(480, 100, 1, 6),
            new Wall(480, 240, 1, 6),
            new Wall(600, 300, 1, 10),
            new Wall(500, 500, 6, 1)
        };
        WALL_NUMBER = 6;
        WALL_KIND = 2;
        return walls;
    }
    public static Wall[] getWallThree() {
        Wall[] walls = new Wall[]{
            new Wall(160, 160, 1, 10),
            new Wall(160, 360, 8, 1),
            new Wall(480, 100, 1, 6),
            new Wall(480, 240, 1, 6),
            new Wall(600, 300, 1, 10),
            new Wall(500, 500, 6, 1)
        };
        WALL_NUMBER = 6;
        WALL_KIND = 3;
        return walls;
    }
    public static Wall[] getWallFour() {
        Wall[] walls = new Wall[]{
            new Wall(160, 160, 1, 10),
            new Wall(160, 360, 8, 1),
            new Wall(480, 100, 1, 6),
            new Wall(480, 240, 1, 6),
            new Wall(600, 300, 1, 10),
            new Wall(500, 500, 6, 1)
        };
        WALL_NUMBER = 6;
        WALL_KIND = 4;
        return walls;
    }
}