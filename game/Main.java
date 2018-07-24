package game;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main{
	public static int EXIT = 666;
    public static boolean PAUSE = false;
    public static boolean GAMEOVER = false;
    public static int window = 1;
    public static int change = 0;
    public static void main(String[] args) throws InterruptedException
                                                , FileNotFoundException
                                                , IOException
                                                , LineUnavailableException{

        JFrame jFrame = new JFrame("Snake");
        Player player = new Player(5);
        Player player2 = new Player(5, 2);
        //window
        GameWindow gameWindow = new GameWindow(player, player2);
        BeginWindow beginWindow = new BeginWindow();
        //listener
        GameMouseListener listener = new GameMouseListener();
        GameKeyListener keyListener = new GameKeyListener(player, player2);
        jFrame.setContentPane(beginWindow);
        jFrame.addKeyListener(keyListener);
        jFrame.addMouseListener(listener);
        jFrame.addMouseMotionListener(listener);
        jFrame.addMouseWheelListener(listener);
        jFrame.setUndecorated(true);//hide the bar
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null); //center the jframe
        jFrame.setVisible(true);

        //set icon
        BufferedImage icon = ImageIO.read(new File(".\\source\\picture\\icon.png"));
        jFrame.setIconImage(icon);
        jFrame.setVisible(true);

        //background music
        /*File bgm = new File("naruto.wav");
        Clip clip;
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bgm);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();*/
        AppleThread AThread = new AppleThread(gameWindow);
        //GameThread main = new GameThread(gameWindow, player, player2);
        //main.start();
        AThread.start();
        int put = 0;
        while(true) {
        	if(EXIT==-1) {
        		jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
        	}
            if(window==1) {
                jFrame.setContentPane(beginWindow);
                jFrame.setVisible(true);
                if(change==1) {
                    player = new Player(1);
                    player2 = new Player(5, 2);
                    keyListener.set(player, player2);
                    init(gameWindow, player, player2);
                    change = 0;
                }
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e) {
                    
                }
            }
            else if(window==2) {
                jFrame.setContentPane(gameWindow);
                jFrame.setVisible(true);
                //AThread.start();
                try {
                    if(!PAUSE) gameWindow.moveSnake(player.getSnake(), player2.getSnake(), 0);
                    if(!PAUSE) gameWindow.moveSnake(player2.getSnake(), player.getSnake(), 1);
                } catch (Exception e) {
                    GAMEOVER = true;
                }
    
                if(Point.eggLeft==0 && !PAUSE) {
                    put++;
                }
                if(put==200 && !PAUSE) {
                    gameWindow.putPoint();
                    gameWindow.putPoint();
                    Point.eggLeft = 2;
                    put = 0;
                }
                try{
                    Thread.sleep(10);
                }catch(InterruptedException e) {
                    
                }
            }
        }
    }
    public static void init(GameWindow window ,Player player, Player player2) {
    	window.p1 = player;
    	window.p2 = player2;
    	window.numberOfPoint = 0;
        window.points = null;
    	window.move = new int[2];
        //keyboard
    	window.valid = new boolean[2];
        //mouse
    	window.exitClick = false;
    	window.resumeClick = false;
    	window.menuClick = false;
        Point.eggLeft = 2;
        //put wall
        window.putWall(Wall.getWalls());
        //System.out.println(Wall.WALL_KIND);
        //System.out.println(Wall.WALL_NUMBER);
        //put caves
        window.putCave(SnakeCave.getSnakeCaves());
        //System.out.println(SnakeCave.CAVE_KIND);
        //System.out.println(SnakeCave.CAVE_NUMBER);
        //put egg
        window.putPoint();
        window.putPoint();
    }
}