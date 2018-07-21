package game;

public class Wall {
    public static int WALL_NUMBER = 4;
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
}