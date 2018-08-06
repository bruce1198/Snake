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
		while(true) {
			try { Thread.sleep(20); } catch (InterruptedException e) { }
			for(int index=0; index<Server.PERSONS; index++) {
				try {
					oosList.get(index).reset();
					oosList.get(index).writeObject(duidata);
				} catch(SocketException e) {
					//System.out.println("Player has left");
				}
				catch (IOException e) {
					System.out.println("Write Data Error");
				}
			}
		}
	}

}
