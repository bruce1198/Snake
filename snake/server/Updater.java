package snake.server;

import java.io.*;
import java.net.*;
import snake.game.*;

public class Updater extends Thread{
	
	DynamicUIData duidata;
    ObjectInputStream ois;
    
	Updater(DynamicUIData duidata, ObjectInputStream ois) {
		this.duidata = duidata;
		this.ois = ois;
	}
	private void update(DynamicUIData duiData) {
		duidata.s1.dir = duiData.nextDir1;
		duidata.s2.dir = duiData.nextDir2;
		duidata.nextDir1 = duiData.nextDir1;
		duidata.nextDir2 = duiData.nextDir2;
		duidata.PAUSE1 = duiData.PAUSE1;
		duidata.PAUSE2 = duiData.PAUSE2;
		duidata.inGame1 = duiData.inGame1;
		duidata.inGame2 = duiData.inGame2;
		duidata.GAMEOVER = duiData.GAMEOVER;
	}
	@Override
	public void run() {
		while(true) {
			try {
				DynamicUIData duiData = null;
				duiData = (DynamicUIData) ois.readObject();
				synchronized(duidata){
					update(duiData);
				}
			} catch(SocketException e) {
				//System.out.println("Player has left");
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}

}
