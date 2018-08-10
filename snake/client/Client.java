package snake.client;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;


public class Client extends Thread{

	public static int window;
	public static int change;
	public static int once;
	public static int EXIT;
	public static String name;
	public static String IP;
	public static int port;

	public Client() {
		window = 0;
		change = 0;
		once = 1;
		EXIT = 666;
		name = "";
		IP = "";
		port = 0;
	}

	@Override
	public void run() {
		JFrame jFrame = new JFrame("Snake");
		BeginUI beginUI = new BeginUI();
		LoginUI loginUI = new LoginUI();
		jFrame.setContentPane(beginUI);
		jFrame.addKeyListener(beginUI);
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
        jFrame.setFocusable(true);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null); //center the jframe
        //set icon
		try {
	        Image icon = ImageIO.read(new File(".\\source\\picture\\icon.png"));
	        jFrame.setIconImage(icon);
	        //set cursor.
	        Image cursor = ImageIO.read(new File(".\\source\\picture\\cursor.png"));
	        Cursor myCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new java.awt.Point(0, 0), "blank cursor");
	        jFrame.setCursor(myCursor);
		} catch (IOException e) {
			e.printStackTrace();
		}
        jFrame.setVisible(true);
        //bgm
        Clip bgm;
		try {
	        AudioInputStream in = AudioSystem.getAudioInputStream(new File(".\\source\\music\\bgm.wav"));
			bgm = AudioSystem.getClip();
	        bgm.open(in);
	        bgm.loop(-1);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        while(true) {
        	if(jFrame.isFocused()) {
        		if(EXIT==-1) {
        			System.exit(0);
        		}
        		if(window==0) {
        			if(once==1) {
                        jFrame.setContentPane(beginUI);
                        jFrame.setVisible(true);
                        once = 0;
        			}
                }
        		else if(window==1) {
        			if(change==2) {
            			jFrame.setContentPane(loginUI);
            			jFrame.setVisible(true);
            			change = 1;
        			}
        		}
                else if(window==2) {
                	if(change==1) {
                		ClientUI UI = new ClientUI();
                		jFrame.addKeyListener(UI);
                		//System.out.println("change");
                        new Thread(UI).start();
                        jFrame.setContentPane(UI);
                        jFrame.setVisible(true);
                        change = 0;
                	}
                }
            	try {
    				Thread.sleep(300);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
        	}
        }
	}
}
