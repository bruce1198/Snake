package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class BeginWindow extends JPanel{
    static int choice = 0;
    Image beginPage;
    Image start;
    Image help;
    Image water;
    BeginWindow() {
        setPreferredSize(new Dimension(1000, 600));
        try {
            beginPage = ImageIO.read(new File(".\\source\\picture\\beginPage.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
        try {
            start = ImageIO.read(new File(".\\source\\picture\\start.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
        try {
            help = ImageIO.read(new File(".\\source\\picture\\help.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
        try {
            water = ImageIO.read(new File(".\\source\\picture\\water.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //background
        g.drawImage(beginPage, 0, 0, beginPage.getWidth(null), beginPage.getHeight(null), null);
        /*drawBox(g, Color.BLACK, 100, 200, 200, 80);
        drawBox(g, Color.BLACK, 100, 320, 200, 80);
        drawBox(g, Color.RED, 100, 200+choice*120, 200, 80);
        drawBox(g, new Color(173, 92, 0), 105, 205, 190, 70);
        drawBox(g, new Color(173, 92, 0), 105, 325, 190, 70);
        write(g, Color.WHITE, "Start", 60, 135, 260);
        write(g, Color.WHITE, "About", 60, 125, 380);*/
        g.drawImage(start, 100, 180, start.getWidth(null), start.getHeight(null), null);
        g.drawImage(help, 100, 380, help.getWidth(null), help.getHeight(null), null);
        g.drawImage(water, 350, 180+choice*200, water.getWidth(null), water.getHeight(null), null);
    }
    /*private void drawBox(Graphics g, Color color, int x, int y, int width, int height) {
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, 10, 10);
        g.fill3DRect(x, y, width, height, true);
    }
    private void write(Graphics g, Color color, String word, int size, int x, int y) {
        g.setColor(color);
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, size));
        g.drawString(word, x, y);
    }*/
}