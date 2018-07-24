package game;

import java.awt.event.WindowEvent;

public class GameThread extends Thread {
	Player p1, p2;
	GameWindow window;
	GameThread(GameWindow window, Player p1, Player p2) {
		this.window = window;
		this.p1 = p1;
		this.p2 = p2;
	}
	@Override
	public void run() {
		
	}
}
