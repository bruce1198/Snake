package game;

public class Body {
    int x;
    int y;
    int dir;
    boolean show;
    Body(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        show = true;
    }
    Body(int x, int y, boolean show) {
        this.x = x;
        this.y = y;
        show = false;
    }
}