package snake.server;

import java.io.*;
import java.util.*;

import snake.game.UIData;

public class Updater extends Thread{
	
	UIData uidata;
    ObjectInputStream ois;
    
	Updater(UIData uidata, ObjectInputStream ois) {
		this.uidata = uidata;
		this.ois = ois;
	}
	private void update(UIData uiData) {
		uidata.s1.dir = uiData.nextDir1;
		uidata.s2.dir = uiData.nextDir2;
	}
	@Override
	public void run() {
		while(true) {
			
		while(true) {
			try {
				UIData uiData = null;
				uiData = (UIData) ois.readObject();
				synchronized(uidata){
					update(uiData);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
	}

}
