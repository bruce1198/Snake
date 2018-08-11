package snake.server;

import java.io.*;
import java.net.*;
import snake.game.*;

public class Updater extends Thread{
	
	DynamicUIData duidata;
    ObjectInputStream ois;
    int id;
    
	Updater(DynamicUIData duidata, ObjectInputStream ois, int id) {
		this.duidata = duidata;
		this.ois = ois;
		this.id = id;
	}
	private void update(DynamicUIData duiData) {
		switch(id) {
			case 0:
				duidata.s1.dir = duiData.nextDir1;
				duidata.nextDir1 = duiData.nextDir1;
				duidata.PAUSE1 = duiData.PAUSE1;
				duidata.inGame1 = duiData.inGame1;
				break;
			case 1:
				duidata.s2.dir = duiData.nextDir2;
				duidata.nextDir2 = duiData.nextDir2;
				duidata.PAUSE2 = duiData.PAUSE2;
				duidata.inGame2 = duiData.inGame2;
				break;
		}
		duidata.GAMEOVER = duiData.GAMEOVER;
	}
	@Override
	public void run() {
		while(duidata.inGame1||duidata.inGame2) {
			try {
				DynamicUIData duiData = null;
				duiData = (DynamicUIData) ois.readObject();
				synchronized(duidata){
					update(duiData);
				}
			} catch(SocketException e) {
				break;
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		System.out.println("updater stop");
	}

}
