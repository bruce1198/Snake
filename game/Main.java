package game;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main{
    public static boolean PAUSE = false;
    public static boolean RESET = false;
    public static int window = 1;
    public static void main(String[] args) throws InterruptedException
                                                , FileNotFoundException
                                                , IOException{

        JFrame jFrame = new JFrame("Snake");
        Player player = new Player(5);
        GameWindow gameWindow = new GameWindow(player);
        BeginWindow beginWindow = new BeginWindow();
        jFrame.addKeyListener(new GameKeyListener(player));
        jFrame.setContentPane(beginWindow);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.pack();

        //set icon
        BufferedImage icon = ImageIO.read(new File(".\\source\\icon.png"));
        jFrame.setIconImage(icon);
        jFrame.setVisible(true);

        //background music
        /*File bgm = new File("naruto.wav");
        Clip clip;
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bgm);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();*/
        //init(gameWindow);
        int put = 0;
        while(true) {
            //System.out.println();
            if(window==1) {
                jFrame.setContentPane(beginWindow);
                jFrame.setVisible(true);
                beginWindow.repaint();
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e) {
                    
                }
            }
            else if(window==2) {
                jFrame.setContentPane(gameWindow);
                jFrame.setVisible(true);
                try {
                    if(!PAUSE) gameWindow.moveSnake();
                } catch (Exception e) {
                    window = 2;
                }
    
                if(Point.eggLeft==0 && !PAUSE) {
                    put++;
                }
                if(put==200 && !PAUSE) {
                    gameWindow.putPoint(new Point());
                    gameWindow.putPoint(new Point());
                    Point.eggLeft = 2;
                    put = 0;
                }
                gameWindow.repaint();
                try{
                    Thread.sleep(10);
                }catch(InterruptedException e) {
                    
                }
            }
        }
    }
    private static void init(GameWindow gameWindow) {
        //put wall
        gameWindow.putWall(Wall.getWalls());
        gameWindow.putCave(new SnakeCave());
        gameWindow.putCave(new SnakeCave());
        //put egg
        gameWindow.putPoint(new Point());
        gameWindow.putPoint(new Point());
        Point.eggLeft = 2;
    }
}