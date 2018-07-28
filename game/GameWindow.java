package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameWindow extends JPanel implements Runnable,
												KeyListener{
    static final long serialVersionUID = 1;
    //Socket
    Socket client;
    Scanner input; //Input
    DataOutputStream dos; //Output
    int id; //id in the server
    boolean go;
    //music
    File doFile;
    Clip doo;
    AudioInputStream doIn;
    File reFile;
    Clip re;
    AudioInputStream reIn;
    File miFile;
    Clip mi;
    AudioInputStream miIn;
    File faFile;
    Clip fa;
    AudioInputStream faIn;
    File soFile;
    Clip so;
    AudioInputStream soIn;
    File laFile;
    Clip la;
    AudioInputStream laIn;
    File appleFile;
    Clip appleClip;
    AudioInputStream appleIn;
    //
    public static boolean PAUSE = false;
    public static boolean GAMEOVER = false;
    //size
    int WIDTH;
    int HEIGHT;
    Player p1;
    Player p2;
    //unit
    static final int unit = 20;
    static final int size = 2;
    Point[] points;
    public static Wall[] walls;
    public static SnakeCave[] snakeCaves;
    int numberOfPoint;
    //counter
    int move[];
    //keyboard valid[id]
    static boolean valid[];
    //mouse control
    static boolean exitClick;
    static boolean resumeClick;
    static boolean menuClick;
    //picture
    Image map;
    //orange snake
    Image oHeadR;
    Image oHeadL;
    Image oHeadU;
    Image oHeadD;
    Image cTailR;
    Image cTailL;
    Image cTailU;
    Image cTailD;
    //green snake
    Image gHeadR;
    Image gHeadL;
    Image gHeadU;
    Image gHeadD;
    Image gTailR;
    Image gTailL;
    Image gTailU;
    Image gTailD;
    //
    Image wall;
    Image cave;
    Image hole;
    Image apple;
    //inCave
    Image orangeInCave;
    Image greenInCave;
    //pause
    Image menu;
    Image resume;
    Image exit;
    Image exitClickIm;
    //game over
    Image orangeWin;
    Image greenWin;
    //board
    Image board;
    //cursor
    Image cursor;
    GameWindow() throws IOException{
        setPreferredSize(new Dimension(1000, 600));
        /*WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
        HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;*/
        WIDTH = 1000;
        HEIGHT = 600;
        //System.out.println(WIDTH +", "+HEIGHT);
        p1 = new Player(5);
        p2 = new Player(5, 2);
        numberOfPoint = 0;
        points = null;
        move = new int[2];
        //keyboard
        valid = new boolean[2];
        //mouse
        exitClick = false;
        resumeClick = false;
        menuClick = false;
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
        putPoint();
        putPoint();
        //sound
        	try {
            	doFile = new File(".\\source\\music\\do.wav");
            	reFile = new File(".\\source\\music\\re.wav");
            	miFile = new File(".\\source\\music\\mi.wav");
            	faFile = new File(".\\source\\music\\fa.wav");
            	soFile = new File(".\\source\\music\\so.wav");
            	laFile = new File(".\\source\\music\\la.wav");
            	appleFile = new File(".\\source\\music\\apple.wav");
        	}catch (Exception e) {
                System.out.println("Music Error");
            }
        //image
        try {
            map = ImageIO.read(new File(".\\source\\picture\\map.png"));
            wall = ImageIO.read(new File(".\\source\\picture\\wall.png"));
            cave = ImageIO.read(new File(".\\source\\picture\\cave.png"));
            hole = ImageIO.read(new File(".\\source\\picture\\hole.png"));
            apple = ImageIO.read(new File(".\\source\\picture\\apple.png"));
            //p1 snake
            oHeadR = ImageIO.read(new File(".\\source\\picture\\cHeadR.png"));
            oHeadL = ImageIO.read(new File(".\\source\\picture\\cHeadL.png"));
            oHeadU = ImageIO.read(new File(".\\source\\picture\\cHeadU.png"));
            oHeadD = ImageIO.read(new File(".\\source\\picture\\cHeadD.png"));
            cTailR = ImageIO.read(new File(".\\source\\picture\\cTailR.png"));
            cTailL = ImageIO.read(new File(".\\source\\picture\\cTailL.png"));
            cTailU = ImageIO.read(new File(".\\source\\picture\\cTailU.png"));
            cTailD = ImageIO.read(new File(".\\source\\picture\\cTailD.png"));
            //p2 snake
            gHeadR = ImageIO.read(new File(".\\source\\picture\\gHeadR.png"));
            gHeadL = ImageIO.read(new File(".\\source\\picture\\gHeadL.png"));
            gHeadU = ImageIO.read(new File(".\\source\\picture\\gHeadU.png"));
            gHeadD = ImageIO.read(new File(".\\source\\picture\\gHeadD.png"));
            gTailR = ImageIO.read(new File(".\\source\\picture\\gTailR.png"));
            gTailL = ImageIO.read(new File(".\\source\\picture\\gTailL.png"));
            gTailU = ImageIO.read(new File(".\\source\\picture\\gTailU.png"));
            gTailD = ImageIO.read(new File(".\\source\\picture\\gTailD.png"));
            //inCave
            orangeInCave = ImageIO.read(new File(".\\source\\picture\\orangeincave.png"));
            greenInCave = ImageIO.read(new File(".\\source\\picture\\greenincave.png"));
            //pause
            menu = ImageIO.read(new File(".\\source\\picture\\menu.png"));
            resume = ImageIO.read(new File(".\\source\\picture\\resume.png"));
            exit = ImageIO.read(new File(".\\source\\picture\\exit.png"));
            board = ImageIO.read(new File(".\\source\\picture\\board.png"));
            exitClickIm = ImageIO.read(new File(".\\source\\picture\\click.png"));
            //game over
            orangeWin = ImageIO.read(new File(".\\source\\picture\\orangewin.png"));
            greenWin = ImageIO.read(new File(".\\source\\picture\\greenwin.png"));
            //set cursor
            cursor = ImageIO.read(new File(".\\source\\picture\\cursor.png"));
        } catch (Exception e) {
            System.out.println("Picture Error");
        }
        //get connection
        try {
            client = new Socket("localhost", 9999);
            dos = new DataOutputStream(client.getOutputStream());
            input = new Scanner(client.getInputStream());
            id = Integer.parseInt(input.nextLine());
            go = false;
            System.out.println("connect!");
        }catch(Exception e) {
        	System.out.println("Cannot connect to the server!");
        }
        
    }
    public void moveSnake(Snake s, Snake other, int id) throws NotEnoughSnakeException, 
                                    ArrayIndexOutOfBoundsException{
    	
    	//System.out.println("move");
        if(s.wait==0) {//wait for two secs
            //move snake
            move[id]+=unit/20;
            for(int i=0; i<s.length; i++) {
                switch(s.bodies[i].dir) {
                    case 0: //right
                        s.bodies[i].x+=unit/20;
                        break;
                    case 1: //left
                        s.bodies[i].x-=unit/20;
                        break;
                    case 2: //up
                        s.bodies[i].y-=unit/20;
                        break;
                    case 3: //down
                        s.bodies[i].y+=unit/20;
                        break;
                }
            }
            
            if(move[id]==unit) {
                for(int i=s.length-1; i>=1; i--) {
                    s.bodies[i].dir = s.bodies[i-1].dir;
                    s.bodies[i].show = s.bodies[i-1].show;
                    if(s.bodies[i].x>WIDTH-200-unit)
                        s.bodies[i].x = s.bodies[i].x%(WIDTH-200-unit)-unit;
                    if(s.bodies[i].x<0)
                        s.bodies[i].x = s.bodies[i].x+WIDTH-200;
                    if(s.bodies[i].y>HEIGHT-unit)
                        s.bodies[i].y = s.bodies[i].y%(HEIGHT-unit)-unit;
                    if(s.bodies[i].y<0)
                        s.bodies[i].y = s.bodies[i].y+HEIGHT;
                }
                if(s.bodies[0].x>WIDTH-200-unit)
                    s.bodies[0].x = s.bodies[0].x%(WIDTH-200-unit)-unit;
                if(s.bodies[0].x<0)
                    s.bodies[0].x = s.bodies[0].x+WIDTH-200;
                if(s.bodies[0].y>HEIGHT-unit)
                    s.bodies[0].y = s.bodies[0].y%(HEIGHT-unit)-unit;
                if(s.bodies[0].y<0)
                    s.bodies[0].y = s.bodies[0].y+HEIGHT;
                s.bodies[0].dir = s.dir;
                move[id] = 0;
                valid[id] = true;
            }
        }
        else {
            move[id] = unit/20;
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
        //hit head
        if(isHitHead(s, other) && s.wait==0) {
        	p1.numberOfSnack--;
        	p2.numberOfSnack--;
        	int choiceCave = (int) (SnakeCave.CAVE_NUMBER * Math.random());
        	int choiceCave2;
        	while(true) {
            	choiceCave2 = (int) (SnakeCave.CAVE_NUMBER * Math.random());
            	if(choiceCave!=choiceCave2)
            		break;
        	}
            //System.out.println(choiceCave);
            try {
            	move[0] = unit/20;
                p1.snakes[p1.numberOfSnack-1] = new Snake(GameWindow.snakeCaves[choiceCave].x, GameWindow.snakeCaves[choiceCave].y);
                //System.out.println(p1.getSnake().bodies[0].x+", "+ p1.getSnake().bodies[0].y);
                //valid[0] = true;
                move[1] = unit/20;
                p2.snakes[p2.numberOfSnack-1] = new Snake(GameWindow.snakeCaves[choiceCave2].x, GameWindow.snakeCaves[choiceCave2].y);
                //valid[1] = true;
                //System.out.println(GameWindow.snakeCaves[choiceCave].x+", "+ GameWindow.snakeCaves[choiceCave].y);
            } catch (ArrayIndexOutOfBoundsException e) {
                GAMEOVER = true;
            }
        }
        //hit wall
        else if(isHit(s, other)) {
            switch(id) {
                case 0:
                    p1.numberOfSnack--;
                    //System.out.println(p1.numberOfSnack);
                    break;
                case 1:
                    p2.numberOfSnack--;
                    //System.out.println(p1.numberOfSnack);
                    break;
            }
            int choiceCave = (int) (SnakeCave.CAVE_NUMBER * Math.random());
            //System.out.println(choiceCave);
            try {
                switch(id) {
                    case 0:
                        move[0] = unit/20;
                        p1.snakes[p1.numberOfSnack-1] = new Snake(GameWindow.snakeCaves[choiceCave].x, GameWindow.snakeCaves[choiceCave].y);
                        //System.out.println(p1.getSnake().bodies[0].x+", "+ p1.getSnake().bodies[0].y);
                        //valid[0] = true;
                        break;
                    case 1:
                        move[1] = unit/20;
                        p2.snakes[p2.numberOfSnack-1] = new Snake(GameWindow.snakeCaves[choiceCave].x, GameWindow.snakeCaves[choiceCave].y);
                        //valid[1] = true;
                        break;
                }
                //System.out.println(GameWindow.snakeCaves[choiceCave].x+", "+ GameWindow.snakeCaves[choiceCave].y);
            } catch (ArrayIndexOutOfBoundsException e) {
                GAMEOVER = true;
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
            s.inCave = 2*Snake.speed;
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
            move[id] = unit/20;
            //System.out.println(move);
        }
        speedControl();
        pointControl();
    }
	//-----------------------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //----------------------background----------------------
        g.drawImage(map, 0, 0, WIDTH-200, HEIGHT, null);
        //---------------------paint wall---------------------
        for(int i=0; i<Wall.WALL_NUMBER; i++) {
            try {
                for(int row=0; row<walls[i].height; row++) {
                    for(int col=0; col<walls[i].width; col++) {
                        g.drawImage(wall, walls[i].x+unit*col, walls[i].y+unit*row, unit, unit, null);
                    }
                }
            } catch (NullPointerException e) {

            }
        }
        //----------------------------------paint apple------------------------
        if(points!=null) {
            for(int i=0; i<numberOfPoint; i++) {
                g.setColor(Point.color);
                try{
                    //g.fillArc(points[i].x, points[i].y, 20, 20, 0, 360);
                    g.drawImage(apple, points[i].x, points[i].y-8, unit, unit+8, null);
                }catch(NullPointerException e) {

                }
            }
        }
        if(snakeCaves!=null) {
            for(int i=0; i<SnakeCave.CAVE_NUMBER; i++) {
                try{
                    g.drawImage(hole, snakeCaves[i].x, snakeCaves[i].y, unit, unit, null);
                }catch(NullPointerException e) {

                }
            }
        }
        //--------------------------------paint p1's snake---------------------------------
        try {
            //System.out.println(p1.getSnake().wait);
            if(p1.getSnake().wait==0) {
                for(int i=1; i<p1.getSnake().length-1; i++) {
                    //g.setColor(p1.getSnake().color);
                    g.setColor(Color.ORANGE);
                    if(p1.getSnake().bodies[i].x!=-1 && p1.getSnake().bodies[i].x != -1 
                        && p1.getSnake().bodies[i].show) {
                    	//g.fillRect(p1.getSnake().bodies[i].x, p1.getSnake().bodies[i].y, unit, unit);
                        g.fillArc(p1.getSnake().bodies[i].x, p1.getSnake().bodies[i].y, unit, unit, 0, 360);
                        if(p1.getSnake().bodies[i].x>WIDTH-200-unit
                            && p1.getSnake().bodies[i].show) {
                        	//g.fillRect((p1.getSnake().bodies[i].x%(WIDTH-200-unit))-unit, p1.getSnake().bodies[i].y, unit, unit);
                            g.fillArc((p1.getSnake().bodies[i].x%(WIDTH-200-unit))-unit, p1.getSnake().bodies[i].y, unit, unit, 0, 360);
                        }
                        if(p1.getSnake().bodies[i].x<0
                            && p1.getSnake().bodies[i].show) {
                        	//g.fillRect(p1.getSnake().bodies[i].x+WIDTH-200, p1.getSnake().bodies[i].y, unit, unit);
                            g.fillArc(p1.getSnake().bodies[i].x+WIDTH-200, p1.getSnake().bodies[i].y, unit, unit, 0, 360);
                        }
                        if(p1.getSnake().bodies[i].y>HEIGHT-unit
                            && p1.getSnake().bodies[i].show) {
                        	//g.fillRect(p1.getSnake().bodies[i].x, p1.getSnake().bodies[i].y%(HEIGHT-unit)-unit, unit, unit);
                            g.fillArc(p1.getSnake().bodies[i].x, p1.getSnake().bodies[i].y%(HEIGHT-unit)-unit, unit, unit, 0, 360);
                        }
                        if(p1.getSnake().bodies[i].y<0
                            && p1.getSnake().bodies[i].show) {
                        	//g.fillRect(p1.getSnake().bodies[i].x, p1.getSnake().bodies[i].y+HEIGHT, unit, unit);
                            g.fillArc(p1.getSnake().bodies[i].x, p1.getSnake().bodies[i].y+HEIGHT, unit, unit, 0, 360);
                        }
                    }
                    //System.out.println(p1.getSnake().bodies[i].x+", "+p1.getSnake().bodies[i].y);
                }
                //for head
                Image head = null;
                switch(p1.getSnake().bodies[0].dir) {
                	case 0:
                		head = oHeadR;
                		break;
                	case 1:
                		head = oHeadL;
                		break;
                	case 2:
                		head = oHeadU;
                		break;
                	case 3:
                		head = oHeadD;
                		break;
                }
                if(p1.getSnake().bodies[0].show)
                	g.drawImage(head, p1.getSnake().bodies[0].x, p1.getSnake().bodies[0].y, unit, unit, null);
                	//g.fillArc(p1.getSnake().bodies[0].x, p1.getSnake().bodies[0].y, unit, unit, 0, 360);
                if(p1.getSnake().bodies[0].x>WIDTH-200-unit
                    && p1.getSnake().bodies[0].show) {
                	g.drawImage(head, (p1.getSnake().bodies[0].x%(WIDTH-200-unit))-unit, p1.getSnake().bodies[0].y, unit, unit, null);
                }
                if(p1.getSnake().bodies[0].x<0
                    && p1.getSnake().bodies[0].show) {
                	g.drawImage(head, p1.getSnake().bodies[0].x+WIDTH-200, p1.getSnake().bodies[0].y, unit, unit, null);
                }
                if(p1.getSnake().bodies[0].y>HEIGHT-unit
                    && p1.getSnake().bodies[0].show) {
                	g.drawImage(head, p1.getSnake().bodies[0].x, p1.getSnake().bodies[0].y%(HEIGHT-unit)-unit, unit, unit, null);
                }
                if(p1.getSnake().bodies[0].y<0
                    && p1.getSnake().bodies[0].show) {
                	g.drawImage(head, p1.getSnake().bodies[0].x, p1.getSnake().bodies[0].y+HEIGHT, unit, unit, null);
                }
                //for tail
                Image tail = null;
                switch(p1.getSnake().bodies[p1.getSnake().length-1].dir) {
                	case 0:
                		tail = cTailR;
                		break;
                	case 1:
                		tail = cTailL;
                		break;
                	case 2:
                		tail = cTailU;
                		break;
                	case 3:
                		tail = cTailD;
                		break;
                }
                if(p1.getSnake().bodies[p1.getSnake().length-1].show)
                	g.drawImage(tail, p1.getSnake().bodies[p1.getSnake().length-1].x, p1.getSnake().bodies[p1.getSnake().length-1].y, unit, unit, null);
                	//g.fillArc(p1.getSnake().bodies[0].x, p1.getSnake().bodies[0].y, unit, unit, 0, 360);
                if(p1.getSnake().bodies[p1.getSnake().length-1].x>WIDTH-200-unit
                    && p1.getSnake().bodies[p1.getSnake().length-1].show) {
                	g.drawImage(tail, (p1.getSnake().bodies[p1.getSnake().length-1].x%(WIDTH-200-unit))-unit, p1.getSnake().bodies[p1.getSnake().length-1].y, unit, unit, null);
                }
                if(p1.getSnake().bodies[p1.getSnake().length-1].x<0
                    && p1.getSnake().bodies[p1.getSnake().length-1].show) {
                	g.drawImage(tail, p1.getSnake().bodies[p1.getSnake().length-1].x+WIDTH-200, p1.getSnake().bodies[p1.getSnake().length-1].y, unit, unit, null);
                }
                if(p1.getSnake().bodies[p1.getSnake().length-1].y>HEIGHT-unit
                    && p1.getSnake().bodies[p1.getSnake().length-1].show) {
                	g.drawImage(tail, p1.getSnake().bodies[p1.getSnake().length-1].x, p1.getSnake().bodies[p1.getSnake().length-1].y%(HEIGHT-unit)-unit, unit, unit, null);
                }
                if(p1.getSnake().bodies[p1.getSnake().length-1].y<0
                    && p1.getSnake().bodies[p1.getSnake().length-1].show) {
                	g.drawImage(tail, p1.getSnake().bodies[p1.getSnake().length-1].x, p1.getSnake().bodies[p1.getSnake().length-1].y+HEIGHT, unit, unit, null);
                }
                //
            }
        } catch (Exception e) {
            //System.out.println(p1.numberOfSnack);
            //e.printStackTrace();
        }
        //---------------------paint p2's snake---------------------------------
        try {
            //System.out.println(p2.getSnake().wait);
            if(p2.getSnake().wait==0) {
                for(int i=1; i<p2.getSnake().length-1; i++) {
                    //g.setColor(p2.getSnake().color);
                    g.setColor(new Color(0, 209, 0));
                    if(p2.getSnake().bodies[i].x!=-1 && p2.getSnake().bodies[i].x != -1 
                        && p2.getSnake().bodies[i].show) {
                        g.fillArc(p2.getSnake().bodies[i].x, p2.getSnake().bodies[i].y, unit, unit, 0, 360);
                        if(p2.getSnake().bodies[i].x>WIDTH-200-unit
                            && p2.getSnake().bodies[i].show) {
                            g.fillArc((p2.getSnake().bodies[i].x%(WIDTH-200-unit))-unit, p2.getSnake().bodies[i].y, unit, unit, 0, 360);
                        }
                        if(p2.getSnake().bodies[i].x<0
                            && p2.getSnake().bodies[i].show) {
                            g.fillArc(p2.getSnake().bodies[i].x+WIDTH-200, p2.getSnake().bodies[i].y, unit, unit, 0, 360);
                        }
                        if(p2.getSnake().bodies[i].y>HEIGHT-unit
                            && p2.getSnake().bodies[i].show) {
                            g.fillArc(p2.getSnake().bodies[i].x, p2.getSnake().bodies[i].y%(HEIGHT-unit)-unit, unit, unit, 0, 360);
                        }
                        if(p2.getSnake().bodies[i].y<0
                            && p2.getSnake().bodies[i].show) {
                            g.fillArc(p2.getSnake().bodies[i].x, p2.getSnake().bodies[i].y+HEIGHT, unit, unit, 0, 360);
                        }
                    }
                }
              //for head
                Image head = null;
                switch(p2.getSnake().bodies[0].dir) {
                	case 0:
                		head = gHeadR;
                		break;
                	case 1:
                		head = gHeadL;
                		break;
                	case 2:
                		head = gHeadU;
                		break;
                	case 3:
                		head = gHeadD;
                		break;
                }
                if(p2.getSnake().bodies[0].show)
                	g.drawImage(head, p2.getSnake().bodies[0].x, p2.getSnake().bodies[0].y, unit, unit, null);
                	//g.fillArc(p2.getSnake().bodies[0].x, p2.getSnake().bodies[0].y, unit, unit, 0, 360);
                if(p2.getSnake().bodies[0].x>WIDTH-200-unit
                    && p2.getSnake().bodies[0].show) {
                	g.drawImage(head, (p2.getSnake().bodies[0].x%(WIDTH-200-unit))-unit, p2.getSnake().bodies[0].y, unit, unit, null);
                }
                if(p2.getSnake().bodies[0].x<0
                    && p2.getSnake().bodies[0].show) {
                	g.drawImage(head, p2.getSnake().bodies[0].x+WIDTH-200, p2.getSnake().bodies[0].y, unit, unit, null);
                }
                if(p2.getSnake().bodies[0].y>HEIGHT-unit
                    && p2.getSnake().bodies[0].show) {
                	g.drawImage(head, p2.getSnake().bodies[0].x, p2.getSnake().bodies[0].y%(HEIGHT-unit)-unit, unit, unit, null);
                }
                if(p2.getSnake().bodies[0].y<0
                    && p2.getSnake().bodies[0].show) {
                	g.drawImage(head, p2.getSnake().bodies[0].x, p2.getSnake().bodies[0].y+HEIGHT, unit, unit, null);
                }
                //for tail
                Image tail = null;
                switch(p2.getSnake().bodies[p2.getSnake().length-1].dir) {
                	case 0:
                		tail = gTailR;
                		break;
                	case 1:
                		tail = gTailL;
                		break;
                	case 2:
                		tail = gTailU;
                		break;
                	case 3:
                		tail = gTailD;
                		break;
                }
                if(p2.getSnake().bodies[p2.getSnake().length-1].show)
                	g.drawImage(tail, p2.getSnake().bodies[p2.getSnake().length-1].x, p2.getSnake().bodies[p2.getSnake().length-1].y, unit, unit, null);
                	//g.fillArc(p2.getSnake().bodies[0].x, p2.getSnake().bodies[0].y, unit, unit, 0, 360);
                if(p2.getSnake().bodies[p2.getSnake().length-1].x>WIDTH-200-unit
                    && p2.getSnake().bodies[p2.getSnake().length-1].show) {
                	g.drawImage(tail, (p2.getSnake().bodies[p2.getSnake().length-1].x%(WIDTH-200-unit))-unit, p2.getSnake().bodies[p2.getSnake().length-1].y, unit, unit, null);
                }
                if(p2.getSnake().bodies[p2.getSnake().length-1].x<0
                    && p2.getSnake().bodies[p2.getSnake().length-1].show) {
                	g.drawImage(tail, p2.getSnake().bodies[p2.getSnake().length-1].x+WIDTH-200, p2.getSnake().bodies[p2.getSnake().length-1].y, unit, unit, null);
                }
                if(p2.getSnake().bodies[p2.getSnake().length-1].y>HEIGHT-unit
                    && p2.getSnake().bodies[p2.getSnake().length-1].show) {
                	g.drawImage(tail, p2.getSnake().bodies[p2.getSnake().length-1].x, p2.getSnake().bodies[p2.getSnake().length-1].y%(HEIGHT-unit)-unit, unit, unit, null);
                }
                if(p2.getSnake().bodies[p2.getSnake().length-1].y<0
                    && p2.getSnake().bodies[p2.getSnake().length-1].show) {
                	g.drawImage(tail, p2.getSnake().bodies[p2.getSnake().length-1].x, p2.getSnake().bodies[p2.getSnake().length-1].y+HEIGHT, unit, unit, null);
                }
                //
            }
            
        } catch (Exception e) {
            //System.out.println(p2.numberOfSnack);
            //e.printStackTrace();
        }
        //---------------------paint point board----------------
        g.drawImage(board, WIDTH-200, 0, 200, HEIGHT, null);
        //-----------------------paint p1's lives---------------
        g.setColor(Color.BLACK);
        if(p1.numberOfSnack==1) {
        	g.setColor(Color.RED);
        }
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(""+p1.numberOfSnack, WIDTH-50, HEIGHT-220);
        //----------------------paint p1's score------------------
        g.setColor(Color.BLACK);
        if(p1.numberOfPoint>=25) {
        	g.setColor(Color.RED);
        }
        g.setFont(new Font("Arial", Font.BOLD, 40+p1.numberOfPoint));
        g.drawString(""+p1.numberOfPoint, WIDTH-100, HEIGHT-170);
        //---------------------paint p2's lives------------------------
        g.setColor(Color.BLACK);
        if(p2.numberOfSnack==1) {
        	g.setColor(Color.RED);
        }
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(""+p2.numberOfSnack, WIDTH-50, HEIGHT-80);
        //----------------------paint p2's score---------------------
        g.setColor(Color.BLACK);
        if(p2.numberOfPoint>=25) {
        	g.setColor(Color.RED);
        }
        g.setFont(new Font("Arial", Font.BOLD, 40+p2.numberOfPoint));
        g.drawString(""+p2.numberOfPoint, WIDTH-100, HEIGHT-30);
        //-------------------paint p1's hint of in cave---------------------
        try {
            if(p1.getSnake().isInCave) {
            	g.drawImage(orangeInCave, (WIDTH-200)/2-110, 0, 200, 100, null);
                g.setColor(new Color(0f, 0f, 0f, 0.5f));
                g.fillRoundRect((WIDTH-200)/2-100, 0, 200, 100, 25, 25);
            }
        } catch (Exception e) {

        }
        //--------------------paint p2's hint of in cave-----------------
        try {
            if(p2.getSnake().isInCave) {
            	g.drawImage(greenInCave, (WIDTH-200)/2-110, 500, 200, 100, null);
                g.setColor(new Color(0f, 0f, 0f, 0.5f));
                g.fillRoundRect((WIDTH-200)/2-100, 500, 200, 100, 25, 25);
            }
        } catch (Exception e) {

        }
        //-------------------------------Level-----------------------------
        g.setColor(new Color(0, 0, 0, 100));
		g.setFont(new Font("Console", Font.BOLD, 30));
        switch (Snake.speed) {
		case 120:
			g.drawString("EASY", 2, 25);
			break;
		case 200:
			g.drawString("NORMAL", 2, 25);
			break;
		case 300:
			g.drawString("HARD", 2, 25);
			break;
		}
        //-----------------------paint pause page----------------------
        if(PAUSE) {
            g.setColor(new Color(0f, 0f, 0f, 0.5f));
            g.fillRect(0, 0, WIDTH-200, HEIGHT);
            //resume
            if(resumeClick) {
            	//g.drawImage(exitClickIm, WIDTH/2-5*400/GameWindow.unit-100-2,  HEIGHT/2-HEIGHT/GameWindow.unit-10*250/GameWindow.unit-2, 10*400/GameWindow.unit+4, 10*250/GameWindow.unit+4, null);
            	g.drawImage(exitClickIm, WIDTH/2-5*400/GameWindow.unit-100-2, 55-2, 200+4, 125+4, null);
            }
            g.drawImage(resume, WIDTH/2-5*400/GameWindow.unit-100, 55, 200, 125, null);
            //menu
            if(menuClick) {
            	g.drawImage(exitClickIm, WIDTH/2-5*400/GameWindow.unit-100-2,  235-2, 10*400/GameWindow.unit+4, 10*250/GameWindow.unit+4, null);
            }
            g.drawImage(menu, WIDTH/2-5*400/GameWindow.unit-100, 235, 10*400/GameWindow.unit, 10*250/GameWindow.unit, null);
            //exit
            if(exitClick)
            	g.drawImage(exitClickIm, WIDTH/2-5*400/GameWindow.unit-100-2, 415-2, 10*400/GameWindow.unit+4, 10*250/GameWindow.unit+4, null);
            g.drawImage(exit, WIDTH/2-5*400/GameWindow.unit-100, 415, 10*400/GameWindow.unit, 10*250/GameWindow.unit, null);
            //resume.setVisible(true);
            //
        }
        //----------------------------------------Game over----------------------------------
        if(GAMEOVER) {
            g.setColor(new Color(0f, 0f, 0f, 0.5f));
            g.fillRect(0, 0, WIDTH-200, HEIGHT);
            if(p1.numberOfPoint>=30) 
            	g.drawImage(orangeWin, WIDTH/2-5*400/GameWindow.unit-200, 30, 400, 200, null);
            else if(p2.numberOfPoint>=30) 
            	g.drawImage(greenWin, WIDTH/2-5*400/GameWindow.unit-200, 30, 400, 200, null);
            else if(p1.numberOfSnack>p2.numberOfSnack)
            	g.drawImage(orangeWin, WIDTH/2-5*400/GameWindow.unit-200, 30, 400, 200, null);
            else if(p1.numberOfSnack<p2.numberOfSnack)
            	g.drawImage(greenWin, WIDTH/2-5*400/GameWindow.unit-200, 30, 400, 200, null);
            if(menuClick) {
            	g.drawImage(exitClickIm, WIDTH/2-5*400/GameWindow.unit-100-2,  235-2, 10*400/GameWindow.unit+4, 10*250/GameWindow.unit+4, null);
            }
            g.drawImage(menu, WIDTH/2-5*400/GameWindow.unit-100, 235, 10*400/GameWindow.unit, 10*250/GameWindow.unit, null);
            if(exitClick)
            	g.drawImage(exitClickIm, WIDTH/2-5*400/GameWindow.unit-100-2, 415-2, 10*400/GameWindow.unit+4, 10*250/GameWindow.unit+4, null);
            g.drawImage(exit, WIDTH/2-5*400/GameWindow.unit-100, 415, 10*400/GameWindow.unit, 10*250/GameWindow.unit, null);
        }
        //if(PAUSE || GAMEOVER) g.drawImage(cursor, GameMouseListener.MOUSE_X-6, GameMouseListener.MOUSE_Y-30, 50, 50, null);
        repaint();
        
    }
    //--------------------------------------------------------------
    public void putPoint() {
        if(points==null) {
            points = new Point[100];
        }
        int x, y;
        while(true) {
            x = GameWindow.unit * (int)(40*Math.random());
            y = GameWindow.unit * (int)(30*Math.random());
            if(isValid(x, y))
                break;
        }
        points[numberOfPoint] = new Point(x, y);
        numberOfPoint++;
        //System.out.println(numberOfPoint);
    }
    //if the position can set apple 
    private boolean isValid(int x, int y)  {
    	//wall
        Wall[] walls = GameWindow.walls;
        SnakeCave[] snakeCaves = GameWindow.snakeCaves;
        for(int i=0; i<Wall.WALL_NUMBER; i++) {
            for(int row=0; row<walls[i].height; row++) {
                for(int col=0; col<walls[i].width; col++) {
                    if(x==walls[i].x+GameWindow.unit*col && y==walls[i].y+GameWindow.unit*row)
                        return false;
                }
            }
        }
        //cave
        for(int i=0; i<SnakeCave.CAVE_NUMBER; i++) {
            if(x==snakeCaves[i].x && y==snakeCaves[i].y)
                return false;
        }
        //snakes
        try {
            for(int i=0;i<p1.getSnake().length; i++) {
            	if(x==p1.getSnake().bodies[i].x && y==p1.getSnake().bodies[i].y)
            		return false;
            }
            for(int i=0;i<p2.getSnake().length; i++) {
            	if(x==p2.getSnake().bodies[i].x && y==p2.getSnake().bodies[i].y)
            		return false;
            }
        }catch(NotEnoughSnakeException e) {
        	
        }
        return true;
    }
    public void putWall(Wall[] walls) {
        GameWindow.walls = walls;
    }
    public void putCave(SnakeCave[] cave) {
        GameWindow.snakeCaves = cave;
    }
    //---------hit head----------
    public boolean isHitHead(Snake s, Snake other) throws NotEnoughSnakeException {
    	if(s.bodies[0].x>other.bodies[0].x - unit
            	&& s.bodies[0].x<other.bodies[0].x + unit
                && s.bodies[0].y>other.bodies[0].y - unit
                && s.bodies[0].y<other.bodies[0].y + unit
                && s.bodies[0].show 
                && other.bodies[0].show)
    		return true;
    	return false;
    }
    //-------------hit wall or other's bodies------------
    public boolean isHit(Snake s, Snake other) throws NotEnoughSnakeException{
    	//hit wall
        for(int i=0; i<Wall.WALL_NUMBER; i++) { //hit wall
            for(int row=0; row<walls[i].height; row++) {
                for(int col=0; col<walls[i].width; col++) {
                    if(s.bodies[0].x>walls[i].x+unit*col-unit 
                    	&& s.bodies[0].x<walls[i].x+unit*col+unit
                        && s.bodies[0].y>walls[i].y+unit*row-unit
                        && s.bodies[0].y<walls[i].y+unit*row+unit
                        && s.bodies[0].show)
                        return true;
                }
            }
        }
        //hit bodies
        for(int i=1; i<s.length; i++) {
            if(s.bodies[0].x==s.bodies[i].x
                && s.bodies[0].y==s.bodies[i].y
                && s.bodies[0].show 
                && s.bodies[i].show) {
                    //System.out.println(i);
                    //System.out.println(s.dir);
                    return true;
                }
        }
        //hit the other snake's body
        for(int i=1; i<other.length; i++) {
            if(s.bodies[0].x>other.bodies[i].x - unit
            	&& s.bodies[0].x<other.bodies[i].x + unit
                && s.bodies[0].y>other.bodies[i].y - unit
                && s.bodies[0].y<other.bodies[i].y + unit
                && s.bodies[0].show 
                && other.bodies[i].show) {
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
                        if(MusicThread.volume!=0) {
                            appleIn = AudioSystem.getAudioInputStream(appleFile);
                            appleClip = AudioSystem.getClip();
                            appleClip.open(appleIn);
                            appleClip.start();
                        }
                        return true;
                    }
                }catch(NullPointerException e) {

                } catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
            }
        }
        return false;
    }
    //speed control
    public void speedControl() {
    	if(p1.numberOfPoint>=20 || p2.numberOfPoint>=20)
    		Snake.speed = 300;
    	else if(p1.numberOfPoint>=10 || p2.numberOfPoint>=10)
    		Snake.speed = 200;
    	else
    		Snake.speed = 120;
    }
    //game over control
    public void pointControl() {
    	if(p1.numberOfPoint>=30 || p2.numberOfPoint>=30)
    		GAMEOVER = true;
    }
    //run
	@Override
	public void run() {
		//new Thread(new Run()).start();
		int put = 0;
		while(true) {
			if(Main.change==1) {
				init();
				Main.change = 0;
			}
			if(Main.window==2) {
				//
				//System.out.println("run");
				try {
					if(!PAUSE && !GAMEOVER) moveSnake(p1.getSnake(), p2.getSnake(), 0);
					if(!PAUSE && !GAMEOVER) moveSnake(p2.getSnake(), p1.getSnake(), 1);
				}catch(NotEnoughSnakeException e) {
					GAMEOVER = true;
				}
				//System.out.println("hi");
			}
			if(Point.eggLeft==0 && !PAUSE) {
                put++;
            }
            if(put==2*Snake.speed && !PAUSE) {
                putPoint();
                putPoint();
                Point.eggLeft = 2;
                put = 0;
            }
			try {Thread.sleep(1000/Snake.speed);} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	public void init() {
    	p1 = new Player(5);
    	p2 = new Player(5, 2);
    	numberOfPoint = 0;
        points = null;
    	move = new int[2];
        //keyboard
    	valid = new boolean[2];
        //mouse
    	exitClick = false;
    	resumeClick = false;
    	menuClick = false;
        Point.eggLeft = 2;
        //put wall
        putWall(Wall.getWalls());
        //put caves
        putCave(SnakeCave.getSnakeCaves());
        //put egg
        putPoint();
        putPoint();
        //level
        Snake.speed = 120;
    }
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(Main.window==1) {
			switch(e.getKeyCode()) {
	        case KeyEvent.VK_ENTER:
	            if(BeginWindow.choice==0) {
	            	Main.window = 2;
	            	GameWindow.PAUSE = false;
	            	/*try {
	            		System.out.println("ready");
					} catch (IOException e1) {}*/
	            }
	            break;
	        case KeyEvent.VK_UP:
	            if(BeginWindow.choice!=0) {
	            	BeginWindow.choice--;
	            }
	            break;
	        case KeyEvent.VK_DOWN:
	            if(BeginWindow.choice!=1)
	            	BeginWindow.choice++;
	            break;
		}
		}
		if(Main.window==2) {
			try {
	            switch(e.getKeyCode()) {
	                case KeyEvent.VK_LEFT:
	                    if(!GameWindow.GAMEOVER && p1.getSnake().bodies[0].show && p1.getSnake().wait==0 && !GameWindow.PAUSE && p1.getSnake().dir!=0 && GameWindow.valid[0]) {
	                        p1.getSnake().dir = 1;
		                    //dos.writeBytes("1\n");
		                    //System.out.println("1");
	                        if(MusicThread.volume!=0) {
	                        	reIn = AudioSystem.getAudioInputStream(reFile);
		                        re = AudioSystem.getClip();
		                        re.open(reIn);
		                        re.start();
	                        }
	                        GameWindow.valid[0] = false;
	                    }
	                    break;
	                case KeyEvent.VK_RIGHT:
	                    if(!GameWindow.GAMEOVER && p1.getSnake().bodies[0].show &&  p1.getSnake().wait==0 && !GameWindow.PAUSE && p1.getSnake().dir!=1 && GameWindow.valid[0]) {
	                        p1.getSnake().dir = 0;
	                        //dos.writeBytes("0\n");
		                    //System.out.println("0");
	                        if(MusicThread.volume!=0) {
	                        	soIn = AudioSystem.getAudioInputStream(soFile);
		                        so = AudioSystem.getClip();
		                        so.open(soIn);
		                        so.start();
	                        }
	                        GameWindow.valid[0] = false;
	                    }
	                    break;
	                case KeyEvent.VK_UP:
	                    if(!GameWindow.GAMEOVER && p1.getSnake().bodies[0].show && p1.getSnake().wait==0 && !GameWindow.PAUSE && p1.getSnake().dir!=3 && GameWindow.valid[0]) {
	                        p1.getSnake().dir = 2;
	                        //dos.writeBytes("2\n");
		                    //System.out.println("2");
	                        if(MusicThread.volume!=0) {
		                        laIn = AudioSystem.getAudioInputStream(laFile);
		                        la = AudioSystem.getClip();
		                        la.open(laIn);
		                        la.start();
	                        }
	                        GameWindow.valid[0] = false;
	                    }
	                    break;
	                case KeyEvent.VK_DOWN:
	                    if(!GameWindow.GAMEOVER && p1.getSnake().bodies[0].show && p1.getSnake().wait==0 && !GameWindow.PAUSE && p1.getSnake().dir!=2 && GameWindow.valid[0]) {
	                        p1.getSnake().dir = 3;
	                        //dos.writeBytes("3\n");
		                    //System.out.println("3");
	                        if(MusicThread.volume!=0) {
		                        doIn = AudioSystem.getAudioInputStream(doFile);
		                        doo = AudioSystem.getClip();
		                        doo.open(doIn);
		                        doo.start();
	                        }
	                        GameWindow.valid[0] = false;
	                    }
	                    break;
	                //player2
	                case KeyEvent.VK_A:
	                    if(!GameWindow.GAMEOVER && p2.getSnake().bodies[0].show && p2.getSnake().wait==0 && !GameWindow.PAUSE && p2.getSnake().dir!=0 && GameWindow.valid[1]) {
	                        p2.getSnake().dir = 1;
	                        //dos.writeBytes("1\n");
	                        if(MusicThread.volume!=0) {
		                        reIn = AudioSystem.getAudioInputStream(reFile);
		                        re = AudioSystem.getClip();
		                        re.open(reIn);
		                        re.start();
	                        }
	                        GameWindow.valid[1] = false;
	                    }
	                    break;
	                case KeyEvent.VK_D:
	                    if(!GameWindow.GAMEOVER && p2.getSnake().bodies[0].show && p2.getSnake().wait==0 && !GameWindow.PAUSE && p2.getSnake().dir!=1 && GameWindow.valid[1]) {
	                        p2.getSnake().dir = 0;
	                        //dos.writeBytes("0\n");
	                        if(MusicThread.volume!=0) {
	                        	soIn = AudioSystem.getAudioInputStream(soFile);
		                        so = AudioSystem.getClip();
		                        so.open(soIn);
		                        so.start();
	                        }
	                        GameWindow.valid[1] = false;
	                    }
	                    break;
	                case KeyEvent.VK_W:
	                    if(!GameWindow.GAMEOVER && p2.getSnake().bodies[0].show && p2.getSnake().wait==0 && !GameWindow.PAUSE && p2.getSnake().dir!=3 && GameWindow.valid[1]) {
	                        p2.getSnake().dir = 2;
	                        //dos.writeBytes("2\n");
	                        if(MusicThread.volume!=0) {
	                        	laIn = AudioSystem.getAudioInputStream(laFile);
		                        la = AudioSystem.getClip();
		                        la.open(laIn);
		                        la.start();
	                        }
	                        GameWindow.valid[1] = false;
	                    }
	                    break;
	                case KeyEvent.VK_S:
	                    if(!GameWindow.GAMEOVER && p2.getSnake().bodies[0].show && p2.getSnake().wait==0 && !GameWindow.PAUSE && p2.getSnake().dir!=2 && GameWindow.valid[1]) {
	                        p2.getSnake().dir = 3;
	                        //dos.writeBytes("3\n");
	                        if(MusicThread.volume!=0) {
		                        doIn = AudioSystem.getAudioInputStream(doFile);
		                        doo = AudioSystem.getClip();
		                        doo.open(doIn);
		                        doo.start();
	                        }
	                        GameWindow.valid[1] = false;
	                    }
	                    break;
	                case KeyEvent.VK_ESCAPE:
	                	if(!GameWindow.GAMEOVER)
	                		GameWindow.PAUSE = !GameWindow.PAUSE;
	                    break;
	            }
	        } catch (NotEnoughSnakeException exception) {
	        	GameWindow.GAMEOVER = true;
	        } catch (Exception ex) {

	        }
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(Main.window==2) {
			if(e.getKeyCode()==KeyEvent.VK_M) {
				if(MusicThread.volume==0.4f)
					MusicThread.volume = 0;
				else if(MusicThread.volume==0)
					MusicThread.volume = 0.4f;
			}
		}
	}
	class Run implements Runnable {

		@Override
		public void run() {
			while(true) {
				
			}
		}
		
	}
}