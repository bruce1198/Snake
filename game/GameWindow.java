package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class GameWindow extends JPanel {
    static final long serialVersionUID = 1;
    Player p1;
    Point[] points;
    Wall[] walls;
    SnakeCave[] snakeCaves;
    int numberOfPoint;
    int numberOfCave;
    int move;
    static boolean valid;
    GameWindow(Player player) {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        p1 = player;
        numberOfPoint = 0;
        numberOfCave = 0;
        points = null;
        move = 0;
        valid = true;
    }
    public void moveSnake() throws NotEnoughSnakeException, 
                                    ArrayIndexOutOfBoundsException{
        if(p1.getSnake().wait==0) {//wait for two secs
            //move snake
            move++;
            for(int i=0; i<p1.getSnake().length; i++) {
                switch(p1.getSnake().bodies[i].dir) {
                    case 0: //right
                        p1.getSnake().bodies[i].x++;
                        break;
                    case 1: //left
                        p1.getSnake().bodies[i].x--;
                        break;
                    case 2: //up
                        p1.getSnake().bodies[i].y--;
                        break;
                    case 3: //down
                        p1.getSnake().bodies[i].y++;
                        break;
                }
            }
            
            if(move==20) {
                for(int i=p1.getSnake().length-1; i>=1; i--) {
                    p1.getSnake().bodies[i].dir = p1.getSnake().bodies[i-1].dir;
                    p1.getSnake().bodies[i].show = p1.getSnake().bodies[i-1].show;
                }
                p1.getSnake().bodies[0].dir = p1.getSnake().dir;
                move = 0;
                valid = true;
            }
        }
        else {
            move = 1;
            p1.getSnake().wait--;
        }
        
        // get point
        if(getPoint()) {
            p1.getSnake().getPoint();
            p1.getPoint();
        }
        //hit wall
        if(isHit()) {
            p1.numberOfSnack--;
            int choiceCave = (int) (numberOfCave * Math.random());
            try {
                p1.snakes[p1.numberOfSnack-1] = new Snake(snakeCaves[choiceCave].x, snakeCaves[choiceCave].y);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw e;
            }
            //System.out.println("hit function");
        }
        //head go in cave
        if(isCave() && p1.getSnake().wait==0) {
            //System.out.println("head go in cave");
            p1.getSnake().bodies[0].show=false;
            p1.getSnake().inCave = 100;
            move = 0;
        }
        //tail go in cave
        else if(!p1.getSnake().isInCave && !p1.getSnake().bodies[p1.getSnake().length-1].show && p1.getSnake().wait==0  && !p1.getSnake().bodies[0].show) {
            p1.getSnake().isInCave = true;
            p1.getSnake().inCave = 200;
            //System.out.println("tail go in cave");
        }
        //count two secs
        else if(p1.getSnake().inCave!=0 && p1.getSnake().wait==0 && p1.getSnake().isInCave && !p1.getSnake().bodies[0].show) {
            p1.getSnake().inCave--;
            //System.out.println(move);
            //System.out.println("count two secs");
        }
        //head out cave
        else if(p1.getSnake().inCave==0 && !p1.getSnake().bodies[0].show && p1.getSnake().wait==0) {
            //System.out.println("head out cave");
            p1.getSnake().isInCave = false;
            p1.getSnake().bodies[0].show=true;
            int choiceCave = (int) (numberOfCave * Math.random());
            p1.getSnake().setPosAndDir(snakeCaves[choiceCave].x, snakeCaves[choiceCave].y);
            move = 1;
            //System.out.println(move);
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //background
        try {
            Image map = ImageIO.read(new File(".\\source\\map.png"));
            g.drawImage(map, 0, 0, map.getWidth(null), map.getHeight(null), null);
        } catch (Exception e) {
            System.out.println("Error");
        }
        //score:
        g.setColor(Color.YELLOW);
        g.fillArc(740, 24, 20, 20, 0, 360);
        g.setColor(Color.BLACK);
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString(""+p1.numberOfPoint, 770, 40);
        //paint number of p1's snake
        g.setColor(Color.BLACK);
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString("Number: "+p1.numberOfSnack, 600, 40);
        //paint wall
        for(int i=0; i<Wall.WALL_NUMBER; i++) {
            g.setColor(Color.GREEN);
            try {
                g.fillRect(walls[i].x, walls[i].y, walls[i].width, walls[i].height);
            } catch (NullPointerException e) {

            }
        }
        //paint point
        if(points!=null) {
            for(int i=0; i<numberOfPoint; i++) {
                g.setColor(Point.color);
                try{
                    g.fillArc(points[i].x, points[i].y, 20, 20, 0, 360);
                }catch(NullPointerException e) {

                }
            }
        }
        //paint cave
        if(snakeCaves!=null) {
            for(int i=0; i<numberOfCave; i++) {
                g.setColor(SnakeCave.color);
                try{
                    g.fillArc(snakeCaves[i].x, snakeCaves[i].y, 20, 20, 0, 360);
                }catch(NullPointerException e) {

                }
            }
        }
        //paint p1's snake
        try {
            if(p1.getSnake().wait==0) {
                for(int i=0; i<p1.getSnake().length; i++) {
                    g.setColor(p1.getSnake().color);
                    if(p1.getSnake().bodies[i].x!=-1 && p1.getSnake().bodies[i].x != -1 
                        && p1.getSnake().bodies[i].show) {
                        g.fillArc(p1.getSnake().bodies[i].x, p1.getSnake().bodies[i].y, 20, 20, 0, 360);
                    }
                }
            }
        } catch (NotEnoughSnakeException e) {

        }
        //paint pause page
        if(Main.PAUSE) {
            g.setColor(new Color(0f, 0f, 0f, 0.5f));
            g.fillRect(0, 0, 800, 600);
            g.setColor(Color.WHITE);
            g.fillRoundRect(350, 100, 100, 50, 25, 25);
        }
        
    }
    //
    public void putPoint(Point point) {
        if(points==null) {
            points = new Point[100];
        }
        points[numberOfPoint] = point;
        numberOfPoint++;
    }
    public void putWall(Wall[] walls) {
        this.walls = walls;
    }
    public void putCave(SnakeCave cave) {
        if(snakeCaves==null) {
            snakeCaves = new SnakeCave[10];
        }
        snakeCaves[numberOfCave] = cave;
        numberOfCave++;
    }
    public boolean isHit() throws NotEnoughSnakeException{
        for(int i=0; i<Wall.WALL_NUMBER; i++) { //hit wall
            if(p1.getSnake().bodies[0].x>=walls[i].x && p1.getSnake().bodies[0].x<walls[i].x+walls[i].width 
                && p1.getSnake().bodies[0].y>=walls[i].y && p1.getSnake().bodies[0].y<walls[i].y+walls[i].height
                && p1.getSnake().bodies[0].show)
                return true;
        }
        for(int i=1; i<p1.getSnake().length; i++) {
            if(p1.getSnake().bodies[0].x==p1.getSnake().bodies[i].x
                && p1.getSnake().bodies[0].y==p1.getSnake().bodies[i].y
                && p1.getSnake().bodies[0].show)
                return true;
        }
        return false;
    }
    public boolean isCave() throws NotEnoughSnakeException{
        for(int i=0; i<numberOfCave; i++) { 
            if(p1.getSnake().bodies[0].x==snakeCaves[i].x && p1.getSnake().bodies[0].y==snakeCaves[i].y
                && p1.getSnake().bodies[0].show) {
                    return true;
                }
        }
        return false;
    }
    public boolean getPoint() throws NotEnoughSnakeException {
        if(points!=null) {
            for(int i=0; i<numberOfPoint; i++) {
                try{
                    if(p1.getSnake().bodies[0].x==points[i].x && p1.getSnake().bodies[0].y==points[i].y) {
                        points[i] = null;
                        //System.out.println(i);
                        return true;
                    }
                }catch(NullPointerException e) {

                }
            }
        }
        return false;
    }
}