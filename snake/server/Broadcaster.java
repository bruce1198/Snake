package snake.server;

import java.io.*;
import java.net.SocketException;
import java.util.*;

import snake.game.UIData;

public class Broadcaster extends Thread {
	
	UIData uidata;
	ArrayList<ObjectOutputStream> oosList;
	
	Broadcaster(UIData uidata, ArrayList<ObjectOutputStream> oosList) {
		this.uidata = uidata;
		this.oosList = oosList;
	}
	
	@Override
	public void run() {
		while(true) {
			try { Thread.sleep(20); } catch (InterruptedException e) { }
			for(int index=0; index<Server.PERSONS; index++) {
				try {
					oosList.get(index).reset();
					oosList.get(index).writeObject(uidata);
				} catch(SocketException e) {
					System.out.println("Player has left");
				}
				catch (IOException e) {
					System.out.println("Write Data Error");
				}
			}
		}
	}

}
