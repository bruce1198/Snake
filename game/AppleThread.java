package game;

public class AppleThread extends Thread {
	
	GameWindow window;
	
	AppleThread(GameWindow window) {
		this.window = window;
	}
	@Override
	public void run() {
		while(true) {
			if(Point.eggLeft==0 && !Main.PAUSE) {
				window.putPoint();
                window.putPoint();
                Point.eggLeft = 2;
                System.out.println("put");
                try {
					wait(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
		}
	}
}
