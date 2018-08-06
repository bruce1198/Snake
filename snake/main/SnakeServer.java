package snake.main;

import java.io.*;

import snake.server.Server;

public class SnakeServer {
	public static void main(String[] args) throws IOException{
		new Server().start();
	}
}
