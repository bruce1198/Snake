package snake.game;

import java.io.Serializable;

public class Body implements Serializable{
    public int x;
    public int y;
    public int dir;
    public boolean show;
    public Body(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        show = true;
    }
    public Body(int x, int y, boolean show) {
        this.x = x;
        this.y = y;
        show = false;
    }
}