package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class BeginWindow extends JPanel{
    static int choice = 0;
    int WIDTH;
    int HEIGHT;
    //mouse
    static boolean exitClick;
    Image beginPage;
    Image start;
    Image help;
    Image water;
    Image exit;
    BeginWindow() {
        setPreferredSize(new Dimension(1000, 600));
    	WIDTH = 1000;
        HEIGHT = 600;
        exitClick = false;
        /*setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
        HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;*/
        try {
            beginPage = ImageIO.read(new File(".\\source\\picture\\beginPage.png"));
            start = ImageIO.read(new File(".\\source\\picture\\start.png"));
            help = ImageIO.read(new File(".\\source\\picture\\help.png"));
            water = ImageIO.read(new File(".\\source\\picture\\water.png"));
            exit = ImageIO.read(new File(".\\source\\picture\\escape.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //background
        g.drawImage(beginPage, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(start, WIDTH/10, HEIGHT/3, WIDTH*start.getWidth(null)/1200, WIDTH*start.getHeight(null)/1200, null);
        g.drawImage(help, WIDTH/10, 5*HEIGHT/8, WIDTH*start.getWidth(null)/1200, WIDTH*start.getHeight(null)/1200, null);
        g.drawImage(water, WIDTH/3, HEIGHT-360+choice*180, WIDTH*water.getWidth(null)/1600, HEIGHT*water.getHeight(null)/900, null);
        if(exitClick) {
        	g.setColor(new Color(255, 255, 255, 100));
        	g.fillRoundRect(WIDTH-exit.getWidth(null)-3, HEIGHT-exit.getHeight(null)-3, exit.getWidth(null)+3, exit.getHeight(null)+3, 20, 20);
        }
        g.drawImage(exit, WIDTH-exit.getWidth(null), HEIGHT-exit.getHeight(null), exit.getWidth(null), exit.getHeight(null), null);
        //
        repaint();
    }
}