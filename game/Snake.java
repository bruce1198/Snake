package game;

import java.awt.*;

public class Snake {
    Body[] bodies = new Body[100];
    int length;
    int dir;
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
        wait = 200;
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
        dir = initDir;
        switch(initDir) {
            case 0: //right
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(1-20*i + init_x, init_y, 0);
                }
                break;
            case 1: //left
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(-1+20*i + init_x, init_y, 1);
                }
                break;
            case 2: // up
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(init_x, -1+20*i + init_y, 2);
                }
                break;
            case 3: // down
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(init_x, 1-20*i + init_y, 3);
                }
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
        dir = initDir;
        switch(initDir) {
            case 0: //right
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(20 + init_x - 20*i, init_y, 0);
                }
                break;
            case 1: //left
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(-20 + 20*i + init_x, init_y, 1);
                }
                break;
            case 2: // up
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(init_x, -20+20*i + init_y, 2);
                }
                break;
            case 3: // down
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(init_x, 20-20*i + init_y, 3);
                }
                break;
        }
        //System.out.println(length);
    }
    public void getPoint() {
        //System.out.println("Get");
        switch(bodies[length-1].dir) {
            case 0:
                bodies[length] = new Body(bodies[length-1].x-20, bodies[length-1].y, bodies[length-1].dir);
                break;
            case 1:
                bodies[length] = new Body(bodies[length-1].x+20, bodies[length-1].y, bodies[length-1].dir);  
                break;  
            case 2:
                bodies[length] = new Body(bodies[length-1].x, bodies[length-1].y+20, bodies[length-1].dir);
                break;
            case 3:
                bodies[length] = new Body(bodies[length-1].x, bodies[length-1].y-20, bodies[length-1].dir);
                break;
        }
        length++;
        Point.eggLeft--;
    }
    public void setPosAndDir(int x, int y) {
        int initDir = (int)(3*Math.random());
        int init_x = x;
        int init_y = y;
        dir = initDir;
        switch(initDir) {
            case 0: //right
                for(int i=0; i<length; i++) {
                    bodies[i].x = 1 - 20*i + init_x;
                    bodies[i].y = init_y;
                    bodies[i].dir = 0;
                }
                break;
            case 1: //left
                for(int i=0; i<length; i++) {
                    bodies[i].x = -1 + 20*i + init_x;
                    bodies[i].y = init_y;
                    bodies[i].dir = 1;
                }
                break;
            case 2: // up
                for(int i=0; i<length; i++) {
                    bodies[i].x = init_x;
                    bodies[i].y = -1+20*i + init_y;
                    bodies[i].dir = 2;
                }
                break;
            case 3: // down
                for(int i=0; i<length; i++) {
                    bodies[i].x = init_x;
                    bodies[i].y = 1-20*i + init_y;
                    bodies[i].dir = 3;
                }
                break;
        }
    }
}