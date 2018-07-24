package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class GameMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

	public static int MOUSE_X;
	public static int MOUSE_Y;
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MOUSE_X = e.getX();
		MOUSE_Y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MOUSE_X = e.getX();
		MOUSE_Y = e.getY();
		if(Main.window==1) {
			if(MOUSE_X>=900&&MOUSE_X<=1000&&MOUSE_Y>=520&&MOUSE_Y<=600) {
				BeginWindow.exitClick = true;
			}
			else {
				BeginWindow.exitClick = false;
			}
		}
		else if(Main.window==2) {
			if(MOUSE_X>=300&&MOUSE_X<=500&&MOUSE_Y>=415&&MOUSE_Y<=540) {
				GameWindow.exitClick = true;
			}
			else
				GameWindow.exitClick = false;
			if(MOUSE_X>=300&&MOUSE_X<=500&&MOUSE_Y>=55&&MOUSE_Y<=180) {
				GameWindow.resumeClick = true;
			}
			else
				GameWindow.resumeClick = false;
			if(MOUSE_X>=300&&MOUSE_X<=500&&MOUSE_Y>=235&&MOUSE_Y<=360) {
				GameWindow.menuClick = true;
			}
			else
				GameWindow.menuClick = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(Main.window==1) {
			if(MOUSE_X>=900&&MOUSE_X<=1000&&MOUSE_Y>=520&&MOUSE_Y<=600) {
				BeginWindow.exitClick = true;
			}
		}
		else if(Main.window==2 ) {
			if(MOUSE_X>=300&&MOUSE_X<=500&&MOUSE_Y>=415&&MOUSE_Y<=540) {
				GameWindow.exitClick = true;
			}
			if(MOUSE_X>=300&&MOUSE_X<=500&&MOUSE_Y>=55&&MOUSE_Y<=180) {
				GameWindow.resumeClick = true;
			}
			if(MOUSE_X>=300&&MOUSE_X<=500&&MOUSE_Y>=235&&MOUSE_Y<=360) {
				GameWindow.menuClick = true;
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(Main.window==1) {
			if(MOUSE_X>=900&&MOUSE_X<=1000&&MOUSE_Y>=520&&MOUSE_Y<=600) {
				Main.EXIT = -1;
				System.out.println("click");
			}
			BeginWindow.exitClick = false;
		}
		else if(Main.window==2) {
			//exit
			if(MOUSE_X>=300&&MOUSE_X<=500&&MOUSE_Y>=415&&MOUSE_Y<=540 && (Main.PAUSE || Main.GAMEOVER)) {
				Main.EXIT = -1;
			}
			//resume
			if(MOUSE_X>=300&&MOUSE_X<=500&&MOUSE_Y>=55&&MOUSE_Y<=180 && Main.PAUSE) {
				Main.PAUSE = false;
			}
			//menu
			if(MOUSE_X>=300&&MOUSE_X<=500&MOUSE_Y>=235&&MOUSE_Y<=360 && (Main.PAUSE || Main.GAMEOVER)) {
				Main.window = 1;
				Main.change = 1;
				Main.GAMEOVER = false;
			}
			GameWindow.resumeClick = false;
			GameWindow.menuClick = false;
			GameWindow.exitClick = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
