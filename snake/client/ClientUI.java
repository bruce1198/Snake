package snake.client;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import snake.game.*;
import snake.util.Setting;


public class ClientUI extends JPanel implements KeyListener, MouseListener, Runnable{

	ObjectInputStream ois;
    ObjectOutputStream oos;
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
	ClientUI(ObjectInputStream ois, ObjectOutputStream oos){
		this.ois = ois;
        this.oos = oos;
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
        //data
        duidata = new DynamicUIData();
        uidata = new UIData();
    }
    //init
    private void init(UIData uiData) {
        this.uidata = uiData;
    }
	//update
	private void update(DynamicUIData duiData) {
		this.duidata = duiData;
	}
	@Override
	public void run() {
        UIData uiData = null;
        try {
            uiData = (UIData)ois.readObject();
            init(uiData);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        init(uiData);
		while(true) {
			DynamicUIData duiData = null;
			try {
                duiData = (DynamicUIData)ois.readObject();
                update(duiData);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
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
        /*if(duidata.points!=null) {
            for(int i=0; i<duidata.numberOfPoint; i++) {
                try{
                    g.drawImage(apple, duidata.points[i].x, duidata.points[i].y-8, Setting.unit, Setting.unit+8, null);
                }catch(NullPointerException e) {

                }
            }
        }*/
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
                    	//g.fillRect(uidata.s1.bodies[i].x, uidata.s1.bodies[i].y, unit, unit);
                        g.fillArc(duidata.s1.bodies[i].x, duidata.s1.bodies[i].y, Setting.unit, Setting.unit, 0, 360);
                        if(duidata.s1.bodies[i].x>WIDTH-200-Setting.unit
                            && duidata.s1.bodies[i].show) {
                        	//g.fillRect((uidata.s1.bodies[i].x%(WIDTH-200-unit))-unit, uidata.s1.bodies[i].y, unit, unit);
                            g.fillArc((duidata.s1.bodies[i].x%(WIDTH-200-Setting.unit))-Setting.unit, duidata.s1.bodies[i].y, Setting.unit, Setting.unit, 0, 360);
                        }
                        if(duidata.s1.bodies[i].x<0
                            && duidata.s1.bodies[i].show) {
                        	//g.fillRect(uidata.s1.bodies[i].x+WIDTH-200, uidata.s1.bodies[i].y, unit, unit);
                            g.fillArc(duidata.s1.bodies[i].x+WIDTH-200, duidata.s1.bodies[i].y, Setting.unit, Setting.unit, 0, 360);
                        }
                        if(duidata.s1.bodies[i].y>HEIGHT-Setting.unit
                            && duidata.s1.bodies[i].show) {
                        	//g.fillRect(uidata.s1.bodies[i].x, uidata.s1.bodies[i].y%(HEIGHT-unit)-unit, unit, unit);
                            g.fillArc(duidata.s1.bodies[i].x, duidata.s1.bodies[i].y%(HEIGHT-Setting.unit)-Setting.unit, Setting.unit, Setting.unit, 0, 360);
                        }
                        if(duidata.s1.bodies[i].y<0
                            && duidata.s1.bodies[i].show) {
                        	//g.fillRect(duidata.s1.bodies[i].x, duidata.s1.bodies[i].y+HEIGHT, unit, unit);
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
                	//g.fillArc(duidata.s1.bodies[0].x, duidata.s1.bodies[0].y, unit, unit, 0, 360);
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
                	//g.fillArc(duidata.s1.bodies[0].x, duidata.s1.bodies[0].y, unit, unit, 0, 360);
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
        /*if(PAUSE) {
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
            //resume.setVisible(true);
            //
        }
        //----------------------------------------Game over----------------------------------
        if(GAMEOVER) {
            g.setColor(new Color(0f, 0f, 0f, 0.5f));
            g.fillRect(0, 0, WIDTH-200, HEIGHT);
            if(p1.numberOfPoint>=30) 
            	g.drawImage(orangeWin, WIDTH/2-5*400/Setting.unit-200, 30, 400, 200, null);
            else if(p2.numberOfPoint>=30) 
            	g.drawImage(greenWin, WIDTH/2-5*400/Setting.unit-200, 30, 400, 200, null);
            else if(p1.numberOfSnack>p2.numberOfSnack)
            	g.drawImage(orangeWin, WIDTH/2-5*400/Setting.unit-200, 30, 400, 200, null);
            else if(p1.numberOfSnack<p2.numberOfSnack)
            	g.drawImage(greenWin, WIDTH/2-5*400/Setting.unit-200, 30, 400, 200, null);
            if(menuClick) {
            	g.drawImage(exitClickIm, WIDTH/2-5*400/Setting.unit-100-2,  235-2, 10*400/Setting.unit+4, 10*250/Setting.unit+4, null);
            }
            g.drawImage(menu, WIDTH/2-5*400/Setting.unit-100, 235, 10*400/Setting.unit, 10*250/Setting.unit, null);
            if(exitClick)
            	g.drawImage(exitClickIm, WIDTH/2-5*400/Setting.unit-100-2, 415-2, 10*400/Setting.unit+4, 10*250/Setting.unit+4, null);
            g.drawImage(exit, WIDTH/2-5*400/Setting.unit-100, 415, 10*400/Setting.unit, 10*250/Setting.unit, null);
        }*/
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		/*if(Main.window==1) {
			switch(e.getKeyCode()) {
	        case KeyEvent.VK_ENTER:
	            if(BeginWindow.choice==0) {
	            	Main.window = 2;
	            	Setting.PAUSE = false;
	            	try {
	            		System.out.println("ready");
					} catch (IOException e1) {}
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
		}*/
		if(Setting.window==2) {
			try {
	            switch(e.getKeyCode()) {
	                case KeyEvent.VK_RIGHT:
	                    if(!Setting.GAMEOVER && duidata.s1.bodies[0].show &&  duidata.s1.wait==0 && !Setting.PAUSE && duidata.s1.dir!=1 /*&& Setting.valid[0]*/) {
	                        duidata.nextDir1 = 0;
	                        if(MusicThread.volume!=0) {
	                        	soIn = AudioSystem.getAudioInputStream(soFile);
		                        so = AudioSystem.getClip();
		                        so.open(soIn);
		                        so.start();
	                        }
	                        //Setting.valid[0] = false;
	                    }
	                break;
                    case KeyEvent.VK_LEFT:
                        if(!Setting.GAMEOVER && duidata.s1.bodies[0].show && duidata.s1.wait==0 && !Setting.PAUSE && duidata.s1.dir!=0 /*&& Setting.valid[0]*/) {
                            duidata.nextDir1 = 1;
                            if(MusicThread.volume!=0) {
                                reIn = AudioSystem.getAudioInputStream(reFile);
                                re = AudioSystem.getClip();
                                re.open(reIn);
                                re.start();
                            }
                            //Setting.valid[0] = false;
                        }
                    break;
	                case KeyEvent.VK_UP:
	                    if(!Setting.GAMEOVER && duidata.s1.bodies[0].show && duidata.s1.wait==0 && !Setting.PAUSE && duidata.s1.dir!=3 /*&& Setting.valid[0]*/) {
	                        duidata.nextDir1 = 2;
	                        if(MusicThread.volume!=0) {
		                        laIn = AudioSystem.getAudioInputStream(laFile);
		                        la = AudioSystem.getClip();
		                        la.open(laIn);
		                        la.start();
	                        }
	                        ///Setting.valid[0] = false;
	                    }
	                break;
	                case KeyEvent.VK_DOWN:
	                    if(!Setting.GAMEOVER && duidata.s1.bodies[0].show && duidata.s1.wait==0 && !Setting.PAUSE && duidata.s1.dir!=2 /*&& Setting.valid[0]*/) {
	                        duidata.nextDir1 = 3;
	                        if(MusicThread.volume!=0) {
		                        doIn = AudioSystem.getAudioInputStream(doFile);
		                        doo = AudioSystem.getClip();
		                        doo.open(doIn);
		                        doo.start();
	                        }
	                        ///*Setting.valid[0]*/ = false;
	                    }
	                break;
	                //player2
	                case KeyEvent.VK_D:
	                    if(!Setting.GAMEOVER && duidata.s2.bodies[0].show && duidata.s2.wait==0 && !Setting.PAUSE && duidata.s2.dir!=1 /*&& Setting.valid[1]*/) {
	                        duidata.nextDir2 = 0;
	                        //dos.writeBytes("0\n");
	                        if(MusicThread.volume!=0) {
	                        	soIn = AudioSystem.getAudioInputStream(soFile);
		                        so = AudioSystem.getClip();
		                        so.open(soIn);
		                        so.start();
	                        }
	                        //Setting.valid[1] = false;
	                    }
	                break;
                    case KeyEvent.VK_A:
                        if(!Setting.GAMEOVER && duidata.s2.bodies[0].show && duidata.s2.wait==0 && !Setting.PAUSE && duidata.s2.dir!=0 /*&& Setting.valid[1]*/) {
                            duidata.nextDir2 = 1;
                            //dos.writeBytes("1\n");
                            if(MusicThread.volume!=0) {
                                reIn = AudioSystem.getAudioInputStream(reFile);
                                re = AudioSystem.getClip();
                                re.open(reIn);
                                re.start();
                            }
                            //Setting.valid[1] = false;
                        }
                    break;
	                case KeyEvent.VK_W:
	                    if(!Setting.GAMEOVER && duidata.s2.bodies[0].show && duidata.s2.wait==0 && !Setting.PAUSE && duidata.s2.dir!=3 /*&& Setting.valid[1]*/) {
	                        duidata.nextDir2 = 2;
	                        if(MusicThread.volume!=0) {
	                        	laIn = AudioSystem.getAudioInputStream(laFile);
		                        la = AudioSystem.getClip();
		                        la.open(laIn);
		                        la.start();
	                        }
	                        //Setting.valid[1] = false;
	                    }
	                break;
	                case KeyEvent.VK_S:
	                    if(!Setting.GAMEOVER && duidata.s2.bodies[0].show && duidata.s2.wait==0 && !Setting.PAUSE && duidata.s2.dir!=2 /*&& Setting.valid[1]*/) {
	                        duidata.nextDir2 = 3;
	                        if(MusicThread.volume!=0) {
		                        doIn = AudioSystem.getAudioInputStream(doFile);
		                        doo = AudioSystem.getClip();
		                        doo.open(doIn);
		                        doo.start();
	                        }
	                        //Setting.valid[1] = false;
	                    }
	                break;
	                /*case KeyEvent.VK_ESCAPE:
	                	if(!Setting.GAMEOVER)
	                		Setting.PAUSE = !Setting.PAUSE;
	                    break;*/
                }
                oos.reset();
                oos.writeObject(duidata);
	        } catch (Exception ex) {

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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
    /*public static int EXIT = 666;
    public static int window = 1;
    public static int change = 0;
    public static void main(String[] args) throws InterruptedException
                                                , FileNotFoundException
                                                , IOException
                                                , LineUnavailableException{

        JFrame jFrame = new JFrame("Snake");
        //window
        BeginWindow beginWindow = new BeginWindow();
        //-------------------listener-------------------
        GameMouseListener listener = new GameMouseListener();
        jFrame.setContentPane(beginWindow);
        jFrame.addKeyListener(beginWindow);
        jFrame.addKeyListener(gameWindow);
        jFrame.addMouseListener(listener);
        jFrame.addMouseMotionListener(listener);
        jFrame.addMouseWheelListener(listener);
        //---------------Frame setting-----------------
        jFrame.setUndecorated(true);//hide the bar
        jFrame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        MetalLookAndFeel.setCurrentTheme(new MyTheme());
        try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
        SwingUtilities.updateComponentTreeUI (jFrame);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null); //center the jframe
        //set icon
        Image icon = ImageIO.read(new File(".\\source\\picture\\icon.png"));
        jFrame.setIconImage(icon);
        Image cursor = ImageIO.read(new File(".\\source\\picture\\cursor.png"));
        //set cursor.
        Cursor myCursor = Toolkit.getDefaultToolkit().createCustomCursor(
         cursor, new java.awt.Point(0, 0), "blank cursor");
        jFrame.setCursor(myCursor);
        //
        jFrame.setVisible(true);
        //----------------------------------------------

        
        //game thread
        new Thread(gameWindow).start();
        new MusicThread(jFrame).start();
        //
        while(true) {
        	if(jFrame.isFocused()) {
            	if(EXIT==-1) {
            		System.exit(0);
            		//jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
            	}
                if(window==1) {
                    jFrame.setContentPane(beginWindow);
                    jFrame.setVisible(true);
                }
                else if(window==2) {
                    jFrame.setContentPane(gameWindow);
                    jFrame.setVisible(true);
                }
        	}
        	Thread.sleep(300);
        }    
    }*/
}
