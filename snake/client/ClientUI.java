package snake.client;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import snake.game.*;
import snake.util.Setting;


public class ClientUI extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable{

	private static final long serialVersionUID = 1L;
	Socket client;
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	boolean waitOtherPlayer;
	int id;
	//pause
	boolean resumeClick;
	boolean exitClick;
	boolean menuClick;
    //
    int WIDTH = 1000;
    int HEIGHT = 600;
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
    //loading
    Image loading1;
    Image loading2;
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
    //data
    DynamicUIData duidata;
    UIData uidata;
    //loading
    int angd;
    int angn;
    int p1angd;
    int p1angn;
    int p2angd;
    int p2angn;
	ClientUI(){
        setPreferredSize(new Dimension(1000, 600));
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
            loading1 = ImageIO.read(new File(".\\source\\picture\\loading1.png"));
            loading2 = ImageIO.read(new File(".\\source\\picture\\loading2.png"));
            map = ImageIO.read(new File(".\\source\\picture\\map.png"));
            wall = ImageIO.read(new File(".\\source\\picture\\wall.png"));
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
        } catch (Exception e) {
            System.out.println("Picture Error");
        }
        //id
        id = -1;
        //pause
    	resumeClick = false;
    	exitClick = false;
    	menuClick = false;
        //wait
        waitOtherPlayer = true;
        //data
        duidata = new DynamicUIData();
        uidata = new UIData();
        //loading
        angd = 0;
        angn = 0;
        p1angd = 0;
        p1angn = 0;
        p2angd = 0;
        p2angn = 0;
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    //init
    private void init(UIData uiData) {
        this.uidata = uiData;
        waitOtherPlayer = false;
        //System.out.println(uidata.numberOfWall);
    }
	//update
	private void update(DynamicUIData duiData) {
        this.duidata = duiData;
        duidata.GAMEOVER = duiData.GAMEOVER;
        duidata.inGame1 = duiData.inGame1;
        duidata.inGame2 = duiData.inGame2;
	}
	@Override
	public void run() {
        while(true) {
            try {
                client = new Socket(Client.IP, Client.port);
                oos = new ObjectOutputStream(client.getOutputStream());
                ois = new ObjectInputStream(client.getInputStream());
                System.out.println("connect");
                break;
            } catch (IOException e) {
                System.out.println("Connect Error");
            }
        }
		UIData uiData = null;
        try {
    		//get id
            id = (Integer)ois.readObject();
            //System.out.println(id);
            if(id==0) {
            	duidata.inGame1 = true;
            }
            else if(id==1){
            	duidata.inGame1 = true;
            	duidata.inGame2 = true;
            }
            oos.reset();
            oos.writeObject(duidata);
            uiData = (UIData)ois.readObject();
            init(uiData);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		while(true) {
			DynamicUIData duiData = null;
			try {
				if(client!=null && client.isConnected()) {
	                duiData = (DynamicUIData)ois.readObject();
	                update(duiData);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {

			}
		}
    }
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(map, 0, 0, WIDTH-200, HEIGHT, null);
        //---------------------paint wall---------------------
        for(int i=0; i<uidata.numberOfWall; i++) {
            try {
                for(int row=0; row<uidata.walls[i].height; row++) {
                    for(int col=0; col<uidata.walls[i].width; col++) {
                        g.drawImage(wall, uidata.walls[i].x+Setting.unit*col, uidata.walls[i].y+Setting.unit*row, Setting.unit, Setting.unit, null);
                    }
                }
            } catch (NullPointerException e) {

            }
        }
        //----------------------------------paint apple------------------------
        for(int i=0; i<duidata.numberOfApple; i++) {
            try{
                g.drawImage(apple, duidata.points[i].x, duidata.points[i].y-8, Setting.unit, Setting.unit+8, null);
            }catch(NullPointerException e) {

            }
        }
        for(int i=0; i<uidata.numberOfCave; i++) {
            try{
                g.drawImage(hole, uidata.snakeCaves[i].x, uidata.snakeCaves[i].y, Setting.unit, Setting.unit, null);
            }catch(NullPointerException e) {

            }
        }
        //--------------------------------paint p1's snake---------------------------------
        try {
            //System.out.println(uidata.s1.wait);
            if(duidata.s1.wait==0) {
                for(int i=1; i<duidata.s1.length-1; i++) {
                    //g.setColor(uidata.s1.color);
                    g.setColor(Color.ORANGE);
                    if(duidata.s1.bodies[i].x!=-1 && duidata.s1.bodies[i].x != -1 
                        && duidata.s1.bodies[i].show) {
                        g.fillArc(duidata.s1.bodies[i].x, duidata.s1.bodies[i].y, Setting.unit, Setting.unit, 0, 360);
                        if(duidata.s1.bodies[i].x>WIDTH-200-Setting.unit
                            && duidata.s1.bodies[i].show) {
                            g.fillArc((duidata.s1.bodies[i].x%(WIDTH-200-Setting.unit))-Setting.unit, duidata.s1.bodies[i].y, Setting.unit, Setting.unit, 0, 360);
                        }
                        if(duidata.s1.bodies[i].x<0
                            && duidata.s1.bodies[i].show) {
                            g.fillArc(duidata.s1.bodies[i].x+WIDTH-200, duidata.s1.bodies[i].y, Setting.unit, Setting.unit, 0, 360);
                        }
                        if(duidata.s1.bodies[i].y>HEIGHT-Setting.unit
                            && duidata.s1.bodies[i].show) {
                            g.fillArc(duidata.s1.bodies[i].x, duidata.s1.bodies[i].y%(HEIGHT-Setting.unit)-Setting.unit, Setting.unit, Setting.unit, 0, 360);
                        }
                        if(duidata.s1.bodies[i].y<0
                            && duidata.s1.bodies[i].show) {
                            g.fillArc(duidata.s1.bodies[i].x, duidata.s1.bodies[i].y+HEIGHT, Setting.unit, Setting.unit, 0, 360);
                        }
                    }
                    //System.out.println(duidata.s1.bodies[i].x+", "+duidata.s1.bodies[i].y);
                }
                //for head
                Image head = null;
                switch(duidata.s1.bodies[0].dir) {
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
                if(duidata.s1.bodies[0].show)
                	g.drawImage(head, duidata.s1.bodies[0].x, duidata.s1.bodies[0].y, Setting.unit, Setting.unit, null);
                if(duidata.s1.bodies[0].x>WIDTH-200-Setting.unit
                    && duidata.s1.bodies[0].show) {
                	g.drawImage(head, (duidata.s1.bodies[0].x%(WIDTH-200-Setting.unit))-Setting.unit, duidata.s1.bodies[0].y, Setting.unit, Setting.unit, null);
                }
                if(duidata.s1.bodies[0].x<0
                    && duidata.s1.bodies[0].show) {
                	g.drawImage(head, duidata.s1.bodies[0].x+WIDTH-200, duidata.s1.bodies[0].y, Setting.unit, Setting.unit, null);
                }
                if(duidata.s1.bodies[0].y>HEIGHT-Setting.unit
                    && duidata.s1.bodies[0].show) {
                	g.drawImage(head, duidata.s1.bodies[0].x, duidata.s1.bodies[0].y%(HEIGHT-Setting.unit)-Setting.unit, Setting.unit, Setting.unit, null);
                }
                if(duidata.s1.bodies[0].y<0
                    && duidata.s1.bodies[0].show) {
                	g.drawImage(head, duidata.s1.bodies[0].x, duidata.s1.bodies[0].y+HEIGHT, Setting.unit, Setting.unit, null);
                }
                //for tail
                Image tail = null;
                switch(duidata.s1.bodies[duidata.s1.length-1].dir) {
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
                if(duidata.s1.bodies[duidata.s1.length-1].show)
                	g.drawImage(tail, duidata.s1.bodies[duidata.s1.length-1].x, duidata.s1.bodies[duidata.s1.length-1].y, Setting.unit, Setting.unit, null);
                if(duidata.s1.bodies[duidata.s1.length-1].x>WIDTH-200-Setting.unit
                    && duidata.s1.bodies[duidata.s1.length-1].show) {
                	g.drawImage(tail, (duidata.s1.bodies[duidata.s1.length-1].x%(WIDTH-200-Setting.unit))-Setting.unit, duidata.s1.bodies[duidata.s1.length-1].y, Setting.unit, Setting.unit, null);
                }
                if(duidata.s1.bodies[duidata.s1.length-1].x<0
                    && duidata.s1.bodies[duidata.s1.length-1].show) {
                	g.drawImage(tail, duidata.s1.bodies[duidata.s1.length-1].x+WIDTH-200, duidata.s1.bodies[duidata.s1.length-1].y, Setting.unit, Setting.unit, null);
                }
                if(duidata.s1.bodies[duidata.s1.length-1].y>HEIGHT-Setting.unit
                    && duidata.s1.bodies[duidata.s1.length-1].show) {
                	g.drawImage(tail, duidata.s1.bodies[duidata.s1.length-1].x, duidata.s1.bodies[duidata.s1.length-1].y%(HEIGHT-Setting.unit)-Setting.unit, Setting.unit, Setting.unit, null);
                }
                if(duidata.s1.bodies[duidata.s1.length-1].y<0
                    && duidata.s1.bodies[duidata.s1.length-1].show) {
                	g.drawImage(tail, duidata.s1.bodies[duidata.s1.length-1].x, duidata.s1.bodies[duidata.s1.length-1].y+HEIGHT, Setting.unit, Setting.unit, null);
                }
                //
            }
        } catch (Exception e) {
            //System.out.println(p1.numberOfSnack);
            //e.printStackTrace();
        }
        //---------------------paint p2's snake---------------------------------
        try {
            //System.out.println(duidata.s2.wait);
            if(duidata.s2.wait==0) {
                for(int i=1; i<duidata.s2.length-1; i++) {
                    //g.setColor(duidata.s2.color);
                    g.setColor(new Color(0, 209, 0));
                    if(duidata.s2.bodies[i].x!=-1 && duidata.s2.bodies[i].x != -1 
                        && duidata.s2.bodies[i].show) {
                        g.fillArc(duidata.s2.bodies[i].x, duidata.s2.bodies[i].y, Setting.unit, Setting.unit, 0, 360);
                        if(duidata.s2.bodies[i].x>WIDTH-200-Setting.unit
                            && duidata.s2.bodies[i].show) {
                            g.fillArc((duidata.s2.bodies[i].x%(WIDTH-200-Setting.unit))-Setting.unit, duidata.s2.bodies[i].y, Setting.unit, Setting.unit, 0, 360);
                        }
                        if(duidata.s2.bodies[i].x<0
                            && duidata.s2.bodies[i].show) {
                            g.fillArc(duidata.s2.bodies[i].x+WIDTH-200, duidata.s2.bodies[i].y, Setting.unit, Setting.unit, 0, 360);
                        }
                        if(duidata.s2.bodies[i].y>HEIGHT-Setting.unit
                            && duidata.s2.bodies[i].show) {
                            g.fillArc(duidata.s2.bodies[i].x, duidata.s2.bodies[i].y%(HEIGHT-Setting.unit)-Setting.unit, Setting.unit, Setting.unit, 0, 360);
                        }
                        if(duidata.s2.bodies[i].y<0
                            && duidata.s2.bodies[i].show) {
                            g.fillArc(duidata.s2.bodies[i].x, duidata.s2.bodies[i].y+HEIGHT, Setting.unit, Setting.unit, 0, 360);
                        }
                    }
                }
              //for head
                Image head = null;
                switch(duidata.s2.bodies[0].dir) {
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
                if(duidata.s2.bodies[0].show)
                	g.drawImage(head, duidata.s2.bodies[0].x, duidata.s2.bodies[0].y, Setting.unit, Setting.unit, null);
                	//g.fillArc(duidata.s2.bodies[0].x, duidata.s2.bodies[0].y, unit, unit, 0, 360);
                if(duidata.s2.bodies[0].x>WIDTH-200-Setting.unit
                    && duidata.s2.bodies[0].show) {
                	g.drawImage(head, (duidata.s2.bodies[0].x%(WIDTH-200-Setting.unit))-Setting.unit, duidata.s2.bodies[0].y, Setting.unit, Setting.unit, null);
                }
                if(duidata.s2.bodies[0].x<0
                    && duidata.s2.bodies[0].show) {
                	g.drawImage(head, duidata.s2.bodies[0].x+WIDTH-200, duidata.s2.bodies[0].y, Setting.unit, Setting.unit, null);
                }
                if(duidata.s2.bodies[0].y>HEIGHT-Setting.unit
                    && duidata.s2.bodies[0].show) {
                	g.drawImage(head, duidata.s2.bodies[0].x, duidata.s2.bodies[0].y%(HEIGHT-Setting.unit)-Setting.unit, Setting.unit, Setting.unit, null);
                }
                if(duidata.s2.bodies[0].y<0
                    && duidata.s2.bodies[0].show) {
                	g.drawImage(head, duidata.s2.bodies[0].x, duidata.s2.bodies[0].y+HEIGHT, Setting.unit, Setting.unit, null);
                }
                //for tail
                Image tail = null;
                switch(duidata.s2.bodies[duidata.s2.length-1].dir) {
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
                if(duidata.s2.bodies[duidata.s2.length-1].show)
                	g.drawImage(tail, duidata.s2.bodies[duidata.s2.length-1].x, duidata.s2.bodies[duidata.s2.length-1].y, Setting.unit, Setting.unit, null);
                	//g.fillArc(duidata.s2.bodies[0].x, duidata.s2.bodies[0].y, unit, unit, 0, 360);
                if(duidata.s2.bodies[duidata.s2.length-1].x>WIDTH-200-Setting.unit
                    && duidata.s2.bodies[duidata.s2.length-1].show) {
                	g.drawImage(tail, (duidata.s2.bodies[duidata.s2.length-1].x%(WIDTH-200-Setting.unit))-Setting.unit, duidata.s2.bodies[duidata.s2.length-1].y, Setting.unit, Setting.unit, null);
                }
                if(duidata.s2.bodies[duidata.s2.length-1].x<0
                    && duidata.s2.bodies[duidata.s2.length-1].show) {
                	g.drawImage(tail, duidata.s2.bodies[duidata.s2.length-1].x+WIDTH-200, duidata.s2.bodies[duidata.s2.length-1].y, Setting.unit, Setting.unit, null);
                }
                if(duidata.s2.bodies[duidata.s2.length-1].y>HEIGHT-Setting.unit
                    && duidata.s2.bodies[duidata.s2.length-1].show) {
                	g.drawImage(tail, duidata.s2.bodies[duidata.s2.length-1].x, duidata.s2.bodies[duidata.s2.length-1].y%(HEIGHT-Setting.unit)-Setting.unit, Setting.unit, Setting.unit, null);
                }
                if(duidata.s2.bodies[duidata.s2.length-1].y<0
                    && duidata.s2.bodies[duidata.s2.length-1].show) {
                	g.drawImage(tail, duidata.s2.bodies[duidata.s2.length-1].x, duidata.s2.bodies[duidata.s2.length-1].y+HEIGHT, Setting.unit, Setting.unit, null);
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
        if(duidata.p1.numberOfSnack==1) {
        	g.setColor(Color.RED);
        }
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(""+duidata.p1.numberOfSnack, WIDTH-50, HEIGHT-220);
        //----------------------paint p1's score------------------
        g.setColor(Color.BLACK);
        if(duidata.p1.numberOfPoint>=25) {
        	g.setColor(Color.RED);
        }
        g.setFont(new Font("Arial", Font.BOLD, 40+duidata.p1.numberOfPoint));
        g.drawString(""+duidata.p1.numberOfPoint, WIDTH-100, HEIGHT-170);
        //---------------------paint p2's lives------------------------
        g.setColor(Color.BLACK);
        if(duidata.p2.numberOfSnack==1) {
        	g.setColor(Color.RED);
        }
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(""+duidata.p2.numberOfSnack, WIDTH-50, HEIGHT-80);
        //----------------------paint p2's score---------------------
        g.setColor(Color.BLACK);
        if(duidata.p2.numberOfPoint>=25) {
        	g.setColor(Color.RED);
        }
        g.setFont(new Font("Arial", Font.BOLD, 40+duidata.p2.numberOfPoint));
        g.drawString(""+duidata.p2.numberOfPoint, WIDTH-100, HEIGHT-30);
        //-------------------paint p1's hint of in cave---------------------
        try {
            if(duidata.s1.isInCave) {
            	g.drawImage(orangeInCave, (WIDTH-200)/2-110, 0, 200, 100, null);
                g.setColor(new Color(0f, 0f, 0f, 0.5f));
                g.fillRoundRect((WIDTH-200)/2-100, 0, 200, 100, 25, 25);
            }
        } catch (Exception e) {

        }
        //--------------------paint p2's hint of in cave-----------------
        try {
            if(duidata.s2.isInCave) {
            	g.drawImage(greenInCave, (WIDTH-200)/2-110, 500, 200, 100, null);
                g.setColor(new Color(0f, 0f, 0f, 0.5f));
                g.fillRoundRect((WIDTH-200)/2-100, 500, 200, 100, 25, 25);
            }
        } catch (Exception e) {

        }
        //-------------------------------Level-----------------------------
        g.setColor(new Color(0, 0, 0, 100));
		g.setFont(new Font("Console", Font.BOLD, 30));
        switch (duidata.speed) {
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
        //someone leave the game
        if((!duidata.inGame2 && id==0) || (!duidata.inGame1 && id==1)) {
        	g.setColor(Color.RED);
        	g.setFont(new Font("STLiti", Font.BOLD, 25));
        	g.drawString("The other player", 800, 175);
        	g.drawString("has left the game.", 800, 200);
        }
        //-----------------------paint pause page----------------------
        if(duidata.PAUSE1 && id==0) {
            g.setColor(new Color(0f, 0f, 0f, 0.5f));
            g.fillRect(0, 0, WIDTH-200, HEIGHT);
            //resume
            if(resumeClick) {
            	//g.drawImage(exitClickIm, WIDTH/2-5*400/Setting.unit-100-2,  HEIGHT/2-HEIGHT/Setting.unit-10*250/Setting.unit-2, 10*400/Setting.unit+4, 10*250/Setting.unit+4, null);
            	g.drawImage(exitClickIm, WIDTH/2-5*400/Setting.unit-100-2, 55-2, 200+4, 125+4, null);
            }
            g.drawImage(resume, WIDTH/2-5*400/Setting.unit-100, 55, 200, 125, null);
            //menu
            if(menuClick) {
            	g.drawImage(exitClickIm, WIDTH/2-5*400/Setting.unit-100-2,  235-2, 10*400/Setting.unit+4, 10*250/Setting.unit+4, null);
            }
            g.drawImage(menu, WIDTH/2-5*400/Setting.unit-100, 235, 10*400/Setting.unit, 10*250/Setting.unit, null);
            //exit
            if(exitClick)
            	g.drawImage(exitClickIm, WIDTH/2-5*400/Setting.unit-100-2, 415-2, 10*400/Setting.unit+4, 10*250/Setting.unit+4, null);
            g.drawImage(exit, WIDTH/2-5*400/Setting.unit-100, 415, 10*400/Setting.unit, 10*250/Setting.unit, null);
            //
        }
        if(duidata.PAUSE1 && id==1) {
            g.setColor(new Color(255, 255, 255, 100));
            g.setFont(new Font("STLiti", Font.BOLD, 70));
            g.drawString("PAUSE", 300, 200);
            if(p1angn==360&&p1angd==0) {
                p1angd+=5;
            }
            else if(p1angn>0&&p1angd==0){
                p1angn+=5;
            }
            else if(p1angn>0&&p1angd>0) {
                p1angd+=5;
                p1angn = 360-p1angd;
            }
            else if(p1angn==0&&p1angd==360) {
                p1angd=0;
            }
            else if(p1angn==0&&p1angd==0){
                p1angn+=5;
            }
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke(7.0f));
            g2.drawArc(350, 250, 100, 100, 90+p1angd, 0+p1angn);
        }
        if(duidata.PAUSE2 && id==0) {
            g.setColor(new Color(255, 255, 255, 100));
            g.setFont(new Font("STLiti", Font.BOLD, 70));
            g.drawString("PAUSE", 300, 200);
            if(p2angn==360&&p2angd==0) {
                p2angd+=5;
            }
            else if(p2angn>0&&p2angd==0){
                p2angn+=5;
            }
            else if(p2angn>0&&p2angd>0) {
                p2angd+=5;
                p2angn = 360-p2angd;
            }
            else if(p2angn==0&&p2angd==360) {
                p2angd=0;
            }
            else if(p2angn==0&&p2angd==0){
                p2angn+=5;
            }
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke(7.0f));
            g2.drawArc(350, 250, 100, 100, 90+p2angd, 0+p2angn);
        } 
        //
        if(duidata.PAUSE2 && id==1) {
            g.setColor(new Color(0f, 0f, 0f, 0.5f));
            g.fillRect(0, 0, WIDTH-200, HEIGHT);
            //resume
            if(resumeClick) {
            	//g.drawImage(exitClickIm, WIDTH/2-5*400/Setting.unit-100-2,  HEIGHT/2-HEIGHT/Setting.unit-10*250/Setting.unit-2, 10*400/Setting.unit+4, 10*250/Setting.unit+4, null);
            	g.drawImage(exitClickIm, WIDTH/2-5*400/Setting.unit-100-2, 55-2, 200+4, 125+4, null);
            }
            g.drawImage(resume, WIDTH/2-5*400/Setting.unit-100, 55, 200, 125, null);
            //menu
            if(menuClick) {
            	g.drawImage(exitClickIm, WIDTH/2-5*400/Setting.unit-100-2,  235-2, 10*400/Setting.unit+4, 10*250/Setting.unit+4, null);
            }
            g.drawImage(menu, WIDTH/2-5*400/Setting.unit-100, 235, 10*400/Setting.unit, 10*250/Setting.unit, null);
            //exit
            if(exitClick)
            	g.drawImage(exitClickIm, WIDTH/2-5*400/Setting.unit-100-2, 415-2, 10*400/Setting.unit+4, 10*250/Setting.unit+4, null);
            g.drawImage(exit, WIDTH/2-5*400/Setting.unit-100, 415, 10*400/Setting.unit, 10*250/Setting.unit, null);
            //
        }
        //----------------------------------------Game over----------------------------------
        if(duidata.GAMEOVER) {
            g.setColor(new Color(0f, 0f, 0f, 0.5f));
            g.fillRect(0, 0, WIDTH-200, HEIGHT);
            if(duidata.p1.numberOfPoint>=30) 
            	g.drawImage(orangeWin, WIDTH/2-5*400/Setting.unit-200, 30, 400, 200, null);
            else if(duidata.p2.numberOfPoint>=30) 
            	g.drawImage(greenWin, WIDTH/2-5*400/Setting.unit-200, 30, 400, 200, null);
            else if(duidata.p1.numberOfSnack>duidata.p2.numberOfSnack)
            	g.drawImage(orangeWin, WIDTH/2-5*400/Setting.unit-200, 30, 400, 200, null);
            else if(duidata.p1.numberOfSnack<duidata.p2.numberOfSnack)
            	g.drawImage(greenWin, WIDTH/2-5*400/Setting.unit-200, 30, 400, 200, null);
            if(menuClick) {
            	g.drawImage(exitClickIm, WIDTH/2-5*400/Setting.unit-100-2,  235-2, 10*400/Setting.unit+4, 10*250/Setting.unit+4, null);
            }
            g.drawImage(menu, WIDTH/2-5*400/Setting.unit-100, 235, 10*400/Setting.unit, 10*250/Setting.unit, null);
            if(exitClick)
            	g.drawImage(exitClickIm, WIDTH/2-5*400/Setting.unit-100-2, 415-2, 10*400/Setting.unit+4, 10*250/Setting.unit+4, null);
            g.drawImage(exit, WIDTH/2-5*400/Setting.unit-100, 415, 10*400/Setting.unit, 10*250/Setting.unit, null);
        }
        //wait
        if(duidata.waitOtherPlayer) {
            if(angn==360&&angd==0) {
                angd+=5;
            }
            else if(angn>0&&angd==0){
                angn+=5;
            }
            else if(angn>0&&angd>0) {
                angd+=5;
                angn = 360-angd;
            }
            else if(angn==0&&angd==360) {
                angd=0;
            }
            else if(angn==0&&angd==0){
                angn+=5;
            }
        	if(id==0) {
                g.setColor(Color.black);
                g.fillRect(0, 0, WIDTH, HEIGHT);
                g.drawImage(loading1, 0, 35, 1000, 530, null);
            }
        	else if(id==1){
                g.setColor(Color.black);
                g.fillRect(0, 0, WIDTH, HEIGHT);
                g.drawImage(loading2, 0, 35, 1000, 530, null);
            }
        	else {
                g.setColor(Color.lightGray);
                g.fillRect(0, 0, WIDTH, HEIGHT);
        	}
            g.setColor(new Color(255, 255, 255, 100));
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke(7.0f));
            g2.drawArc(440, 250, 100, 100, 90+angd, 0+angn);
            g.setFont(new Font("STLiti", Font.BOLD, 40));
            g.drawString("Loading . . .", 400, 400);
            g.setFont(new Font("STLiti", Font.BOLD, 20));
            g.setColor(Color.lightGray);
            g.drawString("Remember to check", 405, 500);
            g.drawString("your input way", 420, 530);

    		if(duidata.inGame1) {
    			g.setColor(Color.red);
    			g.setFont(new Font("Arial", Font.ITALIC, 60));
    			g.drawString("Ready", 100, 300);
    		} 
    		if(duidata.inGame2) {
    			g.drawString("Ready", 650, 300);
    		}
        }
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(Client.window==2 && client!=null && client.isConnected()) {
			try {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        if(!duidata.GAMEOVER && duidata.s1.bodies[0].show &&  duidata.s1.wait==0 && !duidata.PAUSE1 && !duidata.PAUSE2 && duidata.s1.dir!=1 && duidata.valid[0] && id==0) {
                            duidata.nextDir1 = 0;
                            duidata.nextDir2 = duidata.s2.dir;
                            /*if(MusicThread.volume!=0) {
                                soIn = AudioSystem.getAudioInputStream(soFile);
                                so = AudioSystem.getClip();
                                so.open(soIn);
                                so.start();
                            }*/
                            duidata.valid[0] = false;
                        }
                        oos.reset();
                        oos.writeObject(duidata);
                    break;
                    case KeyEvent.VK_LEFT:
                        if(!duidata.GAMEOVER && duidata.s1.bodies[0].show && duidata.s1.wait==0 && !duidata.PAUSE1 && !duidata.PAUSE2 && duidata.s1.dir!=0 && duidata.valid[0] && id==0) {
                            duidata.nextDir1 = 1;
                            duidata.nextDir2 = duidata.s2.dir;
                            /*if(MusicThread.volume!=0) {
                                reIn = AudioSystem.getAudioInputStream(reFile);
                                re = AudioSystem.getClip();
                                re.open(reIn);
                                re.start();
                            }*/
                            duidata.valid[0] = false;
                        }
                        oos.reset();
                        oos.writeObject(duidata);
                    break;
                    case KeyEvent.VK_UP:
                        if(!duidata.GAMEOVER && duidata.s1.bodies[0].show && duidata.s1.wait==0 && !duidata.PAUSE1 && !duidata.PAUSE2 && duidata.s1.dir!=3 && duidata.valid[0] && id==0) {
                            duidata.nextDir1 = 2;
                            duidata.nextDir2 = duidata.s2.dir;
                            /*if(MusicThread.volume!=0) {
                                laIn = AudioSystem.getAudioInputStream(laFile);
                                la = AudioSystem.getClip();
                                la.open(laIn);
                                la.start();
                            }*/
                            duidata.valid[0] = false;
                        }
                        oos.reset();
                        oos.writeObject(duidata);
                    break;
                    case KeyEvent.VK_DOWN:
                        if(!duidata.GAMEOVER && duidata.s1.bodies[0].show && duidata.s1.wait==0 && !duidata.PAUSE1 && !duidata.PAUSE2 && duidata.s1.dir!=2 && duidata.valid[0] && id==0) {
                            duidata.nextDir1 = 3;
                            duidata.nextDir2 = duidata.s2.dir;
                            /*if(MusicThread.volume!=0) {
                                doIn = AudioSystem.getAudioInputStream(doFile);
                                doo = AudioSystem.getClip();
                                doo.open(doIn);
                                doo.start();
                            }*/
                            duidata.valid[0] = false;
                        }
                        oos.reset();
                        oos.writeObject(duidata);
                    break;
                    //player2
                    case KeyEvent.VK_D:
                        if(!duidata.GAMEOVER && duidata.s2.bodies[0].show && duidata.s2.wait==0 && !duidata.PAUSE1 && !duidata.PAUSE2 && duidata.s2.dir!=1 && duidata.valid[1] && id==1) {
                            duidata.nextDir2 = 0;
                            duidata.nextDir1 = duidata.s1.dir;
                            /*if(MusicThread.volume!=0) {
                                soIn = AudioSystem.getAudioInputStream(soFile);
                                so = AudioSystem.getClip();
                                so.open(soIn);
                                so.start();
                            }*/
                            duidata.valid[1] = false;
                        }
                        oos.reset();
                        oos.writeObject(duidata);
                    break;
                    case KeyEvent.VK_A:
                        if(!duidata.GAMEOVER && duidata.s2.bodies[0].show && duidata.s2.wait==0 && !duidata.PAUSE1 && !duidata.PAUSE2 && duidata.s2.dir!=0 && duidata.valid[1] && id==1) {
                            duidata.nextDir2 = 1;
                            duidata.nextDir1 = duidata.s1.dir;
                            /*if(MusicThread.volume!=0) {
                                reIn = AudioSystem.getAudioInputStream(reFile);
                                re = AudioSystem.getClip();
                                re.open(reIn);
                                re.start();
                            }*/
                            duidata.valid[1] = false;
                        }
                        oos.reset();
                        oos.writeObject(duidata);
                    break;
                    case KeyEvent.VK_W:
                        if(!duidata.GAMEOVER && duidata.s2.bodies[0].show && duidata.s2.wait==0 && !duidata.PAUSE1 && !duidata.PAUSE2 && duidata.s2.dir!=3 && duidata.valid[1] && id==1) {
                            duidata.nextDir2 = 2;
                            duidata.nextDir1 = duidata.s1.dir;
                            /*if(MusicThread.volume!=0) {
                                laIn = AudioSystem.getAudioInputStream(laFile);
                                la = AudioSystem.getClip();
                                la.open(laIn);
                                la.start();
                            }*/
                            duidata.valid[1] = false;
                        }
                        oos.reset();
                        oos.writeObject(duidata);
                    break;
                    case KeyEvent.VK_S:
                        if(!duidata.GAMEOVER && duidata.s2.bodies[0].show && duidata.s2.wait==0 && !duidata.PAUSE1 && !duidata.PAUSE2 && duidata.s2.dir!=2 && duidata.valid[1] && id==1) {
                            duidata.nextDir2 = 3;
                            duidata.nextDir1 = duidata.s1.dir;
                            /*if(MusicThread.volume!=0) {
                                doIn = AudioSystem.getAudioInputStream(doFile);
                                doo = AudioSystem.getClip();
                                doo.open(doIn);
                                doo.start();
                            }*/
                            duidata.valid[1] = false;
                        }
                        oos.reset();
                        oos.writeObject(duidata);
                    break;
                    case KeyEvent.VK_ESCAPE:
                        if(!duidata.GAMEOVER) {
                            duidata.nextDir1 = duidata.s1.dir;
                            duidata.nextDir2 = duidata.s2.dir;
                            if(id==0)
                            	duidata.PAUSE1 = !duidata.PAUSE1;
                            else if(id==1)
                            	duidata.PAUSE2 = !duidata.PAUSE2;
                        }
                        oos.reset();
                        oos.writeObject(duidata);
                    break;
                    default:
                        duidata.nextDir1 = -1;
                        duidata.nextDir2 = -1;
                        oos.reset();
                        oos.writeObject(duidata);
                    break;
                }
            
	        } catch (Exception ex) {
                System.out.println("Write Error");
	        }
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		/*if(Main.window==2) {
			if(e.getKeyCode()==KeyEvent.VK_M) {
				if(MusicThread.volume==0.4f)
					MusicThread.volume = 0;
				else if(MusicThread.volume==0)
					MusicThread.volume = 0.4f;
			}
		}*/
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(Client.window==2) {
			if(e.getX()>=300&&e.getX()<=500&&e.getY()>=415&&e.getY()<=540) {
				exitClick = true;
			}
			if(e.getX()>=300&&e.getX()<=500&&e.getY()>=55&&e.getY()<=180) {
				resumeClick = true;
			}
			if(e.getX()>=300&&e.getX()<=500&&e.getY()>=235&&e.getY()<=360) {
				menuClick = true;
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(Client.window==2 && client!=null) {
			//resume
			if(e.getX()>=300&&e.getX()<=500&&e.getY()>=55&&e.getY()<=180 && ((duidata.PAUSE1&&id==0) || (duidata.PAUSE2&&id==1)) ) {
				if(id==0)
					duidata.PAUSE1 = false;
				else if(id==1)
					duidata.PAUSE2 = false;
			}
			//menu
			else if(e.getX()>=300&&e.getX()<=500&e.getY()>=235&&e.getY()<=360 && ((duidata.PAUSE1&&id==0) || (duidata.PAUSE2&&id==1) || duidata.GAMEOVER)) {
				Client.window = 0;
				Client.once = 1;
				Client.change = 2;
				duidata.GAMEOVER = false;
				if(id==0) {
					duidata.inGame1 = false;
					duidata.PAUSE1 = false;
				}
				else if(id==1){
					duidata.inGame2 = false;
					duidata.PAUSE2 = false;
				}
				try {
					oos.writeObject(duidata);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					client.close();
					oos.close();
					ois.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			//exit
			else if(e.getX()>=300&&e.getX()<=500&&e.getY()>=415&&e.getY()<=540 && ((duidata.PAUSE1&&id==0) || (duidata.PAUSE2&&id==1) || duidata.GAMEOVER)) {
				if(id==0) {
					duidata.inGame1 = false;
					duidata.PAUSE1 = false;
				}
				else if(id==1){
					duidata.inGame2 = false;
					duidata.PAUSE2 = false;
				}
				try {
					oos.writeObject(duidata);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					client.close();
					oos.close();
					ois.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Client.EXIT = -1;
			}
			resumeClick = false;
			menuClick = false;
			exitClick = false;
			try {
				oos.writeObject(duidata);
			} catch(SocketException e1) {
				
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(Client.window==2) {
			if(e.getX()>=300&&e.getX()<=500&&e.getY()>=415&&e.getY()<=540) {
				exitClick = true;
			}
			else
				exitClick = false;
			if(e.getX()>=300&&e.getX()<=500&&e.getY()>=55&&e.getY()<=180) {
				resumeClick = true;
			}
			else
				resumeClick = false;
			if(e.getX()>=300&&e.getX()<=500&&e.getY()>=235&&e.getY()<=360) {
				menuClick = true;
			}
			else
				menuClick = false;
		}
	}
}
