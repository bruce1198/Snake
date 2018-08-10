package snake.client;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BeginUI extends JPanel implements KeyListener, MouseListener, MouseMotionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
    Image helppane;
    HelpPanel panel;
    MyButton returnBtn;
    JScrollPane jsp;
    BeginUI() {
        setPreferredSize(new Dimension(1000, 600));
    	WIDTH = 1000;
        HEIGHT = 600;
        exitClick = false;
        try {
            beginPage = ImageIO.read(new File(".\\source\\picture\\beginPage.png"));
            start = ImageIO.read(new File(".\\source\\picture\\start.png"));
            help = ImageIO.read(new File(".\\source\\picture\\help.png"));
            water = ImageIO.read(new File(".\\source\\picture\\water.png"));
            exit = ImageIO.read(new File(".\\source\\picture\\escape.png"));
            helppane = ImageIO.read(new File(".\\source\\picture\\helppane.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
        //help
        setLayout(null);
        panel = new HelpPanel(helppane);
        returnBtn = new MyButton("Close");
        returnBtn.setBounds(450, 520, 100, 50);
        returnBtn.setVisible(false);
        returnBtn.setForeground(Color.BLACK);
        returnBtn.setFont(new Font("Consolas", Font.PLAIN, 30));
        returnBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jsp.setVisible(false);
				returnBtn.setVisible(false);
			}
        	
        });
        jsp = new JScrollPane(panel);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(200, 100, 600, 400);
        jsp.setVisible(false);
        add(jsp);
        add(returnBtn);
        addMouseListener(this);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
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
        if(jsp.isVisible()) {
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, 1000, 600);
        }
        repaint();
    }
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(Client.window==0 && !jsp.isVisible()) {
			switch(e.getKeyCode()) {
		        case KeyEvent.VK_ENTER:
		            if(BeginUI.choice==0) {
		                Client.window = 1;
		                Client.change = 2;
		            }
		            else if(BeginUI.choice==1) {
		            	jsp.setVisible(!jsp.isVisible());
		            	returnBtn.setVisible(!returnBtn.isVisible());
		            }
		            break;
		        case KeyEvent.VK_UP:
		            if(BeginUI.choice!=0) {
		                BeginUI.choice--;
		            }
		            break;
		        case KeyEvent.VK_DOWN:
		            if(BeginUI.choice!=1)
		                BeginUI.choice++;
		            break;
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		/*if(Main.window==1) {
			if(e.getKeyCode()==KeyEvent.VK_M) {
				if(MusicThread.volume==0.4f)
					MusicThread.volume = 0.0f;
				else if(MusicThread.volume==0.0f)
					MusicThread.volume = 0.4f;
			}
		}*/
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(Client.window==0) {
			if(e.getX()>=900&&e.getX()<=1000&&e.getY()>=520&&e.getY()<=600 && !jsp.isVisible()) {
				BeginUI.exitClick = true;
			}
			else {
				BeginUI.exitClick = false;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(Client.window==0 && !jsp.isVisible()) {
			if(e.getX()>=900&&e.getX()<=1000&&e.getY()>=520&&e.getY()<=600) {
				BeginUI.exitClick = true;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(Client.window==0 && !jsp.isVisible()) {
			if(e.getX()>=900&&e.getX()<=1000&&e.getY()>=520&&e.getY()<=600) {
				Client.EXIT = -1;
				//System.out.println("click");
			}
			BeginUI.exitClick = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
}