package snake.server;

import java.io.*;
import java.net.*;
import java.util.*;

import snake.game.*;

public class Initialization extends Thread{
    
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
				oosList.get(index).writeObject(uidata);
			} catch (IOException e) {
				System.out.println("Write Data Error");
			}
    	}
    }
    @Override
    public void run() {
        try {
            init();
        }catch(Exception e) {

        }
        new Updater(duidata, oisList.get(0)).start();
        new Updater(duidata, oisList.get(1)).start();
        new Broadcaster(duidata, oosList).start();
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        new RunGame(duidata, uidata).start();
    }
}