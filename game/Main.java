package game;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main{
	public static int EXIT = 666;
    public static int window = 1;
    public static int change = 0;
    public static void main(String[] args) throws InterruptedException
                                                , FileNotFoundException
                                                , IOException
                                                , LineUnavailableException{

        JFrame jFrame = new JFrame("Snake");
        //window
        GameWindow gameWindow = new GameWindow();
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
    }
}