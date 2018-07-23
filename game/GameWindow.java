package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class GameWindow extends JPanel {
    static final long serialVersionUID = 1;
    Player p1;
    Player p2;
    Point[] points;
    public static Wall[] walls;
    public static SnakeCave[] snakeCaves;
    int numberOfPoint;
    //counter
    int move[];
    //keyboard valid[id]
    static boolean valid[];
    //picture
    Image map;
    Image wall;
    Image cave;
    Image hole;
    Image resume;
    Image exit;
    Image apple;
    //button
    //JButton resume;
    GameWindow(Player player, Player player2) throws IOException{
        setPreferredSize(new Dimension(1000, 600));
        p1 = player;
        p2 = player2;
        numberOfPoint = 0;
        points = null;
        move = new int[2];
        valid = new boolean[2];
        Point.eggLeft = 2;
        //put wall
        putWall(Wall.getWalls());
        //System.out.println(Wall.WALL_KIND);
        //System.out.println(Wall.WALL_NUMBER);
        //put caves
        putCave(SnakeCave.getSnakeCaves());
        //System.out.println(SnakeCave.CAVE_KIND);
        //System.out.println(SnakeCave.CAVE_NUMBER);
        //put egg
        putPoint(new Point());
        putPoint(new Point());
        try {
            map = ImageIO.read(new File(".\\source\\picture\\map.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
        try {
            wall = ImageIO.read(new File(".\\source\\picture\\wall.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
        try {
            cave = ImageIO.read(new File(".\\source\\picture\\cave.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
        try {
            hole = ImageIO.read(new File(".\\source\\picture\\hole.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
        try {
            resume = ImageIO.read(new File(".\\source\\picture\\resume.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
        try {
            exit = ImageIO.read(new File(".\\source\\picture\\exit.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
        try {
            apple = ImageIO.read(new File(".\\source\\picture\\apple.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
        /*resume = new JButton();
        resume.setLayout(null);
        resume.setBounds(200, 200, 400, 250);
        resume.setIcon(new ImageIcon(ImageIO.read(new File(".\\source\\picture\\button.png"))));
        resume.setVisible(false);*/
    }
    public void moveSnake(Snake s, int id) throws NotEnoughSnakeException, 
                                    ArrayIndexOutOfBoundsException{
        if(s.wait==0) {//wait for two secs
            //move snake
            move[id]++;
            for(int i=0; i<s.length; i++) {
                switch(s.bodies[i].dir) {
                    case 0: //right
                        s.bodies[i].x++;
                        break;
                    case 1: //left
                        s.bodies[i].x--;
                        break;
                    case 2: //up
                        s.bodies[i].y--;
                        break;
                    case 3: //down
                        s.bodies[i].y++;
                        break;
                }
            }
            
            if(move[id]==20) {
                for(int i=s.length-1; i>=1; i--) {
                    s.bodies[i].dir = s.bodies[i-1].dir;
                    s.bodies[i].show = s.bodies[i-1].show;
                    if(s.bodies[i].x>=780)
                        s.bodies[i].x = s.bodies[i].x%780-20;
                    if(s.bodies[i].x<=-0)
                        s.bodies[i].x = s.bodies[i].x+800;
                    if(s.bodies[i].y>=580)
                        s.bodies[i].y = s.bodies[i].y%580-20;
                    if(s.bodies[i].y<=0)
                        s.bodies[i].y = s.bodies[i].y+600;
                }
                if(s.bodies[0].x>=780)
                    s.bodies[0].x = s.bodies[0].x%780-20;
                if(s.bodies[0].x<=0)
                    s.bodies[0].x = s.bodies[0].x+800;
                if(s.bodies[0].y>=580)
                    s.bodies[0].y = s.bodies[0].y%580-20;
                if(s.bodies[0].y<=0)
                    s.bodies[0].y = s.bodies[0].y+600;
                s.bodies[0].dir = s.dir;
                move[id] = 0;
                valid[id] = true;
            //System.out.println(s.bodies[0].x+", "+s.bodies[0].y);
            }
            /*if(!GameKeyListener.doo.isActive()) {
                System.out.println("hi");
                GameKeyListener.doo.stop();
                GameKeyListener.doo.close();
            }
            if(!GameKeyListener.re.isActive()) {
                GameKeyListener.re.stop();
                GameKeyListener.re.close();
            }
            if(!GameKeyListener.mi.isActive()) {
                GameKeyListener.mi.stop();
                GameKeyListener.mi.close();
            }
            if(!GameKeyListener.fa.isActive()) {
                GameKeyListener.fa.stop();
                GameKeyListener.fa.close();
            }*/
        }
        else {
            move[id] = 1;
            s.wait--;
        }
        
        // get point
        if(getPoint(s)) {
            s.getPoint();
            switch(id) {
                case 0:
                    p1.getPoint();
                    break;
                case 1:
                    p2.getPoint();
                    break;
            }
        }
        //hit wall
        if(isHit(s)) {
            switch(id) {
                case 0:
                    p1.numberOfSnack--;
                    break;
                case 1:
                    p2.numberOfSnack--;
                    break;
            }
            int choiceCave = (int) (SnakeCave.CAVE_NUMBER * Math.random());
            //System.out.println(choiceCave);
            try {
                switch(id) {
                    case 0:
                        p1.snakes[p1.numberOfSnack-1] = new Snake(GameWindow.snakeCaves[choiceCave].x, GameWindow.snakeCaves[choiceCave].y);
                        break;
                    case 1:
                        p2.snakes[p2.numberOfSnack-1] = new Snake(GameWindow.snakeCaves[choiceCave].x, GameWindow.snakeCaves[choiceCave].y);
                        break;
                }
                //System.out.println(GameWindow.snakeCaves[choiceCave].x+", "+ GameWindow.snakeCaves[choiceCave].y);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw e;
            }
            //System.out.println("hit function");
        }
        //head go in cave
        if(isCave(s) && s.wait==0) {
            //System.out.println("head go in cave");
            s.bodies[0].show=false;
            s.inCave = 100;
            move[id] = 0;
        }
        //tail go in cave
        else if(!s.isInCave && !s.bodies[s.length-1].show && s.wait==0  && !s.bodies[0].show) {
            s.isInCave = true;
            s.inCave = 200;
            //System.out.println("tail go in cave");
        }
        //count two secs
        else if(s.inCave!=0 && s.wait==0 && s.isInCave && !s.bodies[0].show) {
            s.inCave--;
            //System.out.println(move);
            //System.out.println("count two secs");
        }
        //head out cave
        else if(s.inCave==0 && !s.bodies[0].show && s.wait==0) {
            //System.out.println("head out cave");
            s.isInCave = false;
            s.bodies[0].show=true;
            int choiceCave = (int) (SnakeCave.CAVE_NUMBER * Math.random());
            s.setPosAndDir(snakeCaves[choiceCave].x, snakeCaves[choiceCave].y);
            move[id] = 1;
            //System.out.println(move);
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //background
        g.drawImage(map, 0, 0, map.getWidth(null), map.getHeight(null), null);
        g.setColor(new Color(0f, 0f, 0f, 0.5f));
        g.fillRect(0, 0, 800, 600);
        //paint wall
        for(int i=0; i<Wall.WALL_NUMBER; i++) {
            try {
                for(int row=0; row<walls[i].height; row++) {
                    for(int col=0; col<walls[i].width; col++) {
                        g.drawImage(wall, walls[i].x+20*col, walls[i].y+20*row, 20, 20, null);
                    }
                }
            } catch (NullPointerException e) {

            }
        }
        //paint point
        if(points!=null) {
            for(int i=0; i<numberOfPoint; i++) {
                g.setColor(Point.color);
                try{
                    //g.fillArc(points[i].x, points[i].y, 20, 20, 0, 360);
                    g.drawImage(apple, points[i].x, points[i].y-4, 20, 24, null);
                }catch(NullPointerException e) {

                }
            }
        }
        //paint cave
        /*if(snakeCaves!=null) {
            for(int i=0; i<SnakeCave.CAVE_NUMBER; i++) {
                try{
                    g.drawImage(cave, snakeCaves[i].x-20, snakeCaves[i].y-60, 60, 80, null);
                }catch(NullPointerException e) {

                }
            }
        }*/
        if(snakeCaves!=null) {
            for(int i=0; i<SnakeCave.CAVE_NUMBER; i++) {
                try{
                    g.drawImage(hole, snakeCaves[i].x, snakeCaves[i].y, 20, 20, null);
                }catch(NullPointerException e) {

                }
            }
        }
        //paint p1's snake
        try {
            //System.out.println(p1.getSnake().wait);
            if(p1.getSnake().wait==0) {
                for(int i=0; i<p1.getSnake().length; i++) {
                    //g.setColor(p1.getSnake().color);
                    g.setColor(Color.ORANGE);
                    if(p1.getSnake().bodies[i].x!=-1 && p1.getSnake().bodies[i].x != -1 
                        && p1.getSnake().bodies[i].show) {
                        g.fillArc(p1.getSnake().bodies[i].x, p1.getSnake().bodies[i].y, 20, 20, 0, 360);
                        if(p1.getSnake().bodies[i].x>=780
                            && p1.getSnake().bodies[i].show) {
                            g.fillArc((p1.getSnake().bodies[i].x%780)-20, p1.getSnake().bodies[i].y, 20, 20, 0, 360);
                        }
                        if(p1.getSnake().bodies[i].x<=0
                            && p1.getSnake().bodies[i].show) {
                            g.fillArc(p1.getSnake().bodies[i].x+800, p1.getSnake().bodies[i].y, 20, 20, 0, 360);
                        }
                        if(p1.getSnake().bodies[i].y>=580
                            && p1.getSnake().bodies[i].show) {
                            g.fillArc(p1.getSnake().bodies[i].x, p1.getSnake().bodies[i].y%580-20, 20, 20, 0, 360);
                        }
                        if(p1.getSnake().bodies[i].y<=0
                            && p1.getSnake().bodies[i].show) {
                            g.fillArc(p1.getSnake().bodies[i].y+600, p1.getSnake().bodies[i].y, 20, 20, 0, 360);
                        }
                    }
                }
            }
        } catch (Exception e) {
            //System.out.println(p1.numberOfSnack);
            //e.printStackTrace();
        }
        //paint p2's snake
        try {
            //System.out.println(p2.getSnake().wait);
            if(p2.getSnake().wait==0) {
                for(int i=0; i<p2.getSnake().length; i++) {
                    //g.setColor(p2.getSnake().color);
                    g.setColor(new Color(0, 153, 0));
                    if(p2.getSnake().bodies[i].x!=-1 && p2.getSnake().bodies[i].x != -1 
                        && p2.getSnake().bodies[i].show) {
                        g.fillArc(p2.getSnake().bodies[i].x, p2.getSnake().bodies[i].y, 20, 20, 0, 360);
                        if(p2.getSnake().bodies[i].x>=780
                            && p2.getSnake().bodies[i].show) {
                            g.fillArc((p2.getSnake().bodies[i].x%780)-20, p2.getSnake().bodies[i].y, 20, 20, 0, 360);
                        }
                        if(p2.getSnake().bodies[i].x<=0
                            && p2.getSnake().bodies[i].show) {
                            g.fillArc(p2.getSnake().bodies[i].x+800, p2.getSnake().bodies[i].y, 20, 20, 0, 360);
                        }
                        if(p2.getSnake().bodies[i].y>=580
                            && p2.getSnake().bodies[i].show) {
                            g.fillArc(p2.getSnake().bodies[i].x, p2.getSnake().bodies[i].y%580-20, 20, 20, 0, 360);
                        }
                        if(p2.getSnake().bodies[i].y<=0
                            && p2.getSnake().bodies[i].show) {
                            g.fillArc(p2.getSnake().bodies[i].y+600, p2.getSnake().bodies[i].y, 20, 20, 0, 360);
                        }
                    }
                }
            }
        } catch (Exception e) {
            //System.out.println(p2.numberOfSnack);
            //e.printStackTrace();
        }
        //paint point board
        g.setColor(new Color(0, 183, 0));
        g.fillRect(800, 0, 200, 600);
        //paint p1's score
        g.drawImage(apple, 940, 20, 20, 24, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString(""+p1.numberOfPoint, 970, 40);
        //paint p2's score
        g.drawImage(apple, 940, 552, 20, 24, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString(""+p2.numberOfPoint, 970, 570);
        //paint number of p1's snake
        g.setColor(Color.BLACK);
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString("Life: "+p1.numberOfSnack, 800, 40);
        //paint number of p2's snake
        g.setColor(Color.BLACK);
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString("Life: "+p2.numberOfSnack, 800, 570);
        //paint p1's hint of in cave
        try {
            if(p1.getSnake().isInCave) {
                g.setColor(new Color(0f, 0f, 0f, 0.5f));
                g.fillRoundRect(820, 100, 160, 100, 25, 25);
            }
        } catch (Exception e) {

        }
        //paint p2's hint of in cave
        try {
            if(p2.getSnake().isInCave) {
                g.setColor(new Color(0f, 0f, 0f, 0.5f));
                g.fillRoundRect(820, 400, 160, 100, 25, 25);
            }
        } catch (Exception e) {

        }
        g.setColor(Color.BLACK);
        g.fillRect(800, 295, 200, 10);
        //paint pause page
        if(Main.PAUSE) {
            g.setColor(new Color(0f, 0f, 0f, 0.5f));
            g.fillRect(0, 0, 800, 600);
            g.setColor(Color.WHITE);
            g.fillRoundRect(350, 100, 100, 50, 25, 25);
            g.drawImage(resume, 200, 30, 400, 250, null);
            g.drawImage(exit, 200, 320, 400, 250, null);
            //resume.setVisible(true);
        }
        
    }
    //
    public void putPoint(Point point) {
        if(points==null) {
            points = new Point[100];
        }
        points[numberOfPoint] = point;
        numberOfPoint++;
        //System.out.println(numberOfPoint);
    }
    public void putWall(Wall[] walls) {
        GameWindow.walls = walls;
    }
    public void putCave(SnakeCave[] cave) {
        GameWindow.snakeCaves = cave;
    }
    public boolean isHit(Snake s) throws NotEnoughSnakeException{
        for(int i=0; i<Wall.WALL_NUMBER; i++) { //hit wall
            for(int row=0; row<walls[i].height; row++) {
                for(int col=0; col<walls[i].width; col++) {
                    if(s.bodies[0].x==walls[i].x+20*col 
                        && s.bodies[0].y==walls[i].y+20*row
                        && s.bodies[0].show)
                        return true;
                }
            }
        }
        for(int i=1; i<s.length; i++) {
            if(s.bodies[0].x==s.bodies[i].x
                && s.bodies[0].y==s.bodies[i].y
                && s.bodies[0].show) {
                    //System.out.println(i);
                    //System.out.println(s.dir);
                    return true;
                }
        }
        return false;
    }
    public boolean isCave(Snake s) throws NotEnoughSnakeException{
        for(int i=0; i<SnakeCave.CAVE_NUMBER; i++) { 
            if(s.bodies[0].x==snakeCaves[i].x && s.bodies[0].y==snakeCaves[i].y
                && s.bodies[0].show) {
                    return true;
                }
        }
        return false;
    }
    public boolean getPoint(Snake s) throws NotEnoughSnakeException {
        if(points!=null) {
            for(int i=0; i<numberOfPoint; i++) {
                try{
                    if(s.bodies[0].x==points[i].x && s.bodies[0].y==points[i].y
                        && s.bodies[0].show) {
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