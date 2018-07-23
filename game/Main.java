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
                                                , IOException
                                                , LineUnavailableException{

        JFrame jFrame = new JFrame("Snake");
        Player player = new Player(5);
        Player player2 = new Player(5, 2);
        GameWindow gameWindow = new GameWindow(player, player2);
        BeginWindow beginWindow = new BeginWindow();
        jFrame.addKeyListener(new GameKeyListener(player, player2));
        jFrame.setContentPane(beginWindow);
        //jFrame.setLocation(350, 100);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        
        /*JButton resume = new JButton();
        resume.setLayout(null);
        resume.setBounds(200, 200, 400, 250);
        resume.setIcon(new ImageIcon(ImageIO.read(new File(".\\source\\picture\\button.png"))));
        resume.setVisible(false);
        jFrame.add(resume);*/

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
        int put = 0;
        while(true) {
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
                /*if(PAUSE) {
                    System.out.println("hi");
                    resume.setVisible(true);
                }
                else 
                    resume.setVisible(false);*/
                try {
                    if(!PAUSE) gameWindow.moveSnake(player.getSnake(), 0);
                    if(!PAUSE) gameWindow.moveSnake(player2.getSnake(), 1);
                    //if(!PAUSE) gameWindow.moveSnake2();
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
                    Thread.sleep(5);
                }catch(InterruptedException e) {
                    
                }
            }
        }
    }
}