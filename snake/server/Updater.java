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
	}
	@Override
	public void run() {
		while(true) {
			
		while(true) {
			try {
				DynamicUIData duiData = null;
				duiData = (DynamicUIData) ois.readObject();
				synchronized(duidata){
					update(duiData);
				}
			} catch(SocketException e) {
				System.out.println("Player has left");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
	}

}
