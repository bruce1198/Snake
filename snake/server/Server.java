package snake.server;

import java.net.*;
import java.util.*;
import java.io.*;

public class Server extends Thread{
	public static final int PERSONS = 2;
	public static final int port = 5487; 

	ServerSocket server = null;
	ArrayList<Socket> clients = new ArrayList<Socket>();
	ArrayList<ObjectOutputStream> oosList;
	ArrayList<ObjectInputStream> oisList;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	public Server() {
		clients = new ArrayList<Socket>();
		oosList = new ArrayList<ObjectOutputStream>();
		oisList = new ArrayList<ObjectInputStream>();
	}

	@Override
	public void run() {
    	
        try {
			server = new ServerSocket(port);
			System.out.println(InetAddress.getLocalHost());
            while(true) {
        		clients = new ArrayList<Socket>();
        		oosList = new ArrayList<ObjectOutputStream>();
        		oisList = new ArrayList<ObjectInputStream>();
            	for(int i=0; i<PERSONS; i++) {
                	Socket client = server.accept();
                	oos = new ObjectOutputStream(client.getOutputStream());
                	ois = new ObjectInputStream(client.getInputStream());
                	oos.writeObject(i);
                	clients.add(client);
                	oosList.add(oos);
                	oisList.add(ois);
                }
            	Initialization initialization = new Initialization(clients, oisList, oosList);
            	initialization.start();
            	/*clients.clear();
            	oisList.clear();
            	oosList.clear();*/
            }
        } catch (IOException e) {
        	
        } 
	}
}