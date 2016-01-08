package client;

import java.io.IOException;

import gui.GUIChat;


public class Main {

	public static void main(String[] args) {
		String serverHost = "localhost";
		int serverPort = 8080;
		String userName = "2";
		GUIChat gui = new GUIChat();
		ClientController c = new ClientController(gui, serverHost, serverPort, userName);
		try {
			c.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gui.Start(c, "Client");
	}

}
