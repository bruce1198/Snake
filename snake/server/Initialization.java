package snake.server;

import java.io.*;
import java.net.*;
import java.util.*;

import snake.game.*;

public class Initialization implements Runnable{
    
	ArrayList<Socket> clients;
    ArrayList<ObjectOutputStream> oosList;
    ArrayList<ObjectInputStream> oisList;
    UIData uidata;
    DynamicUIData duidata;
    Initialization(ArrayList<Socket> clients, ArrayList<ObjectInputStream> oisList, ArrayList<ObjectOutputStream> oosList) {
        try {
            this.clients = clients;
            this.oisList = oisList;
            this.oosList = oosList;
        } catch (Exception e) {

        }
    }
    private void init() {
        uidata = new UIData();
        duidata = new DynamicUIData();
    	for(int index=0; index<Server.PERSONS; index++) {
    		try {
    			DynamicUIData duiData = (DynamicUIData)oisList.get(index).readObject();
    			duidata.inGame1 = duiData.inGame1;
    			duidata.inGame2 = duiData.inGame2;
    			oosList.get(index).writeObject(uidata);
    			oosList.get(index).writeObject(duidata);
			} catch (IOException e) {
				//System.out.println("Write Data Error");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
    	}
    }
    @Override
    public void run() {
        try {
            init();
        }catch(Exception e) {

        }
    	
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        Updater u1 = new Updater(duidata, oisList.get(0), 0);
        Updater u2 = new Updater(duidata, oisList.get(1), 1);
        Broadcaster caster = new Broadcaster(duidata, oosList);
        RunGame game = new RunGame(duidata, uidata);
        u1.start();
        u2.start();
        caster.start();
        game.start();
    }
}