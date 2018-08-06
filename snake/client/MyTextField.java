package snake.client;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MyTextField extends JTextField implements FocusListener{
	private static final long serialVersionUID = 1L;

	private final String hint;
	private boolean showingHint;
	
	MyTextField(final String hint) {
		super(hint);
	    this.hint = hint;
	    this.showingHint = true;
	    super.addFocusListener(this);
	    setOpaque(false);
	    setBorder(new EmptyBorder(0, 15, 0, 0));
		super.setForeground(Color.lightGray);
	}
	public void paintComponent(Graphics g){
	
	    Graphics2D gd = (Graphics2D)g.create();
	 
	    gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	    Paint oldpaint = gd.getPaint();
	    GradientPaint paint = new GradientPaint(50,0,Color.white,getWidth(),getHeight(),Color.white.darker(),true);
	    gd.setPaint(paint);
	    gd.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);        
	    gd.setPaint(oldpaint);
	
	    super.paintComponent(gd);
	
	    gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_DEFAULT);
	    gd.dispose();   
	}   
	@Override
	public void focusGained(FocusEvent e) {
		if(this.getText().isEmpty()) {
			super.setForeground(Color.black);
			super.setText("");
			showingHint = false;
	    }
	}
	@Override
	public void focusLost(FocusEvent e) {
		if(this.getText().isEmpty()) {
			super.setForeground(Color.lightGray);
			super.setText(hint);
			showingHint = true;
		}
	}

	@Override
	public String getText() {
		return showingHint ? "" : super.getText();
	}
}
