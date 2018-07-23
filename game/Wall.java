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
            new Wall(480, 100, 1, 5),
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
            new Wall(80, 40, 1, 6),
            new Wall(80, 280, 1, 6),
            new Wall(80, 480, 1, 4),
            //
            new Wall(160, 480, 1, 4),
            new Wall(180, 480, 5, 1),
            //
            new Wall(420, 500, 1, 3),
            new Wall(500, 60, 5, 1),
            new Wall(540, 180, 5, 1),
            new Wall(540, 500, 5, 1),
            new Wall(620, 260, 1, 9),
            new Wall(420, 260, 10, 1),
            new Wall(420, 40, 1, 8),
            new Wall(220, 200, 1, 8),
            new Wall(220, 360, 6, 1),
            new Wall(220, 40, 1, 3),
            new Wall(320, 100, 1, 8),
            //
            new Wall(720, 40, 1, 6),
            new Wall(720, 280, 1, 6),
            new Wall(720, 480, 1, 4)
        };
        WALL_NUMBER = 19;
        WALL_KIND = 2;
        return walls;
    }
    public static Wall[] getWallThree() {
        Wall[] walls = new Wall[]{
            new Wall(120, 80, 28, 1),
            new Wall(120, 500, 28, 1),
            new Wall(120, 120, 4, 1),
            new Wall(120, 260, 4, 1),
            new Wall(120, 300, 4, 1),
            new Wall(120, 460, 4, 1),
            new Wall(600, 120, 4, 1),
            new Wall(600, 260, 4, 1),
            new Wall(600, 300, 4, 1),
            new Wall(600, 460, 4, 1),
            new Wall(200, 120, 1, 8),
            new Wall(200, 300, 1, 9),
            new Wall(580, 120, 1, 8),
            new Wall(580, 300, 1, 9)
        };
        WALL_NUMBER = 14;
        WALL_KIND = 3;
        return walls;
    }
    public static Wall[] getWallFour() {
        Wall[] walls = new Wall[]{
            new Wall(80, 80, 6, 1),
            new Wall(80, 120, 4, 1),
            new Wall(140, 140, 1, 20),
            new Wall(180, 100, 1, 20),
            new Wall(140, 540, 26, 1),
            new Wall(180, 500, 22, 1),
            new Wall(600, 80, 6, 1),
            new Wall(640, 120, 4, 1),
            new Wall(640, 140, 1, 20),
            new Wall(600, 100, 1, 20),
            new Wall(300, 120, 1, 5),
            new Wall(300, 280, 1, 7),
            new Wall(500, 120, 1, 5),
            new Wall(500, 280, 1, 7)
        };
        WALL_NUMBER = 14;
        WALL_KIND = 4;
        return walls;
    }
}