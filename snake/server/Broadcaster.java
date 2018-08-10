package snake.server;

import java.io.*;
import java.net.SocketException;
import java.util.*;

import snake.game.*;

public class Broadcaster extends Thread {
	
	DynamicUIData duidata;
	ArrayList<ObjectOutputStream> oosList;
	
	Broadcaster(DynamicUIData duidata, ArrayList<ObjectOutputStream> oosList) {
		this.duidata = duidata;
		this.oosList = oosList;
	}
	
	@Override
	public void run() {
		while(duidata.inGame1||duidata.inGame2) {
			try { Thread.sleep(20); } catch (InterruptedException e) { }
			for(int index=0; index<Server.PERSONS; index++) {
				try {
					oosList.get(index).reset();
					oosList.get(index).writeObject(duidata);
				} catch(SocketException e) {
					switch(index) {
						case 0:
							duidata.inGame1 = false;
							break;
						case 1:
							duidata.inGame2 = false;
							break;
					}
				}
				catch (IOException e) {
					
				}
			}
		}
		System.out.println("broadcaster stop");
	}

}
