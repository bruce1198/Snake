package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class BeginWindow extends JPanel{
    static int choice;
    BeginWindow() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE); 
        choice = 0;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            Image beginPage = ImageIO.read(new File(".\\source\\beginPage.png"));
            g.drawImage(beginPage, 0, 0, beginPage.getWidth(null), beginPage.getHeight(null), null);
        } catch (Exception e) {
            System.out.println("Error");
        }
        drawBox(g, Color.BLACK, 100, 200, 200, 80);
        drawBox(g, Color.BLACK, 100, 320, 200, 80);
        drawBox(g, Color.RED, 100, 200+choice*120, 200, 80);
        drawBox(g, new Color(173, 92, 0), 105, 205, 190, 70);
        drawBox(g, new Color(173, 92, 0), 105, 325, 190, 70);
        write(g, Color.WHITE, "Start", 60, 135, 260);
        write(g, Color.WHITE, "About", 60, 125, 380);
    }
    private void drawBox(Graphics g, Color color, int x, int y, int width, int height) {
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, 10, 10);
    }
    private void write(Graphics g, Color color, String word, int size, int x, int y) {
        g.setColor(color);
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, size));
        g.drawString(word, x, y);
    }
}