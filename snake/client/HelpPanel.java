package snake.client;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class HelpPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Image image;
	
	HelpPanel(Image image) {
		setPreferredSize(new Dimension(600, 1000));
		this.image = image;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
		repaint();
	}
	/*public static void main(String[] args) {
		JFrame frame = new JFrame();
		Image helppane = null;
        try {
            helppane = ImageIO.read(new File(".\\source\\picture\\helppane.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
		HelpPanel panel = new HelpPanel(helppane);
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}*/
}
