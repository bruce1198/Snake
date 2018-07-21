package game;

import java.awt.*;

public class Snake {
    boolean LEFT;
    boolean RIGHT;
    boolean UP;
    boolean DOWN;
    Body[] bodies = new Body[100];
    int length;
    Color color;
    int wait;
    int inCave;//counter
    boolean isInCave;
    public static int speed = 100;
    Snake(int x, int y) {
        int initDir = (int)(3*Math.random());
        int init_x = x;
        int init_y = y;
        int init_color = (int)(4*Math.random());
        this.length = 2;
        wait = 10;
        isInCave = false;
        switch(init_color) {
            case 0:
                color = Color.RED;
                break;
            case 1:
                color = Color.BLUE;
                break;
            case 2:
                color = Color.GREEN;
                break;
            case 3:
                color = Color.ORANGE;
                break;
        }
        switch(initDir) {
            case 0: //right
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(20-20*i + init_x, init_y);
                }
                this.LEFT = false;
                this.RIGHT = true;
                this.UP = false;
                this.DOWN = false;
                break;
            case 1: //left
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(-20+20*i + init_x, init_y);
                }
                this.LEFT = true;
                this.RIGHT = false;
                this.UP = false;
                this.DOWN = false;
                break;
            case 2: // up
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(init_x, -20+20*i + init_y);
                }
                this.LEFT = false;
                this.RIGHT = false;
                this.UP = true;
                this.DOWN = false;
                break;
            case 3: // down
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(init_x, 20-20*i + init_y);
                }
                this.LEFT = false;
                this.RIGHT = false;
                this.UP = false;
                this.DOWN = true;
                break;
        }
    }
    Snake() {
        int initDir = (int)(3*Math.random());
        int init_x = 100 + 20 * (int)(30*Math.random());
        int init_y = 100 + 20 * (int)(20*Math.random());
        int init_color = (int)(4*Math.random());
        this.length = 2;
        wait = 0;
        switch(init_color) {
            case 0:
                color = Color.RED;
                break;
            case 1:
                color = Color.BLUE;
                break;
            case 2:
                color = Color.GREEN;
                break;
            case 3:
                color = Color.ORANGE;
                break;
        }
        switch(initDir) {
            case 0: //right
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(20 + init_x - 20*i, init_y);
                }
                this.LEFT = false;
                this.RIGHT = true;
                this.UP = false;
                this.DOWN = false;
                break;
            case 1: //left
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(-20 + 20*i + init_x, init_y);
                }
                this.LEFT = true;
                this.RIGHT = false;
                this.UP = false;
                this.DOWN = false;
                break;
            case 2: // up
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(init_x, -20+20*i + init_y);
                }
                this.LEFT = false;
                this.RIGHT = false;
                this.UP = true;
                this.DOWN = false;
                break;
            case 3: // down
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(init_x, 20-20*i + init_y);
                }
                this.LEFT = false;
                this.RIGHT = false;
                this.UP = false;
                this.DOWN = true;
                break;
        }
    }
    public void getPoint() {
        bodies[length] = new Body(-1, -1);
        length++;
        Point.eggLeft--;
    }
    public void setPosAndDir(int x, int y) {
        int initDir = (int)(3*Math.random());
        int init_x = x;
        int init_y = y;
        switch(initDir) {
            case 0: //right
                for(int i=0; i<length; i++) {
                    bodies[i].x = 20 - 20*i + init_x;
                    bodies[i].y = init_y;
                }
                this.LEFT = false;
                this.RIGHT = true;
                this.UP = false;
                this.DOWN = false;
                break;
            case 1: //left
                for(int i=0; i<length; i++) {
                    bodies[i].x = -20 + 20*i + init_x;
                    bodies[i].y = init_y;
                }
                this.LEFT = true;
                this.RIGHT = false;
                this.UP = false;
                this.DOWN = false;
                break;
            case 2: // up
                for(int i=0; i<length; i++) {
                    bodies[i].x = init_x;
                    bodies[i].y = -20+20*i + init_y;
                }
                this.LEFT = false;
                this.RIGHT = false;
                this.UP = true;
                this.DOWN = false;
                break;
            case 3: // down
                for(int i=0; i<length; i++) {
                    bodies[i].x = init_x;
                    bodies[i].y = 20-20*i + init_y;
                }
                this.LEFT = false;
                this.RIGHT = false;
                this.UP = false;
                this.DOWN = true;
                break;
        }
    }
}