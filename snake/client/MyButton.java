package snake.client;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class MyButton extends JButton {

    private static final long serialVersionUID = 1L;

    private Color color;
    
    public MyButton(String label) {
        super(label);
	    setOpaque(false);
	    setBorder(new EmptyBorder(0, 0, 0, 0));
	    color = new Color(255, 189, 97);
    }

    @Override
    protected void paintComponent(Graphics g) {

	    Graphics2D gd = (Graphics2D)g.create();
	 
	    gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    Paint oldpaint = gd.getPaint();
	    GradientPaint paint = new GradientPaint(50, 0, color, getWidth(), getHeight(), color, true);
	    gd.setPaint(paint);
	    gd.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);        
	    gd.setPaint(oldpaint);
	
	    super.paintComponent(gd);
	
	    gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
	    gd.dispose(); 
    }

}