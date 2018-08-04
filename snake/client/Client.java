package snake.client;

import java.io.*;
import java.net.*;
import javax.swing.*;
import snake.server.*;

public class Client extends Thread{

	Socket client;
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;

	public Client() {

	}

	@Override
	public void run() {
		try {
			client = new Socket("localhost", Server.port);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			System.out.println("connect");
		} catch (IOException e) {
			System.out.println("Connect Error");
		}
		JFrame jFrame = new JFrame();
		ClientUI UI = new ClientUI(ois, oos);
		jFrame.setContentPane(UI);
		jFrame.addKeyListener(UI);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        new Thread(UI).start();
	}
}
