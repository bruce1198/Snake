package snake.game;

import java.awt.*;
import java.io.Serializable;

import snake.util.Setting;

public class Snake implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Body[] bodies = new Body[100];
    public int length;
    public int dir;
    public Color color;
    public int wait;
    public int inCave;//counter
    public boolean isInCave;
    public static int speed = 100;
    //first snake
    public Snake(int x, int y, int dir) {
        int init_x = x;
        int init_y = y;
        int init_color = (int)(4*Math.random());
        this.length = 2;
        this.dir = dir;
        wait=0;
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
        switch(dir) {
            case 0: //right
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(-Setting.unit*i + init_x, init_y, 0);
                }
                break;
            case 1: //left
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(Setting.unit*i + init_x, init_y, 1);
                }
                break;
            case 2: // up
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(init_x, Setting.unit*i + init_y, 2);
                }
                break;
            case 3: // down
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(init_x, -Setting.unit*i + init_y, 3);
                }
                break;
        }
    }
    //snake born from cave
    public Snake(int x, int y) {
        int initDir = (int)(3*Math.random());
        int init_x = x;
        int init_y = y;
        int init_color = (int)(4*Math.random());
        this.length = 2;
        wait = 2*Snake.speed;
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
        //System.out.println(dir);
        switch(initDir) {
            case 0: //right
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(Setting.unit/20-Setting.unit*i + init_x, init_y, 0);
                }
                break;
            case 1: //left
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(-Setting.unit/20+Setting.unit*i + init_x, init_y, 1);
                }
                break;
            case 2: // up
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(init_x, -Setting.unit/20+Setting.unit*i + init_y, 2);
                }
                break;
            case 3: // down
                for(int i=0; i<length; i++) {
                    bodies[i] = new Body(init_x, Setting.unit/20-Setting.unit*i + init_y, 3);
                }
                break;
        }
    }
    
    public void setPosAndDir(int x, int y) {
        int initDir = (int)(3*Math.random());
        int init_x = x;
        int init_y = y;
        dir = initDir;
        switch(initDir) {
            case 0: //right
                for(int i=0; i<length; i++) {
                    bodies[i].x = Setting.unit/20-Setting.unit*i + init_x;
                    bodies[i].y = init_y;
                    bodies[i].dir = 0;
                }
                break;
            case 1: //left
                for(int i=0; i<length; i++) {
                    bodies[i].x = -Setting.unit/20+Setting.unit*i + init_x;
                    bodies[i].y = init_y;
                    bodies[i].dir = 1;
                }
                break;
            case 2: // up
                for(int i=0; i<length; i++) {
                    bodies[i].x = init_x;
                    bodies[i].y = -Setting.unit/20+Setting.unit*i + init_y;
                    bodies[i].dir = 2;
                }
                break;
            case 3: // down
                for(int i=0; i<length; i++) {
                    bodies[i].x = init_x;
                    bodies[i].y = Setting.unit/20-Setting.unit*i + init_y;
                    bodies[i].dir = 3;
                }
                break;
        }
        //System.out.println(bodies[0].x+", "+ bodies[0].y);

    }
    //---------------------------------------------------------------------
    public void getPoint() {
        //System.out.println("Get");
        switch(bodies[length-1].dir) {
            case 0:
                bodies[length] = new Body(bodies[length-1].x-Setting.unit, bodies[length-1].y, bodies[length-1].dir);
                break;
            case 1:
                bodies[length] = new Body(bodies[length-1].x+Setting.unit, bodies[length-1].y, bodies[length-1].dir);  
                break;  
            case 2:
                bodies[length] = new Body(bodies[length-1].x, bodies[length-1].y+Setting.unit, bodies[length-1].dir);
                break;
            case 3:
                bodies[length] = new Body(bodies[length-1].x, bodies[length-1].y-Setting.unit, bodies[length-1].dir);
                break;
        }
        length++;
        Point.eggLeft--;
    }
    //snake select cave to go out
    
}